package kr.ac.kopo.project_pas.character;

import java.util.List;

import kr.ac.kopo.project_pas.tag.TagInstance;

/**
 * 아군/적 유닛 공통 인터페이스
 */
public interface CombatUnit {

    /**
     * 피해 적용 (HP 감소)
     */
    void takeDamage(int amount);

    /**
     * 체력 회복
     */
    void heal(int amount);

    /**
     * 현재 체력 조회
     */
    int getHp();

    /**
     * 이름 반환 (로그용)
     */
    String getName();

    List<TagInstance> getTags();
    void removeExpiredTags();

}
