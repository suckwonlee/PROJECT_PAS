package kr.ac.kopo.project_pas.battle;

public interface IBattleEntity {
    void takeFireDamage(int amount);
    int getCurrentBurnDamage();
    void setCurrentBurnDamage(int amount);
    boolean isBurnedThisTurn();
    void setBurnedThisTurn(boolean value);
    void reduceHp(int amount);
}
