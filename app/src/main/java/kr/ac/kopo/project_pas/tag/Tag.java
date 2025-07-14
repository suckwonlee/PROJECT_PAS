package kr.ac.kopo.project_pas.tag;

public enum Tag {
    // 상태이상 및 버프/디버프 태그

    FIRE,           // 화염 도트 피해 (매턴 피해, 5%씩 감소)
    POISON,         // 중독 도트 피해 (고정 피해)
    ARMOR,          // 중갑 (즉발형 피해 감소, 중첩 가능)
    DAMAGE_REDUCE,  // 피해 감소 (다음 턴 시작까지 1회성 즉발 무효화)
    IMMUNITY,       // 의지 (마비/기절 무효화)
    EVADE,          // 회피 (일시적 회피율 증가)
    DELAYED_DMG,    // 혈잠 (3턴 후 피해 적용)
    LIFESTEAL,      // 흡혈 (공격 시 회복)
    BERSERK,         // 투쟁심 (공격력 증가)
    REGEN
}
