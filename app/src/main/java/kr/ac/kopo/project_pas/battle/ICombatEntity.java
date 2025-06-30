package kr.ac.kopo.project_pas.battle;

public interface ICombatEntity {
    int getCurrentHP();
    void takeDamage(int amount);
    void applyStatusEffect(String statusId);
    // 필요에 따라 추가 가능
}
