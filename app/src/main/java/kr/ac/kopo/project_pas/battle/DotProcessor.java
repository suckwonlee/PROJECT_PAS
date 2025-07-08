package kr.ac.kopo.project_pas.battle;

import kr.ac.kopo.project_pas.character.CombatUnit;
import kr.ac.kopo.project_pas.tag.Tag;
import kr.ac.kopo.project_pas.tag.TagInstance;

import java.util.ArrayList;
import java.util.List;

public class DotProcessor {

    /**
     * 턴 시작 시 처리되는 도트 효과 (예: 재생)
     */
    public static void onTurnStart(CombatUnit unit) {
        List<TagInstance> tags = new ArrayList<>(unit.getTags());
        for (TagInstance tag : tags) {
            switch (tag.getTag()) {
                case REGEN:
                    unit.heal(tag.getValue());
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * 턴 종료 시 처리되는 도트 효과 (예: 화염, 중독)
     */
    public static void onTurnEnd(CombatUnit unit) {
        List<TagInstance> tags = new ArrayList<>(unit.getTags());
        for (TagInstance tag : tags) {
            switch (tag.getTag()) {
                case FIRE:
                    unit.takeDamage(tag.getValue());
                    if (!receivedAdditionalFireDamage(unit)) {
                        tag.setValue((int) (tag.getValue() * 0.95));
                    }
                    break;
                case POISON:
                    unit.takeDamage(tag.getValue());
                    break;
                default:
                    break;
            }
            tag.decrementDuration();
        }
        unit.removeExpiredTags();
    }

    /**
     * 해당 턴에 추가 화염 피해를 받은 적이 있는지 확인
     * (임시: 추후 CombatLogger 등과 연동 필요)
     */
    private static boolean receivedAdditionalFireDamage(CombatUnit unit) {
        // TODO: CombatLogger 또는 전투 상태 기록 기반으로 구현 예정
        return false; // 현재는 무조건 감소됨
    }
}
