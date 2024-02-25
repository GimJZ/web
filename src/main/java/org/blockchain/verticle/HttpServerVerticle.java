package org.blockchain.verticle;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import java.util.Base64;

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
            decodedString = new String(decodedBytes);
        } catch (IllegalArgumentException e) {
            context.response().setStatusCode(400).end("Invalid Base64 encoding");
            return;
        }

        // Process the decoded string (for demonstration, we'll simply return it)
        context.response().putHeader("Content-Type", "application/json").end(decodedString);
    }
}
