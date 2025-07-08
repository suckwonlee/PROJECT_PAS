package kr.ac.kopo.project_pas.tag;

import kr.ac.kopo.project_pas.character.CombatUnit;


public class TagInstance {
    private final Tag tag;             // 태그 종류
    private int value;                 // 수치 (예: 화염 피해량, 중갑 수치 등)
    private int duration;              // 지속 턴 수 (-1이면 무한 지속)
    private final boolean stackable;   // 중첩 가능 여부
    private final CombatUnit source;   // 태그를 부여한 유닛
    private final CombatUnit target;   // 태그가 적용된 유닛

    public TagInstance(Tag tag, int value, int duration, boolean stackable,
                       CombatUnit source, CombatUnit target) {
        this.tag = tag;
        this.value = value;
        this.duration = duration;
        this.stackable = stackable;
        this.source = source;
        this.target = target;
    }

    public Tag getTag() {
        return tag;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getDuration() {
        return duration;
    }

    public void decrementDuration() {
        if (duration > 0) duration--;
    }

    public boolean isExpired() {
        return duration == 0;
    }

    public boolean isStackable() {
        return stackable;
    }

    public CombatUnit getSource() {
        return source;
    }

    public CombatUnit getTarget() {
        return target;
    }
}
