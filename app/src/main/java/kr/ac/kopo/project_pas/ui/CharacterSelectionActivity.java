package kr.ac.kopo.project_pas.ui;

import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import kr.ac.kopo.project_pas.R;
import kr.ac.kopo.project_pas.characterdata.herodata.HeroCandidateData;
import kr.ac.kopo.project_pas.save.SaveConverter;
import kr.ac.kopo.project_pas.save.SaveManager;

public class CharacterSelectionActivity extends AppCompatActivity {
    private SaveManager saveManager;
    private LinearLayout infoPanel;
    private ImageView portrait;
    private TextView detailPrompt;
    private TextView detailPlaceholder;

    private final int[] containerIds = {
            R.id.char1_container,
            R.id.char2_container,
            R.id.char3_container,
            R.id.char4_container,
            R.id.char5_container,
            R.id.char6_container,
            R.id.char7_container,
            R.id.char8_container
    };
    private final int[] iconViewIds = {
            R.id.char1,
            R.id.char2,
            R.id.char3,
            R.id.char4,
            R.id.char5,
            R.id.char6,
            R.id.char7,
            R.id.char8
    };
    private final String[] characterIds = {
            SaveConverter.ID.Character.HERO,
            SaveConverter.ID.Character.HUNTER,
            SaveConverter.ID.Character.CLERIC,
            SaveConverter.ID.Character.WIZARD,
            SaveConverter.ID.Character.DRUID,
            // 여섯번째 슬롯: 기술자(Engineer)
            SaveConverter.ID.Character.ENGINEER,
            // 일곱번째 슬롯: 음유시인(Bard)
            SaveConverter.ID.Character.BARD,
            // 마지막 슬롯: 이계종(Exotic)
            SaveConverter.ID.Character.EXOTIC
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_selection);

        saveManager = new SaveManager(this);
        infoPanel = findViewById(R.id.panel_info);
        portrait = findViewById(R.id.portrait);
        detailPrompt = findViewById(R.id.detail_prompt);
        detailPlaceholder = findViewById(R.id.detail_placeholder);

        initSlots();
        infoPanel.setVisibility(View.GONE);
    }

    // 뒤로(취소) 버튼 누르면 대륙 선택 창으로 돌아가기
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void initSlots() {
        for (int i = 0; i < iconViewIds.length; i++) {
            ImageView iconView = findViewById(iconViewIds[i]);
            String charId = characterIds[i];

            iconView.setImageResource(getIconResId(charId));
            boolean unlocked = saveManager.isCharacterUnlocked(charId)
                    || SaveConverter.ID.Character.HERO.equals(charId);
            if (!unlocked) {
                iconView.setColorFilter(new PorterDuffColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY));
            } else {
                iconView.clearColorFilter();
            }

            final int index = i;
            iconView.setOnClickListener(v -> {
                infoPanel.setVisibility(View.VISIBLE);
                updateCharacterInfo(index);
            });
        }
    }

    private void updateCharacterInfo(int index) {
        String charId = characterIds[index];
        String name;
        String description;

        if (SaveConverter.ID.Character.HERO.equals(charId)) {
            HeroCandidateData data = new HeroCandidateData();
            name = data.getName();
            description = data.getDescription();
        } else {
            switch (charId) {
                case SaveConverter.ID.Character.HUNTER:
                    name = "사냥꾼";
                    break;
                case SaveConverter.ID.Character.BARD:
                    name = "음유시인";
                    break;
                case SaveConverter.ID.Character.CLERIC:
                    name = "성직자";
                    break;
                case SaveConverter.ID.Character.DRUID:
                    name = "드루이드";
                    break;
                case SaveConverter.ID.Character.ENGINEER:
                    name = "기술자";
                    break;
                case SaveConverter.ID.Character.EXOTIC:
                    name = "이계종";
                    break;
                case SaveConverter.ID.Character.WIZARD:
                    name = "마법사";
                    break;
                default:
                    name = "개발중";
            }
            description = "아직 개발 중인 캐릭터입니다.";
        }
        portrait.setImageResource(getIconResId(charId));
        detailPrompt.setText(name);
        detailPlaceholder.setText(description);
    }

    private int getIconResId(String characterId) {
        switch (characterId) {
            case SaveConverter.ID.Character.HERO:
                return R.drawable.hero;
            case SaveConverter.ID.Character.BARD:
                return R.drawable.bard;
            case SaveConverter.ID.Character.CLERIC:
                return R.drawable.priest;
            case SaveConverter.ID.Character.DRUID:
                return R.drawable.druid;
            case SaveConverter.ID.Character.ENGINEER:
                // 기술자 아이콘 매핑 수정
                return R.drawable.artificer;
            case SaveConverter.ID.Character.HUNTER:
                return R.drawable.hunter;
            case SaveConverter.ID.Character.EXOTIC:
                // 이계종 아이콘 매핑 수정
                return R.drawable.exotic;
            case SaveConverter.ID.Character.WIZARD:
                return R.drawable.wizard;
            default:
                return R.drawable.icon12;
        }
    }
}
