// RunePassive.java
package kr.ac.kopo.project_pas.runedata;

import static kr.ac.kopo.project_pas.characterdata.SkillData.runeMap;

import kr.ac.kopo.project_pas.publicdata.PassiveSkill;
import kr.ac.kopo.project_pas.save.SaveManager;
import kr.ac.kopo.project_pas.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 패시브 룬 모델 클래스
 *  - 기존 복잡한 매핑 로직 제거
 *  - 룬 이름(name) 기준으로 아이콘 매핑만 추가
 */
public class RunePassive {
    private final String name;      // 룬 이름 (예: "화염의 룬")
    private final int level;
    private final int effectValue;
    private final int iconResId;

    // 룬 이름 -> 아이콘 리소스 매핑
    private static final Map<String, Integer> ICON_TABLE = new HashMap<>();
    static {
        ICON_TABLE.put("화염의 룬", R.drawable.rune_p_fire);
        ICON_TABLE.put("혹한의 룬", R.drawable.rune_p_frost);
        ICON_TABLE.put("수호자의 룬", R.drawable.rune_p_guardian);
        ICON_TABLE.put("독사의 룬", R.drawable.rune_p_venom);
        ICON_TABLE.put("투지의 룬", R.drawable.rune_p_fighting);
        ICON_TABLE.put("혈귀의 룬", R.drawable.rune_p_vempire);
        ICON_TABLE.put("필생의 룬", R.drawable.rune_p_mustlive);
        ICON_TABLE.put("돌격의 룬", R.drawable.rune_p_charge);
        ICON_TABLE.putIfAbsent("default", R.drawable.placeholder_rune1);
    }

    /**
     * @param name 룬 이름
     * @param level 룬 레벨
     */
    public RunePassive(String name, int level) {
        this.name = name;
        this.level = level;
        // PassiveSkill에서 동일 이름으로 바로 조회
        this.effectValue = PassiveSkill.PassiveData.getEffectValue(name, level);
        this.iconResId = ICON_TABLE.getOrDefault(name, ICON_TABLE.get("default"));
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public int getEffectValue() {
        return effectValue;
    }

    /**
     * 룬의 아이콘 리소스 ID 반환
     */
    public int getIconResId() {
        return iconResId;
    }

    @Override
    public String toString() {
        return String.format("%s Lv.%d → 효과치: %d", name, level, effectValue);
    }
    public static List<String> getUnlockedRuneNames() {
        List<String> unlocked = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : SaveManager.getUnlockedRunes().passive.entrySet()) {
            unlocked.add(entry.getKey());
        }
        return unlocked;
    }

    public static int getIconResByName(String runeName) {
        return runeMap.get(runeName).iconRes;
    }


}