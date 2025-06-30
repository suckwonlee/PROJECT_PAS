package kr.ac.kopo.project_pas.publicdata;

import kr.ac.kopo.project_pas.battle.ICombatEntity;

public interface IPassiveSkill {
    String getId();
    String getName();
    String getDescription();
    void applyEffect(ICombatEntity entity); // 전투 전/중 적용
}
