package main.components;

import com.google.gson.Gson;
import main.responses.NumSequencesResponse;
import main.services.GenerateNumSequencesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;

@Component
public class AutoSendMessageHelper implements Runnable {

    private final int DEFAULT_COUNT = 6;
    private final long DELAY = 10000;

    private volatile boolean start;
    private WebSocketSession session;

    @Autowired
    private GenerateNumSequencesService service;

    public boolean isStart() {
        return start;
    }

    @Override
    public void run() {
        while (start) {
            try {
                NumSequencesResponse response = new NumSequencesResponse(service.getRandomSeq(DEFAULT_COUNT));
                String json = new Gson().toJson(response);
                session.sendMessage(new TextMessage(json));
                Thread.sleep(DELAY);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void start(WebSocketSession session) {
        start = true;
        this.session = session;
        new Thread(this).start();
    }

    public void stop() {
        start = false;
    }

}
