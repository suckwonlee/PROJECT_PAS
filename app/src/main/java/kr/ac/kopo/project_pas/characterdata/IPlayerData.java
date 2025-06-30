package kr.ac.kopo.project_pas.characterdata;

public interface IPlayerData {
    String getId();
    String getName();
    String getDescription();
    int getBaseHP();
    int getBaseATK();
    int getBaseDEF();
    int getBaseCRIT();
    int getDodge();

    String getArmorName();
    String getArmorDescription();
    String getArmorPassiveId();
}
