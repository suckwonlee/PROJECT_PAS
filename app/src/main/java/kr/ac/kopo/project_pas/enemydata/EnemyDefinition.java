package kr.ac.kopo.project_pas.enemydata;

import kr.ac.kopo.project_pas.character.CombatUnit;
import kr.ac.kopo.project_pas.tag.TagInstance;

import java.util.ArrayList;
import java.util.List;

public class EnemyDefinition implements CombatUnit {
    private String name;
    private int hp;
    private boolean poisoned;
    private List<TagInstance> tags;

    public EnemyDefinition(String name, int hp) {
        this.name = name;
        this.hp = hp;
        this.poisoned = false;
        this.tags = new ArrayList<>();
    }

    public void applyPoison(int amount) {
        this.poisoned = true;
    }

    public void takeFireDamage(int amount) {
        this.hp -= amount;
    }

    public void reduceHp(int amount) {
        this.hp -= amount;
    }

    public boolean isDead() {
        return this.hp <= 0;
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
        reduceHp(amount);
    }

    @Override
    public void heal(int amount) {
        this.hp += amount;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getHp() {
        return hp;
    }
}
