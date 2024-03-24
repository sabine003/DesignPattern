package com.fges.todoapp;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.List;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class WebCommand implements Command {

    private final String dataSource;

    public WebCommand(String dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public int execute(List<String> args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        TodoFileStorage fileStorage = new TodoFileStorage(dataSource);

        server.createContext("/todos", new HttpHandler() {
            @Override
            public void handle(HttpExchange exchange) throws IOException {
                String method = exchange.getRequestMethod();
                String response = "";
                if ("POST".equals(method)) {
                    // Handle POST request here
                    response = "TODO created"; // Simplified for demonstration
                } else if ("GET".equals(method)) {
                    // Handle GET request here
                    response = "Listing TODOs"; // Simplified for demonstration
                }
                exchange.sendResponseHeaders(200, response.getBytes().length);
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            }
        });

        System.out.println("Starting server on port 8080...");
        server.start();

        // To keep the server running and not exit the application immediately
        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            System.err.println("Server interrupted");
        }

        return 0;
    }
}
