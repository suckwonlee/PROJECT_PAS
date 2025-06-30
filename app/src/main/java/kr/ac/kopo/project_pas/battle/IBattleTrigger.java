package kr.ac.kopo.project_pas.battle;

interface IBattleTrigger {
    boolean shouldTrigger(int turnCount, ICombatEntity self); // 특정 턴마다, 상태 조건 등 평가
    void applyEffect(ICombatEntity self); // 효과 적용 (예: 회복, 부활 등)

    default boolean triggersOnDeath() {
        return false; // 부활 등 특수한 사망 트리거 여부
    }
}
