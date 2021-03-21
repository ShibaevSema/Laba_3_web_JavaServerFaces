import org.icefaces.ace.json.JSONException;
import org.icefaces.ace.json.JSONObject;

import javax.websocket.OnMessage;
import javax.websocket.server.ServerEndpoint;
import java.util.Locale;

@ServerEndpoint("/socket")
public class AreaCheckSocket {

    @OnMessage
    public String onMessage(String message) throws JSONException {
        JSONObject obj = new JSONObject(message.trim());
        try {
            Double x = obj.getDouble("x");
            Double y = obj.getDouble("y");
            Double r = obj.getDouble("r");
            Result result = new Result(x, y, r);
            boolean isInArea = result.checkArea(result);
            result.setIsInArea(isInArea);
            result.save();
            return String.format(Locale.US,"{\"x\":%.2f, \"y\":%.2f, \"r\":%.2f, \"inArea\":%s}", x, y, r, isInArea ? "true" : "false");
        } catch (Exception e) {
            return "error";
        }

    }

}