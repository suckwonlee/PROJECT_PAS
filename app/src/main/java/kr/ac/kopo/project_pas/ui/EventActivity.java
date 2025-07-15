package kr.ac.kopo.project_pas.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

import java.util.List;

import kr.ac.kopo.project_pas.R;
import kr.ac.kopo.project_pas.event.EventManager;

public class EventActivity extends AppCompatActivity {

    private TextView descriptionText, characterNameText, hpText, progressText;
    private ProgressBar hpBar;
    private ImageView characterImage, eventImage;
    private Button button1, button2, button3;

    private EventManager eventManager = new EventManager();
    private JSONObject currentEvent;

    private String characterId;
    private int currentHp;
    private int maxHp;
    private int chapterIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.non_battle_event);

        // 인텐트로부터 값 전달받기
        Intent intent = getIntent();
        characterId = intent.getStringExtra("characterId");
        currentHp = intent.getIntExtra("currentHp", 100);
        maxHp = intent.getIntExtra("maxHp", 100);
        chapterIndex = intent.getIntExtra("chapterIndex", 1);

        descriptionText = findViewById(R.id.event_description);
        characterNameText = findViewById(R.id.header_character_name);
        hpText = findViewById(R.id.header_hp);
        progressText = findViewById(R.id.header_progress);
        hpBar = findViewById(R.id.header_hp_bar);
        characterImage = findViewById(R.id.header_character_image);
        eventImage = findViewById(R.id.event_image);
        button1 = findViewById(R.id.choice_button_1);
        button2 = findViewById(R.id.choice_button_2);
        button3 = findViewById(R.id.choice_button_3);

        // 상단 정보 바인딩
        // 영어 이름 기준으로 이미지 파일 처리 (예: "Wizard" → R.drawable.wizard)
        String resName = characterId.toLowerCase();
        int imageResId = getResources().getIdentifier(resName, "drawable", getPackageName());
        if (imageResId != 0) {
            characterImage.setImageResource(imageResId);
        } else {
            characterImage.setImageResource(R.drawable.ic_launcher_foreground); // fallback 이미지
        }
        characterNameText.setText(characterId);
        hpText.setText("HP: " + currentHp + " / " + maxHp);
        hpBar.setMax(maxHp);
        hpBar.setProgress(currentHp);
        progressText.setText("1-" + chapterIndex + " / 10");

        eventManager.setEventListener(new EventManager.EventListener() {
            @Override
            public void onChoicesReady(String description, List<String> choices) {
                descriptionText.setText(description);

                button1.setText(choices.get(choices.size() - 1));
                button2.setText(choices.get(choices.size() - 2));
                if (choices.size() == 3) {
                    button3.setText(choices.get(0));
                    button3.setVisibility(Button.VISIBLE);
                } else {
                    button3.setVisibility(Button.GONE);
                }
            }

            @Override
            public void onEventResult(String resultText, String outcome) {
                Toast.makeText(EventActivity.this, resultText, Toast.LENGTH_LONG).show();
                // TODO: outcome 값에 따라 실제 효과 처리
            }
        });

        currentEvent = eventManager.loadEventById(this, "church");
        if (currentEvent != null) {
            eventManager.runEvent(currentEvent, characterId);
        }

        button1.setOnClickListener(v -> eventManager.handleChoice(currentEvent, 1, characterId));
        button2.setOnClickListener(v -> eventManager.handleChoice(currentEvent, 2, characterId));
        button3.setOnClickListener(v -> eventManager.handleChoice(currentEvent, 3, characterId));
    }
}
