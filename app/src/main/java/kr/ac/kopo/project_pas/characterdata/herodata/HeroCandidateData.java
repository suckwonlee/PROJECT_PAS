package kr.ac.kopo.project_pas.characterdata.herodata;

import kr.ac.kopo.project_pas.characterdata.IPlayerData;

/**
 * HeroCandidateData는 용사후보 캐릭터의 데이터를 보유하며,
 * 외부에서는 getter/setter를 통해 접근할 수 있습니다.
 */
public class HeroCandidateData implements IPlayerData {
    private String id = "hero";
    private String name = "용사후보";
    private String description = "추상적이었기에 수많았던 용사 후보 중 한 명. 자신이 용사라 믿고 세상의 구원을 위해 일어섰다.";
    private int baseHp = 170;
    private int baseAtk = 13;
    private int baseDef = 14;
    private int baseCrit = 7;
    private int dodge = 3;

    private String armorName = "철제 갑옷";
    private String armorDesc = "3년연속 풍년이라 살았지. 덕분에 저축을 열심히 해서 맞췄으니까 말이야";
    private String armorPassive = "fortitude";

    @Override
    public String getId() {
        return id;
    }
    public void setId(String id) { this.id = id; }

    @Override
    public String getName() {
        return name;
    }
    public void setName(String name) { this.name = name; }

    @Override
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) { this.description = description; }

    @Override
    public int getBaseHP() {
        return baseHp;
    }
    public void setBaseHP(int baseHp) { this.baseHp = baseHp; }

    @Override
    public int getBaseATK() {
        return baseAtk;
    }
    public void setBaseATK(int baseAtk) { this.baseAtk = baseAtk; }

    @Override
    public int getBaseDEF() {
        return baseDef;
    }
    public void setBaseDEF(int baseDef) { this.baseDef = baseDef; }

    @Override
    public int getBaseCRIT() {
        return baseCrit;
    }
    public void setBaseCRIT(int baseCrit) { this.baseCrit = baseCrit; }

    @Override
    public int getDodge() {
        return dodge;
    }
    public void setDodge(int dodge) { this.dodge = dodge; }

    @Override
    public String getArmorName() {
        return armorName;
    }
    public void setArmorName(String armorName) { this.armorName = armorName; }

    @Override
    public String getArmorDescription() {
        return armorDesc;
    }
    public void setArmorDescription(String armorDesc) { this.armorDesc = armorDesc; }

    @Override
    public String getArmorPassiveId() {
        return armorPassive;
    }
    public void setArmorPassive(String armorPassive) { this.armorPassive = armorPassive; }
}
