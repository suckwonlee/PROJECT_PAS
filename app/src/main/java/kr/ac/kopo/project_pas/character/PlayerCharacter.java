package kr.ac.kopo.project_pas.character;

import java.util.ArrayList;
import java.util.List;
import kr.ac.kopo.project_pas.publicdata.PassiveSkill;

public class PlayerCharacter {
    private int hp;
    private int atk;
    private int def;
    private double crit;
    private double evasion; // ⬅️ 회피율 추가
    private String name;
    private List<PassiveSkill> passives;

    public PlayerCharacter(String name, int hp, int atk, int def, double crit, double evasion) {
        this.name = name;
        this.hp = hp;
        this.atk = atk;
        this.def = def;
        this.crit = crit;
        this.evasion = evasion;
        this.passives = new ArrayList<>();
    }

    public void addPassive(PassiveSkill passive) {
        passives.add(passive);
    }

    public List<PassiveSkill> getPassives() {
        return passives;
    }

    public void increaseAtk(int amount) {
        atk += amount;
    }

    public void increaseDef(int amount) {
        def += amount;
    }

    public void increaseHp(int amount) {
        hp += amount;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getAtk() {
        return atk;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    public double getCrit() {
        return crit;
    }

    public void setCrit(double crit) {
        this.crit = crit;
    }

    public int getDef() {
        return def;
    }

    public void setDef(int def) {
        this.def = def;
    }

    public double getEvasion() {
        return evasion;
    }

    public void setEvasion(double evasion) {
        this.evasion = evasion;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassives(List<PassiveSkill> passives) {
        this.passives = passives;
    }

    public void increaseCrit(double amount) {
        crit += amount;
    }

    public void increaseEvasion(double amount) {
        evasion += amount;
    }
}
