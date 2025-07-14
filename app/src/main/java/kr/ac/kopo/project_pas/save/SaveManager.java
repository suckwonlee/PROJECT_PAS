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

public class SaveManager {
    private static final String SAVE_FILE_NAME = "save.json";
    private final File saveFile;
    private SaveData saveData;
    private final Gson gson = new Gson();

    public SaveManager(Context context) {
        saveFile = new File(context.getFilesDir(), SAVE_FILE_NAME);
        // 버전 업그레이드를 위해 저장 파일 변환
        SaveConverter.convert(saveFile);
        load();
    }

    private void load() {
        if (!saveFile.exists()) {
            saveData = new SaveData();
            save();
            return;
        }
        try (FileReader reader = new FileReader(saveFile)) {
            Type type = new TypeToken<SaveData>() {}.getType();
            saveData = gson.fromJson(reader, type);
        } catch (IOException e) {
            e.printStackTrace();
            saveData = new SaveData();
        }
    }

    public void save() {
        try (FileWriter writer = new FileWriter(saveFile)) {
            gson.toJson(saveData, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isRuneUnlocked(String runeId) {
        RuneInfo info = saveData.runeInfos.get(runeId);
        return info != null && info.unlocked;
    }

    public int getRuneLevel(String runeId) {
        RuneInfo info = saveData.runeInfos.get(runeId);
        return info != null ? info.level : 0;
    }

    public void unlockRune(String runeId) {
        RuneInfo info = saveData.runeInfos.computeIfAbsent(runeId, k -> new RuneInfo());
        info.unlocked = true;
        save();
    }

    public void setRuneLevel(String runeId, int level) {
        RuneInfo info = saveData.runeInfos.computeIfAbsent(runeId, k -> new RuneInfo());
        info.level = level;
        save();
    }

    public boolean isCharacterUnlocked(String characterId) {
        Boolean unlocked = saveData.characterUnlocks.get(characterId);
        return unlocked != null && unlocked;
    }

    public void unlockCharacter(String characterId) {
        saveData.characterUnlocks.put(characterId, true);
        save();
    }

    private static class SaveData {
        Map<String, RuneInfo> runeInfos = new HashMap<>();
        Map<String, Boolean> characterUnlocks = new HashMap<>();
    }

    private static class RuneInfo {
        boolean unlocked = false;
        int level = 0;
    }
}
