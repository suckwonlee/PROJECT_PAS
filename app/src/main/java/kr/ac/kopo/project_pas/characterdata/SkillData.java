package kr.ac.kopo.project_pas.characterdata;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.ac.kopo.project_pas.characterdata.heroData.HeroCandidateSkillSet;
import kr.ac.kopo.project_pas.characterdata.hunterData.HunterSkillSet;
import kr.ac.kopo.project_pas.characterdata.bardData.BardSkillSet;
import kr.ac.kopo.project_pas.characterdata.clericData.ClericSkillSet;
import kr.ac.kopo.project_pas.characterdata.druidData.DruidSkillSet;
import kr.ac.kopo.project_pas.characterdata.engineerData.EngineerSkillSet;
import kr.ac.kopo.project_pas.characterdata.otherworlderData.OtherworlderSkillSet;
import kr.ac.kopo.project_pas.characterdata.wizardData.WizardSkillSet;

/**
 * 하위 패키지 SkillSet 클래스를 통해 각 캐릭터별 스킬 데이터를 제공하는 헬퍼 클래스
 */
public class SkillData {

    // 캐릭터별 공격·방어 스킬 매핑
    private static final Map<String, List<?>> attackSkillsMap = new HashMap<>();
    private static final Map<String, List<?>> defenseSkillsMap = new HashMap<>();

    static {
        // 용사 후보(HERO)
        attackSkillsMap.put("HERO", HeroCandidateSkillSet.getInstance().getAttackSkills());
        defenseSkillsMap.put("HERO", HeroCandidateSkillSet.getInstance().getDefenseSkills());

//        // 사냥꾼(HUNTER)
//        attackSkillsMap.put("HUNTER", HunterSkillSet.getInstance().getAttackSkills());
//        defenseSkillsMap.put("HUNTER", HunterSkillSet.getInstance().getDefenseSkills());
//
//        // 음유시인(BARD)
//        attackSkillsMap.put("BARD", BardSkillSet.getInstance().getAttackSkills());
//        defenseSkillsMap.put("BARD", BardSkillSet.getInstance().getDefenseSkills());
//
//        // 성직자(CLERIC)
//        attackSkillsMap.put("CLERIC", ClericSkillSet.getInstance().getAttackSkills());
//        defenseSkillsMap.put("CLERIC", ClericSkillSet.getInstance().getDefenseSkills());
//
//        // 드루이드(DRUID)
//        attackSkillsMap.put("DRUID", DruidSkillSet.getInstance().getAttackSkills());
//        defenseSkillsMap.put("DRUID", DruidSkillSet.getInstance().getDefenseSkills());
//
//        // 기술자(ENGINEER)
//        attackSkillsMap.put("ENGINEER", EngineerSkillSet.getInstance().getAttackSkills());
//        defenseSkillsMap.put("ENGINEER", EngineerSkillSet.getInstance().getDefenseSkills());
//
//        // 이계종(EXOTIC)
//        attackSkillsMap.put("EXOTIC", OtherworlderSkillSet.getInstance().getAttackSkills());
//        defenseSkillsMap.put("EXOTIC", OtherworlderSkillSet.getInstance().getDefenseSkills());
//
//        // 마법사(WIZARD)
//        attackSkillsMap.put("WIZARD", WizardSkillSet.getInstance().getAttackSkills());
//        defenseSkillsMap.put("WIZARD", WizardSkillSet.getInstance().getDefenseSkills());
    }

    /**
     * 지정된 캐릭터 ID의 공격 스킬 목록을 반환합니다.
     */
    public static List<?> getAttackSkills(String characterId) {
        return attackSkillsMap.getOrDefault(characterId, Collections.emptyList());
    }

    /**
     * 지정된 캐릭터 ID의 방어 스킬 목록을 반환합니다.
     */
    public static List<?> getDefenseSkills(String characterId) {
        return defenseSkillsMap.getOrDefault(characterId, Collections.emptyList());
    }
}
