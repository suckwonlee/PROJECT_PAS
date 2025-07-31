package kr.ac.kopo.project_pas.ui;

import java.util.List;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import kr.ac.kopo.project_pas.R;
import kr.ac.kopo.project_pas.character.PlayerCharacter;
import kr.ac.kopo.project_pas.characterdata.CharacterDataRegistry;
import kr.ac.kopo.project_pas.characterdata.CharacterDataRegistry.CharacterInfo;
import kr.ac.kopo.project_pas.characterdata.IPlayerData;
import kr.ac.kopo.project_pas.characterdata.heroData.HeroCandidateData;
import kr.ac.kopo.project_pas.characterdata.heroData.HeroCandidateSkillSet;
import kr.ac.kopo.project_pas.characterdata.heroData.HeroCandidateSkillSet.SkillDefinition;
import kr.ac.kopo.project_pas.runedata.RunePassive;
import kr.ac.kopo.project_pas.runedata.RuneStat;
import kr.ac.kopo.project_pas.save.SaveConverter;
import kr.ac.kopo.project_pas.save.SaveManager;
import kr.ac.kopo.project_pas.character.RuneEffectApplier;

public class CharacterSelectionActivity extends AppCompatActivity {
    private LinearLayout infoPanel;
    private ImageView portrait;
    private TextView detailPrompt;
    private TextView detailPlaceholder;
    private ImageButton btnAttackSkill;
    private ImageButton btnDefenseSkill;
    private ImageButton btnRune1;
    private ImageButton btnRune2;

    private PlayerCharacter currentCharacter;
    private SkillOption[] attackOptions;
    private SkillOption[] defenseOptions;

    private static class SkillOption {
        final String id, name, effectDesc, flavorText;
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

        infoPanel = findViewById(R.id.panel_info);
        portrait = findViewById(R.id.portrait);
        detailPrompt = findViewById(R.id.detail_prompt);
        detailPlaceholder = findViewById(R.id.detail_placeholder);
        btnAttackSkill = findViewById(R.id.btn_attack_skill);
        btnDefenseSkill = findViewById(R.id.btn_defense_skill);
        btnRune1 = findViewById(R.id.btn_rune1);
        btnRune2 = findViewById(R.id.btn_rune2);

        String charId = SaveConverter.ID.Character.HERO;
        CharacterInfo info = CharacterDataRegistry.getCharacterById(charId);

        IPlayerData data;
        switch (charId) {
            case "HERO":
                data = new HeroCandidateData();
                break;
            default:
                throw new IllegalArgumentException("지원하지 않는 캐릭터 ID: " + charId);
        }

        List<SkillDefinition> atkDefs = HeroCandidateSkillSet.getAttackSkillOptions();
        List<SkillDefinition> defDefs = HeroCandidateSkillSet.getDefenseSkillOptions();

        attackOptions = new SkillOption[atkDefs.size()];
        defenseOptions = new SkillOption[defDefs.size()];

        for (int i = 0; i < atkDefs.size(); i++) {
            SkillDefinition s = atkDefs.get(i);
            attackOptions[i] = new SkillOption(s.getId(), s.getName(), s.getEffectText(), s.getFlavorText(), s.getIconRes());
        }
        for (int i = 0; i < defDefs.size(); i++) {
            SkillDefinition s = defDefs.get(i);
            defenseOptions[i] = new SkillOption(s.getId(), s.getName(), s.getEffectText(), s.getFlavorText(), s.getIconRes());
        }

        currentCharacter = new PlayerCharacter(
                data.getName(),
                data.getBaseHP(),
                data.getBaseATK(),
                data.getBaseDEF(),
                data.getBaseCRIT(),
                data.getDodge()
        );

        btnAttackSkill.setOnClickListener(view -> showSkillDialog(attackOptions, "공격 스킬 선택"));
        btnDefenseSkill.setOnClickListener(view -> showSkillDialog(defenseOptions, "방어 스킬 선택"));
        btnRune1.setOnClickListener(view -> showRuneDialog(true));
        btnRune2.setOnClickListener(view -> showRuneDialog(false));
    }

    private void showSkillDialog(SkillOption[] options, String title) {
        String[] items = new String[options.length];
        for (int i = 0; i < options.length; i++) {
            items[i] = options[i].name + " - " + options[i].effectDesc;
        }
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setItems(items, (dialog, which) -> {
                    SkillOption selected = options[which];
                    if ("공격 스킬 선택".equals(title)) {
                        SaveManager.setSelectedAttackSkillId(selected.id);
                        btnAttackSkill.setImageResource(selected.iconResId);
                    } else {
                        SaveManager.setSelectedDefenseSkillId(selected.id);
                        btnDefenseSkill.setImageResource(selected.iconResId);
                    }
                    detailPrompt.setText(selected.name);
                    detailPlaceholder.setText(
                            selected.effectDesc + "\n\n" + selected.flavorText
                    );
                })
                .show();
    }

    private void showRuneDialog(boolean isRune1) {
        String[] runes = isRune1
                ? RunePassive.getUnlockedRuneNames().toArray(new String[0])
                : RuneStat.getUnlockedRuneNames().toArray(new String[0]);

        new AlertDialog.Builder(this)
                .setTitle(isRune1 ? "패시브 룬 선택" : "스탯 룬 선택")
                .setItems(runes, (dialog, which) -> {
                    String selected = runes[which];
                    detailPrompt.setText(selected);

                    if (isRune1) {
                        SaveManager.setSelectedRune1Name(selected);
                        btnRune1.setImageResource(RunePassive.getIconResByName(selected));
                        String desc = RuneEffectApplier.applyRune1Passive(
                                new PlayerCharacter("dummy", 0, 0, 0, 0, 0), selected, 1);
                        detailPlaceholder.setText(desc);
                    } else {
                        SaveManager.setSelectedRune2Name(selected);
                        btnRune2.setImageResource(RuneStat.getIconResByName(selected));
                        String desc = RuneEffectApplier.applyRune2Stat(
                                new PlayerCharacter("dummy", 0, 0, 0, 0, 0), selected, 1);
                        detailPlaceholder.setText(desc);
                    }
                })
                .show();
    }
}
