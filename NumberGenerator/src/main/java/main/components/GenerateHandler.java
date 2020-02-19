package main.components;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import main.responses.ErrorResponse;
import main.responses.NumSequencesResponse;
import main.services.GenerateNumSequencesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Map;

@Component
public class GenerateHandler extends TextWebSocketHandler {

    @Autowired
    private GenerateNumSequencesService service;

    @Autowired
    private AutoSendMessageHelper autoSendMessageHelper;

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        Map map = null;
        try {
            map = new Gson().fromJson(message.getPayload(), Map.class);
        } catch (JsonSyntaxException e) {
            session.sendMessage(new TextMessage(new Gson().toJson(new ErrorResponse("не корректный JSON"))));
            return;
        }

        if (autoSendMessageHelper.isStart()) {
            autoSendMessageHelper.stop();
        }

        Object countObject = map.get("count");
        if (countObject == null) {
            session.sendMessage(new TextMessage(new Gson().toJson(new ErrorResponse("не вверный ввод"))));
            return;
        }
        try {
            int count = (int) Double.parseDouble(countObject.toString());
            if (count < 0) {
                autoSendMessageHelper.start(session);
            } else if (count < 10 || count > 100) {
                session.sendMessage(new TextMessage(new Gson().toJson(new ErrorResponse("число должно быть от 10 до 100"))));
            } else {
                NumSequencesResponse response = new NumSequencesResponse(service.getRandomSeq(count));
                String json = new Gson().toJson(response);
                session.sendMessage(new TextMessage(json));
            }
        } catch (NumberFormatException ignore) {
            session.sendMessage(new TextMessage(new Gson().toJson(new ErrorResponse("ВВедите целочисленное число от 10 до 100"))));
        }
    }
}
