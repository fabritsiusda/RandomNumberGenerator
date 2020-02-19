package main.config;

import main.components.GenerateHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
@EnableAutoConfiguration
public class GenerateRandomNumberConfigure implements WebSocketConfigurer {

    @Autowired
    private GenerateHandler handler;

    public void registerWebSocketHandlers(WebSocketHandlerRegistry config) {
        config.addHandler(handler, "/generate");
    }

}
