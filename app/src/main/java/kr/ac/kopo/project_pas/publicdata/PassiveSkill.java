package kr.ac.kopo.project_pas.publicdata;

import java.util.HashMap;
import java.util.Map;

public class PassiveSkill {
    private String name;
    private int level;

    public PassiveSkill(String name, int level) {
        this.name = name;
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public class PassiveData {
        public static final Map<String, int[]> EFFECT_TABLE = new HashMap<>();

        static {
            EFFECT_TABLE.put("투쟁심", new int[]{1, 2, 4, 7, 10});
            EFFECT_TABLE.put("흡혈", new int[]{3, 5, 8, 12, 20});
            EFFECT_TABLE.put("생존", new int[]{4, 10, 20, 40, 70});
            EFFECT_TABLE.put("침독", new int[]{1, 1, 2, 3, 4});
            EFFECT_TABLE.put("출격", new int[]{10, 20, 40, 70, 100});
            EFFECT_TABLE.put("방화", new int[]{5, 7, 10, 18, 25});
            EFFECT_TABLE.put("중갑", new int[]{2, 4, 8, 13, 20});
            EFFECT_TABLE.put("의지", new int[]{2, 5, 8, 15, 25});
        }

        public static int getEffectValue(String passiveName, int level) {
            if (!EFFECT_TABLE.containsKey(passiveName)) return 0;
            int[] levels = EFFECT_TABLE.get(passiveName);
            int index = Math.max(0, Math.min(level - 1, levels.length - 1));
            return levels[index];
        }
    }
    }

