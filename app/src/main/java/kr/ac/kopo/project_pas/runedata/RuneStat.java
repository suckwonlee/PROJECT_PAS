package kr.ac.kopo.project_pas.runedata;

import java.util.HashMap;
import java.util.Map;
import kr.ac.kopo.project_pas.R;

/**
 * 스탯 룬 모델 클래스
 */
public class RuneStat {
    private final String name;
    private final int level;
    private final int baseValue;
    private final int growthPerLevel;
    private final int iconResId;

    // 룬 이름별 기본값 및 성장치 테이블
    private static final Map<String, int[]> STAT_TABLE = new HashMap<>();
    // 룬 이름별 아이콘 매핑 테이블
    private static final Map<String, Integer> ICON_TABLE = new HashMap<>();
    static {
        STAT_TABLE.put("강격의 룬", new int[]{2, 2});
        STAT_TABLE.put("부동의 룬", new int[]{1, 1});
        STAT_TABLE.put("태산의 룬", new int[]{20, 20});
        STAT_TABLE.put("살의의 룬", new int[]{3, 3});
        STAT_TABLE.put("잔상의 룬", new int[]{1, 1});

        ICON_TABLE.put("강격의 룬", R.drawable.rune_s_smash);
        ICON_TABLE.put("부동의 룬", R.drawable.rune_s_notmove);
        ICON_TABLE.put("태산의 룬", R.drawable.rune_s_mountain);
        ICON_TABLE.put("살의의 룬", R.drawable.rune_s_fleshy);
        ICON_TABLE.put("잔상의 룬", R.drawable.rune_s_spectrum);
        ICON_TABLE.putIfAbsent("default", R.drawable.placeholder_rune2);
    }

    public RuneStat(String name, int level) {
        this.name = name;
        this.level = level;
        int[] data = STAT_TABLE.getOrDefault(name, new int[]{0, 0});
        this.baseValue = data[0];
        this.growthPerLevel = data[1];
        this.iconResId = ICON_TABLE.getOrDefault(name, ICON_TABLE.get("default"));
    }

    public String getName() { return name; }
    public int getLevel() { return level; }
    public int getTotalValue() { return baseValue + growthPerLevel * (level - 1); }
    public int getIconResId() { return iconResId; }

    @Override
    public String toString() {
        return String.format("%s Lv.%d → 스탯: %d", name, level, getTotalValue());
    }
}