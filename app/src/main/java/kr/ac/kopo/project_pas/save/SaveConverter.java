package kr.ac.kopo.project_pas.save;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class SaveConverter {
    private static final int CURRENT_VERSION = 1;
    private static final String KEY_VERSION = "version";

    public static void convert(File saveFile) {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(saveFile)) {
            JsonElement element = JsonParser.parseReader(reader);
            JsonObject root = element.isJsonObject() ? element.getAsJsonObject() : new JsonObject();
            int version = root.has(KEY_VERSION) ? root.get(KEY_VERSION).getAsInt() : 0;
            if (version < CURRENT_VERSION) {
                root.addProperty(KEY_VERSION, CURRENT_VERSION);
                try (FileWriter writer = new FileWriter(saveFile)) {
                    gson.toJson(root, writer);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static class ID {
        public static class PassiveRunes {
            public static final String FIRE          = "화염";
            public static final String FROST         = "혹한";
            public static final String GUARDIAN      = "수호자";
            public static final String VIPER         = "독사";
            public static final String RESOLVE       = "투지";
            public static final String SPECTER       = "혈귀";
            public static final String DETERMINATION = "필생";
            public static final String CHARGE        = "돌격";
        }

        public static class StatRunes {
            public static final String STRIKE     = "강격";
            public static final String IMMOBILE   = "부동";
            public static final String MOUNTAIN   = "태산";
            public static final String MALICE     = "살의";
            public static final String AFTERIMAGE = "잔상";
        }

        public static class Character {
            public static final String HERO   = "용사후보";
            public static final String BARD   = "음유시인";
            public static final String CLERIC = "성직자";
            public static final String DRUID  = "드루이드";
            public static final String ENGINEER = "기술자";
            public static final String HUNTER   = "사냥꾼";
            public static final String EXOTIC = "이계종";
            public static final String WIZARD   = "마법사";
        }
    }
}
