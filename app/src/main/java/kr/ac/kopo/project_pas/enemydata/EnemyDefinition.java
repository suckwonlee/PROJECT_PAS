package kr.ac.kopo.project_pas.enemydata;

public class EnemyDefinition {
    private String name;
    private int hp;
    private boolean poisoned;

    public EnemyDefinition(String name, int hp) {
        this.name = name;
        this.hp = hp;
        this.poisoned = false;
    }

    public void applyPoison(int amount) {
        this.poisoned = true;
        System.out.println(name + " 에게 중독 " + amount + " 적용됨");
    }

    public void takeFireDamage(int amount) {
        this.hp -= amount;
        System.out.println(name + " 이 " + amount + "의 화염 피해를 입음");
    }

    public void reduceHp(int amount) {
        this.hp -= amount;
        System.out.println(name + " 이 " + amount + "의 피해를 입음");
    }

    public boolean isDead() {
        return this.hp <= 0;
    }

    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }
}
