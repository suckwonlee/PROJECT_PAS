package kr.ac.kopo.project_pas.tag;

import kr.ac.kopo.project_pas.character.CombatUnit;

/**
 * 태그 효과 처리 인터페이스
 */
public interface TagEffectProcessor {

    /**
     * 태그의 매턴 시작 시 처리 (예: 화염 피해 적용)
     */
    void onTurnStart(CombatUnit unit, TagInstance tag);

    /**
     * 태그 부여 시 즉시 처리 (예: 즉시 회복, 상태 부여 등)
     */
    void onApply(CombatUnit unit, TagInstance tag);

    /**
     * 태그가 제거될 때 처리 (예: 회복 차단 해제 등)
     */
    void onRemove(CombatUnit unit, TagInstance tag);
}
