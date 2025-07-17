package kr.ac.kopo.project_pas.characterdata.heroData;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import kr.ac.kopo.project_pas.R;

/**
 * 용사후보의 스킬 정의 및 선택 상태를 관리하는 클래스
 */
public class HeroCandidateSkillSet {
    // 스킬 정의 엔티티
    public static class SkillDefinition {
        private final String id;
        private final String name;
        private final String flavorText;
        private final int iconRes;
        private final int[] powerPerLevel;
        private final int usageLimit; // 챕터별 사용 횟수 제한 (-1: 무제한)

        public SkillDefinition(String id, String name, String flavorText,
                               int iconRes, int[] powerPerLevel, int usageLimit) {
            this.id = id;
            this.name = name;
            this.flavorText = flavorText;
            this.iconRes = iconRes;
            this.powerPerLevel = powerPerLevel;
            this.usageLimit = usageLimit;
        }

        public String getId()           { return id; }
        public String getName()         { return name; }
        public String getFlavorText()   { return flavorText; }
        public int getIconRes()         { return iconRes; }
        public int[] getPowerPerLevel() { return powerPerLevel; }
        public int getUsageLimit()      { return usageLimit; }

        /**
         * 각 스킬별 효과 설명을 반환합니다.
         */
        public String getEffectDescription() {
            switch (id) {
                case "HERO_ATK_1": {
                    int basePct   = powerPerLevel[0];
                    int growthPct = powerPerLevel[1] - powerPerLevel[0];
                    return String.format(
                            "공격력의 %d%%만큼의 피해를 입힙니다. (성장치 %d%%)",
                            basePct, growthPct
                    );
                }
                case "HERO_ATK_2": {
                    int baseDef   = powerPerLevel[0];
                    int growthDef = powerPerLevel[1] - powerPerLevel[0];
                    return String.format(
                            "방어력 %d만큼 방어합니다. (성장치 %d)",
                            baseDef, growthDef
                    );
                }
                case "HERO_ATK_3": {
                    int basePct   = powerPerLevel[0];
                    int growthPct = powerPerLevel[1] - powerPerLevel[0];
                    return String.format(
                            "공격력의 %d%%만큼의 피해와 화염을 줍니다. (성장치 %d%%)",
                            basePct, growthPct
                    );
                }
                case "HERO_DEF_1":
                    return String.format("방어력 %d만큼 방어", powerPerLevel[0]);
                case "HERO_DEF_2": {
                    int basePct   = powerPerLevel[0];
                    int growthPct = powerPerLevel[1] - powerPerLevel[0];
                    return String.format(
                            "공격력의 %d%%만큼의 피해를 입힙니다. (성장치 %d%%)",
                            basePct, growthPct
                    );
                }
                case "HERO_DEF_3": {
                    int plate = powerPerLevel[0];
                    return String.format(
                            "중갑%d, 의지1, 저지불가1 (사용횟수 %d회)",
                            plate, usageLimit
                    );
                }
                default:
                    if (usageLimit > 0) {
                        return String.format(
                                "%d 효과 (사용횟수 %d회)",
                                powerPerLevel[0], usageLimit
                        );
                    } else {
                        return String.format(
                                "%d 효과",
                                powerPerLevel[0]
                        );
                    }
            }
        }
    }

    // 공격 스킬 정의 (6단계)
    private static final List<SkillDefinition> ATTACK_SKILLS = Collections.unmodifiableList(
            Arrays.asList(
                    new SkillDefinition(
                            "HERO_ATK_1", "공격",
                            "언제나 기본기를 다지던 친구라네. 괭이질도 검 내려베기 같더라니까",
                            R.drawable.skill_slash,
                            new int[]{100, 120, 140, 160, 180, 200},
                            -1
                    ),
                    new SkillDefinition(
                            "HERO_ATK_2", "방패술",
                            "성검의 선택을 받지 못한다고 해도 세상을 구하겠다며 단련한 그는 자신이 방패술의 재능을 가진걸 알게됐다",
                            R.drawable.skill_shield,
                            new int[]{95, 115, 135, 155, 175, 195},
                            -1
                    ),
                    new SkillDefinition(
                            "HERO_ATK_3", "신념의 검",
                            "사악한 모든걸 불태우는 성검. 사용자조차 이 검을 악한 의도로 사용하려 한다면 불태운다",
                            R.drawable.skill_whirl,
                            new int[]{25, 30, 35, 40, 45, 50},
                            -1
                    )
            )
    );

    // 방어 스킬 정의 (6단계)
    private static final List<SkillDefinition> DEFENSE_SKILLS = Collections.unmodifiableList(
            Arrays.asList(
                    new SkillDefinition(
                            "HERO_DEF_1", "방어",
                            "1대 때리고 죽지말고 8번 막고 2대 때리는게 더 효율적이다",
                            R.drawable.skill_guard,
                            new int[]{250, 300, 350, 400, 450, 500},
                            -1
                    ),
                    new SkillDefinition(
                            "HERO_DEF_2", "무기방어",
                            "때로는 검도 훌륭한 방어 도구다. 이 쇳덩이가 그리 쉽게 부러지겠나?",
                            R.drawable.skill_counter,
                            new int[]{200, 240, 280, 320, 360, 400},
                            -1
                    ),
                    new SkillDefinition(
                            "HERO_DEF_3", "철벽의 의지",
                            "나의 길이 꺾이지 않기를...세상이 평화로워지는 그 순간까지...",
                            R.drawable.skill_strike,
                            new int[]{2, 2, 2, 2, 2, 2},
                            15
                    )
            )
    );

    // 싱글톤 인스턴스 (선택 상태 포함)
    private static final HeroCandidateSkillSet INSTANCE = new HeroCandidateSkillSet();

    // 선택된 스킬 및 레벨, 남은 방어 스킬 사용 횟수
    private String selectedAttackId = ATTACK_SKILLS.get(0).getId();
    private int selectedAttackLevel = 1;
    private String selectedDefenseId = DEFENSE_SKILLS.get(0).getId();
    private int selectedDefenseLevel = 1;
    private int defenseUsesLeft = DEFENSE_SKILLS.get(2).getUsageLimit();

    private HeroCandidateSkillSet() {
        // 초기 상태 설정
    }

    public static HeroCandidateSkillSet getInstance() {
        return INSTANCE;
    }

    /** 스킬 정의 리스트 반환 */
    public List<SkillDefinition> getAttackSkills() {
        return ATTACK_SKILLS;
    }

    public List<SkillDefinition> getDefenseSkills() {
        return DEFENSE_SKILLS;
    }

    // 선택 상태 접근자/설정자
    public void setSelectedAttack(String id, int level) {
        this.selectedAttackId = id;
        this.selectedAttackLevel = level;
    }

    public void setSelectedDefense(String id, int level) {
        this.selectedDefenseId = id;
        this.selectedDefenseLevel = level;
        this.defenseUsesLeft = DEFENSE_SKILLS.stream()
                .filter(s -> s.getId().equals(id))
                .findFirst()
                .map(SkillDefinition::getUsageLimit)
                .orElse(-1);
    }

    public int getDefenseUsesLeft() {
        return defenseUsesLeft;
    }

    public void useDefenseSkill() {
        if (defenseUsesLeft > 0) defenseUsesLeft--;
    }
}
