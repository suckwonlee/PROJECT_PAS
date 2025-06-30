package kr.ac.kopo.project_pas.publicdata;

import kr.ac.kopo.project_pas.battle.ICombatEntity;

public interface IActiveSkill {
    String getId();
    String getName();
    String getDescription();
    int getMaxUses(); // -1이면 무제한 사용
    int getCooldown(); // -1이면 쿨다운 없음
    String getTag();

    void applyToTarget(ICombatEntity user, ICombatEntity target);
}
