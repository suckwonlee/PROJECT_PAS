package kr.ac.kopo.project_pas.save;

import android.content.Context;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * 게임 데이터 저장 및 로드 매니저
 */
public class SaveManager {
    private static final String SAVE_FILE_NAME = "save.json";
    public static SaveManager instance;

    // -------------------- ID 정의 --------------------
    public static class ID {
        public static class Character {
            public static final String HERO      = "hero";
            public static final String HUNTER    = "hunter";
            public static final String CLERIC    = "cleric";
            public static final String WIZARD    = "wizard";
            public static final String DRUID     = "druid";
            public static final String ENGINEER = "engineer";
            public static final String BARD      = "bard";
            public static final String EXOTIC    = "exotic";
        }
    }

    private final File saveFile;
    private SaveData saveData;
    private final Gson gson = new Gson();

    public SaveManager(Context context) {
        this.saveFile = new File(context.getFilesDir(), SAVE_FILE_NAME);
        load();
    }

    public static SaveManager getInstance(Context context) {
        if (instance == null) {
            instance = new SaveManager(context);
        }
        return instance;
    }

    /**
     * 데이터 로드 (파일이 없으면 초기화 후 저장)
     */
    private void load() {
        if (saveFile.exists()) {
            try (FileReader reader = new FileReader(saveFile)) {
                Type type = new TypeToken<SaveData>() {}.getType();
                saveData = gson.fromJson(reader, type);
            } catch (IOException e) {
                e.printStackTrace();
                saveData = new SaveData();
            }
        } else {
            saveData = new SaveData();
            save();
        }
    }

    /**
     * 데이터 저장
     */
    private void save() {
        try (FileWriter writer = new FileWriter(saveFile)) {
            gson.toJson(saveData, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // -------------------- 룬 정보 --------------------

    public boolean isRuneUnlocked(String runeName) {
        RuneInfo info = saveData.runeInfos.get(runeName);
        return info != null && info.unlocked;
    }

    public int getRuneLevel(String runeName) {
        RuneInfo info = saveData.runeInfos.get(runeName);
        return (info != null) ? info.level : 0;
    }

    public void unlockRune(String runeName) {
        RuneInfo info = saveData.runeInfos.computeIfAbsent(runeName, k -> new RuneInfo());
        info.unlocked = true;
        save();
    }

    public void setRuneLevel(String runeName, int level) {
        RuneInfo info = saveData.runeInfos.computeIfAbsent(runeName, k -> new RuneInfo());
        info.level = level;
        save();
    }

    // -------------------- 선택된 룬 --------------------

    public String getSelectedPassiveRuneName() {
        return saveData.selectedPassiveRuneName;
    }

    public void setSelectedPassiveRuneName(String runeName) {
        saveData.selectedPassiveRuneName = runeName;
        save();
    }

    public int getSelectedPassiveRuneLevel() {
        return saveData.selectedPassiveRuneLevel;
    }

    public void setSelectedPassiveRuneLevel(int level) {
        saveData.selectedPassiveRuneLevel = level;
        save();
    }

    public String getSelectedStatRuneName() {
        return saveData.selectedStatRuneName;
    }

    public void setSelectedStatRuneName(String runeName) {
        saveData.selectedStatRuneName = runeName;
        save();
    }

    public int getSelectedStatRuneLevel() {
        return saveData.selectedStatRuneLevel;
    }

    public void setSelectedStatRuneLevel(int level) {
        saveData.selectedStatRuneLevel = level;
        save();
    }

    // -------------------- 캐릭터 언락 --------------------

    public boolean isCharacterUnlocked(String characterId) {
        Boolean unlocked = saveData.characterUnlocks.get(characterId);
        return unlocked != null && unlocked;
    }

    public void unlockCharacter(String characterId) {
        saveData.characterUnlocks.put(characterId, true);
        save();
    }

    // SaveData inner class
    private static class SaveData {
        Map<String, RuneInfo> runeInfos = new HashMap<>();
        Map<String, Boolean> characterUnlocks = new HashMap<>();

        // 선택된 룬 저장 필드
        String selectedPassiveRuneName = "";
        int selectedPassiveRuneLevel = 0;
        String selectedStatRuneName = "";
        int selectedStatRuneLevel = 0;
    }

    // RuneInfo inner class
    private static class RuneInfo {
        boolean unlocked = false;
        int level = 0;
    }

    public static void setSelectedAttackSkillId(String id) {
        saveJson.getSelectedSkills().attackSkill = id;
        write();
    }

    public static void setSelectedDefenseSkillId(String id) {
        saveJson.getSelectedSkills().defenseSkill = id;
        write();
    }

    public static void setSelectedRune1Name(String name) {
        saveJson.getSelectedRunes().rune1Name = name;
        write();
    }

    public static void setSelectedRune2Name(String name) {
        saveJson.getSelectedRunes().rune2Name = name;
        write();
    }
}
