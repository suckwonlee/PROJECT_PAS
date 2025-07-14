package kr.ac.kopo.project_pas.character;

import java.util.ArrayList;
import java.util.List;
import kr.ac.kopo.project_pas.publicdata.PassiveSkill;
import kr.ac.kopo.project_pas.tag.TagInstance;

public class PlayerCharacter implements CombatUnit {
    private int hp;
    private int atk;
    private int def;
    private double crit;
    private double evasion;
    private String name;
    private List<PassiveSkill> passives;
    private List<TagInstance> tags;

    public PlayerCharacter(String name, int hp, int atk, int def, double crit, double evasion) {
        this.name = name;
        this.hp = hp;
        this.atk = atk;
        this.def = def;
        this.crit = crit;
        this.evasion = evasion;
        this.passives = new ArrayList<>();
        this.tags = new ArrayList<>();
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

    public void increaseCrit(double amount) {
        crit += amount;
    }

    public void increaseEvasion(double amount) {
        evasion += amount;
    }

    public void addTag(TagInstance tag) {
        tags.add(tag);
    }

    public List<TagInstance> getTags() {
        return tags;
    }

    public void removeExpiredTags() {
        tags.removeIf(TagInstance::isExpired);
    }

    @Override
    public void takeDamage(int amount) {
        hp -= amount;
        if (hp < 0) hp = 0;
    }

    @Override
    public void heal(int amount) {
        hp += amount;
    }

    @Override
    public int getHp() {
        return hp;
    }

    @Override
    public String getName() {
        return name;
    }
}
