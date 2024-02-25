import io.vertx.core.Vertx;
import org.blockchain.verticle.HttpServerVerticle;

public class App {
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new HttpServerVerticle());
    }
}
