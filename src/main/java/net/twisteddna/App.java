package net.twisteddna;

import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.twisteddna.controllers.AccountController;
import io.netty.channel.Channel;
import org.glassfish.jersey.netty.httpserver.NettyHttpContainerProvider;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * Created by Maksym_Mazur on 5/19/2017.
 */
public class App {

    static final String ROOT_PATH = "transfer";

    private static final URI BASE_URI = URI.create("http://localhost:8080/");

    public static void main(String[] args) {
        try {
            ResourceConfig resourceConfig = new ResourceConfig(AccountController.class);
            final Channel server = NettyHttpContainerProvider.createHttp2Server(BASE_URI, resourceConfig, null);

            Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
                public void run() {
                    server.close();
                }
            }));

            System.out.println(
                String.format("Application started. (HTTP/2 enabled!)\nTry out %s%s\nStop the application using "
                              + "CTRL+C.", BASE_URI, ROOT_PATH));
            Thread.currentThread().join();
        } catch (InterruptedException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
