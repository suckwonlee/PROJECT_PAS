package kr.ac.kopo.project_pas.tag;

public class TagUtils {

    public static String getDescription(TagInstance tag) {
        String name;
        switch (tag.getTag()) {
            case FIRE:
                name = "화염";
                return "[" + name + "] 매턴 " + tag.getValue() + "의 화염 피해 (해당 턴에 화염 피해를 추가로 입지 않으면 종료 시 5% 감소)";
            case POISON:
                name = "중독";
                return "[" + name + "] 매턴 " + tag.getValue() + "의 중독 피해";
            case ARMOR:
                name = "중갑";
                return "[" + name + "] 즉발 피해 감소: " + tag.getValue();
            case DAMAGE_REDUCE:
                name = "피해감소";
                return "[" + name + "] 피해 1회 무효화 (턴 종료 시 삭제됨)";
            case IMMUNITY:
                name = "의지";
                return "[" + name + "] 기절 및 마비 면역 (매턴 1씩 감소)";
            case EVADE:
                name = "회피";
                return "[" + name + "] 회피율 상승 (매턴 1씩 감소)";
            case DELAYED_DMG:
                name = "혈잠";
                return "[" + name + "] 3턴 후 피해: " + tag.getValue();
            case LIFESTEAL:
                name = "흡혈";
                return "[" + name + "] 공격 시 " + tag.getValue() + "% 흡혈";
            case BERSERK:
                name = "공격력증가";
                return "[" + name + "] 공격력 +" + tag.getValue();
            default:
                name = tag.getTag().name();
                return "[" + name + "]";
        }
    }
}
