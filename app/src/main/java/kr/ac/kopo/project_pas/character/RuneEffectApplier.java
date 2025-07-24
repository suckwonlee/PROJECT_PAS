package kr.ac.kopo.project_pas.character;

import kr.ac.kopo.project_pas.R;
import kr.ac.kopo.project_pas.publicdata.PassiveSkill;

public class RuneEffectApplier {

    public static String applyRune1Passive(PlayerCharacter player, String runeName, int runeLevel) {
        String passiveName = null;
        String flavorText = "";

        switch (runeName) {
            case "화염의 룬":
                passiveName = "방화";
                flavorText = "어릴적부터 불장난을 많이 치던 아이였지. 그 불 장난이 전장에서 유용할 수준까지 가는건 상상도 못했군";
                break;
            case "혹한의 룬":
                passiveName = "중갑";
                flavorText = "동대륙에서 혹한에서 살아남은 전사들은 갑옷이 필요하지 않다네. 그들의 육신이 어떤 갑옷보다도 더 단단했지.";
                break;
            case "수호자의 룬":
                passiveName = "의지";
                flavorText = "사흘 밤낮으로 싸우고도 포기하지 않던 사내라네. 기어코 지원군이 도착해서 사람들이 안전해진걸 확인한 뒤에야 기절하고 말더군.";
                break;
            case "독사의 룬":
                passiveName = "침독";
                flavorText = "뱀같은 사내라는 말을 아는가? 그는 입이 아니라 무기에 독이 들었네";
                break;
            case "투지의 룬":
                passiveName = "투쟁심";
                flavorText = "상대가 크면 그에 비례해서 용기가 샘솟고, 상대가 강하다면 오히려 투지가 오르더군. 정말 공격적인 녀석이었지";
                break;
            case "혈귀의 룬":
                passiveName = "흡혈";
                flavorText = "인간답지 않다? 눈썰미가 좋군. 그자는 흡혈귀의 혼혈이라네.";
                break;
            case "필생의 룬":
                passiveName = "생존";
                flavorText = "강한자가 살아남는게 아니다. 살아남는자가 강한것이다";
                break;
            case "돌격의 룬":
                passiveName = "출격";
                flavorText = "정말 태생이 싸움꾼이었지. 다 죽어가던 녀석이 새 싸움이라니까 병상에서 바로 일어나더군";
                break;
        }

        if (passiveName != null) {
            int passiveLevel = (runeLevel == 3) ? 2 : 1;
            player.addPassive(new PassiveSkill(passiveName, passiveLevel));

            if (runeLevel >= 2) {
                applyRune1BonusStats(player, runeName);
            }
        }

        return flavorText;
    }

    private static void applyRune1BonusStats(PlayerCharacter player, String runeName) {
        switch (runeName) {
            case "화염의 룬":
                player.increaseAtk(1);
                break;
            case "혹한의 룬":
                player.increaseDef(1);
                break;
            case "수호자의 룬":
                player.increaseHp(10);
                break;
            case "독사의 룬":
                player.increaseCrit(0.03);
                break;
            case "투지의 룬":
                player.increaseHp(10);
                break;
            case "혈귀의 룬":
                player.increaseAtk(1);
                break;
            case "필생의 룬":
                player.increaseHp(10);
                break;
            case "돌격의 룬":
                player.increaseHp(10);
                break;
        }
    }

    public static String applyRune2Stat(PlayerCharacter player, String runeName, int level) {
        String flavorText = "";

        switch (runeName) {
            case "강격의 룬":
                player.increaseAtk(level);
                flavorText = "기선제압. 싸움에서는 기세를 꺾는게 중요하지. 그러니 힘조절 없이, 온 힘을 다해서. 전력으로.";
                break;
            case "부동의 룬":
                player.increaseDef(level);
                flavorText = "서대륙의 야만전사들이 싸움에 임하는 자세는 모든 투사가 본받을 내용이네. 그들은 지진이 일어나도 흔들리지 않지";
                break;
            case "태산의 룬":
                player.increaseHp(level * 20);
                flavorText = "폭우가 대지를 잠기게 하더라도, 업화가 초목을 태우더라도, 태산은 그 자리에 우뚝 서있다. 그렇기에 태산이다.";
                break;
            case "살의의 룬":
                player.increaseCrit(0.03 * level);
                flavorText = "공격은 상대를 확실히 죽이도록 한방한방에 살의를 담아 처야 한단다. 그래 다리사이 같은";
                break;
            case "잔상의 룬":
                player.increaseEvasion(0.01 * level);
                flavorText = "어딜 보시는 겁니까? 그건 제 잔상입니다만.";
                break;
        }

        return flavorText;
    }

}
