package kr.ac.kopo.project_pas.ui;

import androidx.appcompat.app.AlertDialog;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;
import kr.ac.kopo.project_pas.R;
import kr.ac.kopo.project_pas.characterdata.heroData.HeroCandidateData;
import kr.ac.kopo.project_pas.characterdata.heroData.HeroCandidateSkillSet;
import kr.ac.kopo.project_pas.characterdata.heroData.HeroCandidateSkillSet.SkillDefinition;
import kr.ac.kopo.project_pas.save.SaveConverter;
import kr.ac.kopo.project_pas.save.SaveManager;

public class CharacterSelectionActivity extends AppCompatActivity {
    private SaveManager saveManager;
    private LinearLayout infoPanel;
    private ImageView portrait;
    private TextView detailPrompt;
    private TextView detailPlaceholder;
    private ImageButton btnAttackSkill;
    private ImageButton btnDefenseSkill;
    private ImageButton btnRune1;
    private ImageButton btnRune2;

    private SkillOption[] attackOptions;
    private SkillOption[] defenseOptions;
    private String currentCharId;

    private final int[] iconViewIds = {
            R.id.char1, R.id.char2, R.id.char3, R.id.char4,
            R.id.char5, R.id.char6, R.id.char7, R.id.char8
    };
    private final String[] characterIds = {
            SaveConverter.ID.Character.HERO,
            SaveConverter.ID.Character.HUNTER,
            SaveConverter.ID.Character.CLERIC,
            SaveConverter.ID.Character.WIZARD,
            SaveConverter.ID.Character.DRUID,
            SaveConverter.ID.Character.ENGINEER,
            SaveConverter.ID.Character.BARD,
            SaveConverter.ID.Character.EXOTIC
    };

    /**
     * SkillOption: 스킬 ID, 이름, 효과 설명, 플레이버 텍스트, 아이콘을 보관
     */
    private static class SkillOption {
        final String id;
        final String name;
        final String effectDesc;
        final String flavorText;
        final int iconResId;
        SkillOption(String id, String name, String effectDesc, String flavorText, int iconResId) {
            this.id = id;
            this.name = name;
            this.effectDesc = effectDesc;
            this.flavorText = flavorText;
            this.iconResId = iconResId;
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_selection);

        saveManager = new SaveManager(this);
        infoPanel = findViewById(R.id.panel_info);
        portrait = findViewById(R.id.portrait);
        detailPrompt = findViewById(R.id.detail_prompt);
        detailPlaceholder = findViewById(R.id.detail_placeholder);
        btnAttackSkill  = findViewById(R.id.btn_attack_skill);
        btnDefenseSkill = findViewById(R.id.btn_defense_skill);
        btnRune1        = findViewById(R.id.btn_rune1);
        btnRune2        = findViewById(R.id.btn_rune2);

        // 초기 버튼 비활성화
        btnAttackSkill.setEnabled(false);
        btnDefenseSkill.setEnabled(false);
        btnRune1.setEnabled(false);
        btnRune2.setEnabled(false);

        initSlots();
        infoPanel.setVisibility(View.GONE);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void initSlots() {
        for (int i = 0; i < iconViewIds.length; i++) {
            ImageView iconView = findViewById(iconViewIds[i]);
            final String charId = characterIds[i];

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
                currentCharId = charId;
                infoPanel.setVisibility(View.VISIBLE);
                updateCharacterInfo(index);
                loadSkillOptions(charId);
                btnAttackSkill.setEnabled(attackOptions.length > 0);
                btnDefenseSkill.setEnabled(defenseOptions.length > 0);
                btnRune1.setEnabled(true);
                btnRune2.setEnabled(true);

                btnAttackSkill.setOnClickListener(view -> showSkillDialog(attackOptions, "공격 스킬 선택"));
                btnDefenseSkill.setOnClickListener(view -> showSkillDialog(defenseOptions, "방어 스킬 선택"));
            });
        }
    }

    private void showSkillDialog(SkillOption[] options, String title) {
        String[] names = new String[options.length];
        for (int i = 0; i < options.length; i++) names[i] = options[i].name;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setItems(names, (dialog, which) -> {
            SkillOption opt = options[which];
            detailPrompt.setText(opt.name);
            // 스킬 효과 설명과 플레이버 텍스트를 2줄 띄워 함께 설정
            detailPlaceholder.setText(opt.effectDesc + "\n\n" + opt.flavorText);
            portrait.setImageResource(opt.iconResId);
            if (title.contains("공격")) {
                btnAttackSkill.setImageResource(opt.iconResId);
                HeroCandidateSkillSet.getInstance().setSelectedAttack(opt.id, which + 1);
            } else {
                btnDefenseSkill.setImageResource(opt.iconResId);
                HeroCandidateSkillSet.getInstance().setSelectedDefense(opt.id, which + 1);
            }
        });
        builder.show();
    }

    private void loadSkillOptions(String charId) {
        if (!SaveConverter.ID.Character.HERO.equals(charId)) {
            attackOptions = new SkillOption[0];
            defenseOptions = new SkillOption[0];
            return;
        }
        HeroCandidateSkillSet skillSet = HeroCandidateSkillSet.getInstance();
        List<SkillDefinition> atkDefs = skillSet.getAttackSkills();
        attackOptions = new SkillOption[atkDefs.size()];
        for (int i = 0; i < atkDefs.size(); i++) {
            SkillDefinition def = atkDefs.get(i);
            attackOptions[i] = new SkillOption(
                    def.getId(),
                    def.getName(),
                    def.getEffectDescription(),
                    def.getFlavorText(),
                    def.getIconRes()
            );
        }
        List<SkillDefinition> defDefs = skillSet.getDefenseSkills();
        defenseOptions = new SkillOption[defDefs.size()];
        for (int i = 0; i < defDefs.size(); i++) {
            SkillDefinition def = defDefs.get(i);
            defenseOptions[i] = new SkillOption(
                    def.getId(),
                    def.getName(),
                    def.getEffectDescription(),
                    def.getFlavorText(),
                    def.getIconRes()
            );
        }
    }

    private void updateCharacterInfo(int index) {
        String charId = characterIds[index];
        HeroCandidateData data = null;
        if (SaveConverter.ID.Character.HERO.equals(charId)) {
            data = new HeroCandidateData();
        }
        detailPrompt.setText(data != null ? data.getName() : "개발중");
        detailPlaceholder.setText(data != null ? data.getDescription() : "아직 개발 중입니다.");
        portrait.setImageResource(getIconResId(charId));
    }

    private int getIconResId(String characterId) {
        switch (characterId) {
            case SaveConverter.ID.Character.HERO:    return R.drawable.hero;
            case SaveConverter.ID.Character.HUNTER:  return R.drawable.hunter;
            case SaveConverter.ID.Character.CLERIC:  return R.drawable.priest;
            case SaveConverter.ID.Character.WIZARD:  return R.drawable.wizard;
            case SaveConverter.ID.Character.DRUID:   return R.drawable.druid;
            case SaveConverter.ID.Character.ENGINEER:return R.drawable.artificer;
            case SaveConverter.ID.Character.BARD:    return R.drawable.bard;
            case SaveConverter.ID.Character.EXOTIC:  return R.drawable.exotic;
            default: return R.drawable.hero;
        }
    }
}
