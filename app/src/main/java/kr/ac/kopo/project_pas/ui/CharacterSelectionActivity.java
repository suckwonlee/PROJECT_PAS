package kr.ac.kopo.project_pas.ui;

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
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import kr.ac.kopo.project_pas.R;
import kr.ac.kopo.project_pas.characterdata.heroData.HeroCandidateData;
import kr.ac.kopo.project_pas.characterdata.heroData.HeroCandidateSkillSet;
import kr.ac.kopo.project_pas.characterdata.heroData.HeroCandidateSkillSet.SkillDefinition;
import kr.ac.kopo.project_pas.runedata.RunePassive;
import kr.ac.kopo.project_pas.runedata.RuneStat;
import kr.ac.kopo.project_pas.save.SaveManager;
import kr.ac.kopo.project_pas.character.RuneEffectApplier;  // 룬 플레이버 텍스트 적용기

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

    // 캐릭터 슬롯 뷰와 ID 배열
    private final int[] iconViewIds = {
            R.id.char1, R.id.char2, R.id.char3, R.id.char4,
            R.id.char5, R.id.char6, R.id.char7, R.id.char8
    };
    private final String[] characterIds = {
            SaveManager.ID.Character.HERO,
            SaveManager.ID.Character.HUNTER,
            SaveManager.ID.Character.CLERIC,
            SaveManager.ID.Character.WIZARD,
            SaveManager.ID.Character.DRUID,
            SaveManager.ID.Character.ENGINEER,
            SaveManager.ID.Character.BARD,
            SaveManager.ID.Character.EXOTIC
    };

    // 룬 목록
    private final String[] passiveRunes = {"화염의 룬","혹한의 룬","수호자의 룬","독사의 룬","투지의 룬","혈귀의 룬","필생의 룬","돌격의 룬"};
    private final String[] statRunes    = {"강격의 룬","부동의 룬","태산의 룬","살의의 룬","잔상의 룬"};

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

        saveManager = SaveManager.getInstance(this);
        infoPanel = findViewById(R.id.panel_info);
        portrait = findViewById(R.id.portrait);
        detailPrompt = findViewById(R.id.detail_prompt);
        detailPlaceholder = findViewById(R.id.detail_placeholder);
        btnAttackSkill  = findViewById(R.id.btn_attack_skill);
        btnDefenseSkill = findViewById(R.id.btn_defense_skill);
        btnRune1        = findViewById(R.id.btn_rune1);
        btnRune2        = findViewById(R.id.btn_rune2);

        // 초기 버튼 및 패널 상태
        btnAttackSkill.setEnabled(false);
        btnDefenseSkill.setEnabled(false);
        btnRune1.setEnabled(false);
        btnRune2.setEnabled(false);
        infoPanel.setVisibility(View.GONE);

        initSlots();
    }

    private void initSlots() {
        for (int i = 0; i < iconViewIds.length; i++) {
            ImageView iconView = findViewById(iconViewIds[i]);
            final String charId = characterIds[i];
            iconView.setImageResource(getIconResId(charId));

            boolean unlocked = saveManager.isCharacterUnlocked(charId)
                    || SaveManager.ID.Character.HERO.equals(charId);
            if (!unlocked) {
                iconView.setColorFilter(
                        new PorterDuffColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY)
                );
            } else {
                iconView.clearColorFilter();
            }

            final int index = i;
            iconView.setOnClickListener(v -> {
                currentCharId = charId;
                infoPanel.setVisibility(View.VISIBLE);
                updateCharacterInfo(index);           // 캐릭터 이름/설명 표시
                loadSkillOptions(charId);
                btnAttackSkill.setEnabled(attackOptions.length > 0);
                btnDefenseSkill.setEnabled(defenseOptions.length > 0);

                // 룬 버튼 활성화 및 초기 UI 갱신
                btnRune1.setEnabled(true);
                btnRune2.setEnabled(true);
                updateRuneButton(true);
                updateRuneButton(false);

                btnAttackSkill.setOnClickListener(view ->
                        showSkillDialog(attackOptions, "공격 스킬 선택")
                );
                btnDefenseSkill.setOnClickListener(view ->
                        showSkillDialog(defenseOptions, "방어 스킬 선택")
                );
                btnRune1.setOnClickListener(view -> showRuneDialog(true));
                btnRune2.setOnClickListener(view -> showRuneDialog(false));
            });
        }
    }

    /** 스킬 선택 다이얼로그 */
    private void showSkillDialog(SkillOption[] options, String title) {
        String[] names = new String[options.length];
        for (int i = 0; i < options.length; i++) names[i] = options[i].name;
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setItems(names, (dialog, which) -> {
                    SkillOption opt = options[which];
                    detailPrompt.setText(opt.name);
                    detailPlaceholder.setText(opt.effectDesc + "\n\n" + opt.flavorText);
                    portrait.setImageResource(opt.iconResId);
                    if (title.contains("공격")) {
                        btnAttackSkill.setImageResource(opt.iconResId);
                        HeroCandidateSkillSet.getInstance()
                                .setSelectedAttack(opt.id, which + 1);
                    } else {
                        btnDefenseSkill.setImageResource(opt.iconResId);
                        HeroCandidateSkillSet.getInstance()
                                .setSelectedDefense(opt.id, which + 1);
                    }
                })
                .show();
    }

    private void showRuneDialog(boolean isPassive) {
        final String[] items = isPassive ? passiveRunes : statRunes;
        new AlertDialog.Builder(this)
                .setTitle(isPassive ? "패시브 룬 선택" : "스탯 룬 선택")
                .setItems(items, (dialog, which) -> {
                    String selected = items[which];
                    int lvl = isPassive
                            ? saveManager.getSelectedPassiveRuneLevel()
                            : saveManager.getSelectedStatRuneLevel();
                    if (lvl <= 0) lvl = 1;

                    if (isPassive) {
                        saveManager.setSelectedPassiveRuneName(selected);
                        saveManager.setSelectedPassiveRuneLevel(lvl);
                        RunePassive rp = new RunePassive(selected, lvl);

                        // 아이콘 및 이름·효과·플레이버 텍스트 표시
                        btnRune1.setImageResource(rp.getIconResId());
                        portrait.setImageResource(rp.getIconResId());
                        detailPrompt.setText(rp.getName() + " Lv." + lvl);
                        String flavor = RuneEffectApplier.applyRune1Passive(null, selected, lvl);
                        detailPlaceholder.setText(
                                "효과: " + rp.getEffectValue() +
                                        "\n\n" + flavor
                        );

                    } else {
                        saveManager.setSelectedStatRuneName(selected);
                        saveManager.setSelectedStatRuneLevel(lvl);
                        RuneStat rs = new RuneStat(selected, lvl);

                        // 아이콘 및 이름·스탯 표시
                        btnRune2.setImageResource(rs.getIconResId());
                        portrait.setImageResource(rs.getIconResId());
                        detailPrompt.setText(rs.getName() + " Lv." + lvl);
                        detailPlaceholder.setText("스탯: " + rs.getTotalValue());
                    }
                })
                .show();
    }

    /** 룬 버튼 아이콘 업데이트 */
    private void updateRuneButton(boolean isPassive) {
        if (isPassive) {
            String name = saveManager.getSelectedPassiveRuneName();
            int lvl = saveManager.getSelectedPassiveRuneLevel();
            if (name != null && !name.isEmpty()) {
                RunePassive rp = new RunePassive(name, lvl);
                btnRune1.setImageResource(rp.getIconResId());
            }
        } else {
            String name = saveManager.getSelectedStatRuneName();
            int lvl = saveManager.getSelectedStatRuneLevel();
            if (name != null && !name.isEmpty()) {
                RuneStat rs = new RuneStat(name, lvl);
                btnRune2.setImageResource(rs.getIconResId());
            }
        }
    }

    /** 캐릭터 스킬 옵션 불러오기 */
    private void loadSkillOptions(String charId) {
        if (!SaveManager.ID.Character.HERO.equals(charId)) {
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
                    def.getId(), def.getName(), def.getEffectDescription(),
                    def.getFlavorText(), def.getIconRes()
            );
        }
        List<SkillDefinition> defDefs = skillSet.getDefenseSkills();
        defenseOptions = new SkillOption[defDefs.size()];
        for (int i = 0; i < defDefs.size(); i++) {
            SkillDefinition def = defDefs.get(i);
            defenseOptions[i] = new SkillOption(
                    def.getId(), def.getName(), def.getEffectDescription(),
                    def.getFlavorText(), def.getIconRes()
            );
        }
    }

    /** 캐릭터 정보 패널 업데이트 */
    private void updateCharacterInfo(int index) {
        String charId = characterIds[index];
        HeroCandidateData data = null;
        if (SaveManager.ID.Character.HERO.equals(charId)) {
            data = new HeroCandidateData();
        }
        detailPrompt.setText(data != null ? data.getName() : "개발중");
        detailPlaceholder.setText(data != null ? data.getDescription() : "아직 개발 중입니다.");
        portrait.setImageResource(getIconResId(charId));
    }

    /** 캐릭터 아이콘 리소스 반환 */
    private int getIconResId(String characterId) {
        switch (characterId) {
            case SaveManager.ID.Character.HERO:    return R.drawable.hero;
            case SaveManager.ID.Character.HUNTER:  return R.drawable.hunter;
            case SaveManager.ID.Character.CLERIC:  return R.drawable.priest;
            case SaveManager.ID.Character.WIZARD:  return R.drawable.wizard;
            case SaveManager.ID.Character.DRUID:   return R.drawable.druid;
            case SaveManager.ID.Character.ENGINEER:return R.drawable.artificer;
            case SaveManager.ID.Character.BARD:    return R.drawable.bard;
            case SaveManager.ID.Character.EXOTIC:  return R.drawable.exotic;
            default: return R.drawable.hero;
        }
    }
}
