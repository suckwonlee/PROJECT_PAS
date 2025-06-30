package kr.ac.kopo.project_pas.characterdata.herodata;

import kr.ac.kopo.project_pas.characterdata.IPlayerData;

class HeroCandidateData implements IPlayerData {
    private final String id = "hero";
    private final String name = "용사후보";
    private final String description = "추상적이었기에 수많았던 용사 후보 중 한 명. 자신이 용사라 믿고 세상의 구원을 위해 일어섰다.";
    private final int hp = 170;
    private final int atk = 13;
    private final int def = 14;
    private final int crit = 7;
    private final int dodge = 3;

    private final String armorName = "철제 갑옷";
    private final String armorDesc = "3년연속 풍년이라 살았지. 덕분에 저축을 열심히 해서 맞췄으니까 말이야";
    private final String armorPassive = "heavy_armor_lv2";

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public int getBaseHP() {
        return hp;
    }

    @Override
    public int getBaseATK() {
        return atk;
    }

    @Override
    public int getBaseDEF() {
        return def;
    }

    @Override
    public int getBaseCRIT() {
        return crit;
    }

    @Override
    public int getDodge() {
        return dodge;
    }

    @Override
    public String getArmorName() {
        return armorName;
    }

    @Override
    public String getArmorDescription() {
        return armorDesc;
    }

    @Override
    public String getArmorPassiveId() {
        return armorPassive;
    }
}

