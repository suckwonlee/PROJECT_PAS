package kr.ac.kopo.project_pas.characterdata;

import java.util.HashMap;
import java.util.Map;

import kr.ac.kopo.project_pas.R;

public class CharacterDataRegistry {

    public static class CharacterInfo {
        public final String id;
        public final String name;
        public final String flavorText;
        public final int portraitRes;
        public final boolean isWip;

        public CharacterInfo(String id, String name, String flavorText, int portraitRes, boolean isWip) {
            this.id = id;
            this.name = name;
            this.flavorText = flavorText;
            this.portraitRes = portraitRes;
            this.isWip = isWip;
        }
    }

    private static final Map<String, CharacterInfo> characterMap = new HashMap<>();

    static {
        characterMap.put("HERO", new CharacterInfo(
                "HERO",
                "용사후보",
                "추상적이었기에 수많았던 용사 후보 중 한 명. 자신이 용사라 믿고 세상의 구원을 위해 일어섰다.",
                R.drawable.hero,
                true
        ));
        characterMap.put("HUNTER", new CharacterInfo(
                "HUNTER",
                "사냥꾼",
                "오랜 세월 이계의 존재들을 감시해온 감시단의 생존자. 이제 형제들의 복수를 위한 사냥에 나섰다.",
                R.drawable.hunter,
                false
        ));
        characterMap.put("CLERIC", new CharacterInfo(
                "CLERIC",
                "성직자",
                "이계의 침략으로 흔들리고 있는 교단 추기경의 딸. 세상의 평화를 기원하며 여정을 떠났다",
                R.drawable.priest,
                false
        ));
        characterMap.put("WIZARD", new CharacterInfo(
                "WIZARD",
                "마법사",
                "금지된 마법을 연구하다 마탑에서 추방된 최연소 졸업생. 누구의 방식이 옳았는지 보여줄 시간이 왔다.",
                R.drawable.wizard,
                false
        ));
        characterMap.put("DRUID", new CharacterInfo(
                "DRUID",
                "드루이드",
                "오랜 세월동안 잠들어있던 야생신들의 직계 혈통. 이계의 침략을 막기위해 오랜 잠에서 눈을 떴다.",
                R.drawable.druid,
                false
        ));
        characterMap.put("ENGINEER", new CharacterInfo(
                "ENGINEER",
                "기술자",
                "기술로 마법을 모두에게 선사하겠다 다짐한 기술자. 이제 그 기술로 모두를 구할 때가 도래했다.",
                R.drawable.artificer,
                false
        ));
        characterMap.put("BARD", new CharacterInfo(
                "BARD",
                "음유시인",
                "몽환계의 힘을 내려받아 노래로써 빚어내는 음유시인. 세상에 이야기를 남기기 위해 모험을 떠났다.",
                R.drawable.bard,
                false
        ));
        characterMap.put("EXOTIC", new CharacterInfo(
                "EXOTIC",
                "이계종",
                "침략해온 이계의 존재들과는 달리 물질계를 관찰하는걸 즐기는 존재. 죽어가던 병사와 계약을 맺어 빙의했다.",
                R.drawable.exotic,
                false
        ));
    }

    public static CharacterInfo getCharacterById(String id) {
        return characterMap.get(id);
    }
}
