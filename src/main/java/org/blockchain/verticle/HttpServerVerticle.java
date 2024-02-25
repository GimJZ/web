package org.blockchain.verticle;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import org.blockchain.model.Blockchain;
import org.blockchain.util.JsonUtil;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.zip.InflaterInputStream;

public class HttpServerVerticle extends AbstractVerticle {

    @Override
    public void start(Promise<Void> startPromise) {
        Router router = Router.router(vertx);

        // Middleware to handle the body of the request if needed
        router.route().handler(BodyHandler.create());

        // Specific route for handling GET requests with a base64-encoded query parameter 'cc'
        router.get("/blockchain").handler(this::handleGetBlockchain);

        // Set up and start the HTTP server
        vertx.createHttpServer()
                .requestHandler(router)
                .listen(config().getInteger("http.port", 8080), http -> {
                    if (http.succeeded()) {
                        startPromise.complete();
                        System.out.println("HTTP server started on port 8080");
                    } else {
                        startPromise.fail(http.cause());
                    }
                });
    }

    private void handleGetBlockchain(RoutingContext context) {
        // Extract the 'cc' query parameter
        String base64Encoded = context.request().getParam("cc");
        if (base64Encoded == null) {
            context.response().setStatusCode(400).end("Missing 'cc' query parameter");
            return;
        }

        // Decode the base64-encoded string
        String decodedString;
        try {
            byte[] decodedBytes = Base64.getUrlDecoder().decode(base64Encoded);

            // Decompress the decoded bytes using the Zlib algorithm
            byte[] decompressedBytes = decompressZlib(decodedBytes);
            if (decompressedBytes == null) {
                context.response().setStatusCode(400).end("Invalid Zlib compression");
                return;
            }

            decodedString = new String(decompressedBytes, StandardCharsets.UTF_8);
            System.out.println("Decoded JSON string: " + decodedString);
        } catch (IllegalArgumentException e) {
            context.response().setStatusCode(400).end("Invalid Base64 encoding");
            return;
        }

        // Deserialize the decoded string into a Blockchain object
        try {
            Blockchain blockchain = JsonUtil.fromJsonToBlockchain(decodedString);
            // Process the blockchain object
        } catch (IllegalArgumentException | JsonProcessingException e) {
            // Log the error or provide a more descriptive error message
            System.err.println("Error parsing JSON: " + e.getMessage());
            e.printStackTrace();
            // Respond with an error to the client
            context.response().setStatusCode(400).end("Invalid JSON format");
        }

        // Process the decoded string (for demonstration, we'll simply return it)
        context.response().putHeader("Content-Type", "application/json").end(decodedString);
    }

    private static byte[] decompressZlib(byte[] compressedBytes) {
        try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(compressedBytes);
             InflaterInputStream inflaterInputStream = new InflaterInputStream(byteArrayInputStream);
             ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {

            byte[] buffer = new byte[1024];
            int len;
            while ((len = inflaterInputStream.read(buffer)) > 0) {
                byteArrayOutputStream.write(buffer, 0, len);
            }
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
