package kr.ac.kopo.project_pas.characterdata.herodata;

public class HeroCandidateSkillSet {
    private String attackSkill;   // 공격 스킬 이름
    private int attackSkillLevel; // 공격 스킬 등급

    private String defenseSkill;   // 방어 스킬 이름
    private int defenseSkillLevel; // 방어 스킬 등급

    public HeroCandidateSkillSet(String attackSkill, int attackSkillLevel,
                                 String defenseSkill, int defenseSkillLevel) {
        this.attackSkill = attackSkill;
        this.attackSkillLevel = attackSkillLevel;
        this.defenseSkill = defenseSkill;
        this.defenseSkillLevel = defenseSkillLevel;
    }

    public String getAttackSkill() {
        return attackSkill;
    }

    public void setAttackSkill(String attackSkill) {
        this.attackSkill = attackSkill;
    }

    public int getAttackSkillLevel() {
        return attackSkillLevel;
    }

    public void setAttackSkillLevel(int attackSkillLevel) {
        this.attackSkillLevel = attackSkillLevel;
    }

    public String getDefenseSkill() {
        return defenseSkill;
    }

    public void setDefenseSkill(String defenseSkill) {
        this.defenseSkill = defenseSkill;
    }

    public int getDefenseSkillLevel() {
        return defenseSkillLevel;
    }

    public void setDefenseSkillLevel(int defenseSkillLevel) {
        this.defenseSkillLevel = defenseSkillLevel;
    }
}
