package kr.ac.kopo.project_pas.event;

import android.content.Context;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EventManager {

    public interface EventListener {
        void onChoicesReady(String description, List<String> choices);
        void onEventResult(String resultText, String outcome);
    }

    private EventListener listener;
    private final Random random = new Random();
    private final String[] eventIds = {"church", "farm", "ruins", "temple", "alley"}; // 이벤트 파일명 배열

    // 고정 이벤트 ID (null이면 무작위 선택)
    private String fixedEventId = null;

    // 외부에서 고정 이벤트 설정할 수 있도록 메서드 제공
    public void setFixedEventId(String eventId) {
        this.fixedEventId = eventId;
    }

    public void setEventListener(EventListener listener) {
        this.listener = listener;
    }

    public JSONObject loadEventByRandomId(Context context) {
        // 고정 ID가 설정되어 있다면 그것을 우선 로드
        if (fixedEventId != null) {
            return loadEventById(context, fixedEventId);
        }

        int idx = random.nextInt(eventIds.length);
        String eventId = eventIds[idx];
        return loadEventById(context, eventId);
    }

    public JSONObject loadEventById(Context context, String eventId) {
        try {
            InputStream inputStream = context.getAssets().open("events/event_" + eventId + ".json");
            return loadEventFromStream(inputStream);
        } catch (Exception e) {
            Log.e("EventManager", "이벤트 파일을 여는 데 실패했습니다: " + eventId, e);
            return null;
        }
    }

    public void runEvent(JSONObject eventJson, String characterId) {
        String description = eventJson.optString("description");
        JSONArray choicesArray = eventJson.optJSONArray("choices");
        List<String> availableChoices = new ArrayList<>();

        for (int i = 0; i < choicesArray.length(); i++) {
            JSONObject choice = choicesArray.optJSONObject(i);
            if (!choice.has("characterId") || choice.optString("characterId").equals(characterId)) {
                int index = choice.optInt("index");
                String text = choice.optString("text");
                availableChoices.add(index + ". " + text);
            }
        }

        if (listener != null) {
            listener.onChoicesReady(description, availableChoices);
        }
    }

    public void handleChoice(JSONObject eventJson, int choiceIndex, String characterId) {
        JSONArray choices = eventJson.optJSONArray("choices");

        for (int i = 0; i < choices.length(); i++) {
            JSONObject choice = choices.optJSONObject(i);
            if (choice.optInt("index") == choiceIndex &&
                    (!choice.has("characterId") || choice.optString("characterId").equals(characterId))) {

                String resultText = choice.optString("resultText");
                String outcome = choice.optString("outcome");

                if (listener != null) {
                    listener.onEventResult(resultText, outcome);
                }
                break;
            }
        }
    }

    public JSONObject loadEventFromStream(InputStream inputStream) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
            return new JSONObject(builder.toString());
        } catch (Exception e) {
            Log.e("EventManager", "이벤트 JSON 파일을 불러오는 데 실패했습니다", e);
            return null;
        }
    }
}
