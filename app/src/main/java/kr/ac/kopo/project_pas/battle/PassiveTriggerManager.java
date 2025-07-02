package kr.ac.kopo.project_pas.battle;

import kr.ac.kopo.project_pas.character.PlayerCharacter;
import kr.ac.kopo.project_pas.enemydata.EnemyDefinition;
import kr.ac.kopo.project_pas.publicdata.PassiveSkill;
import kr.ac.kopo.project_pas.publicdata.PassiveSkill.PassiveData;

import java.util.List;

public class PassiveTriggerManager {

    public static void onBattleStart(PlayerCharacter player) {
        List<PassiveSkill> passives = player.getPassives();

        for (PassiveSkill skill : passives) {
            switch (skill.getName()) {
                case "투쟁심":
                    int atk = PassiveData.getEffectValue("투쟁심", skill.getLevel());
                    player.increaseAtk(atk);
                    break;

                case "출격":
                    int bonusHp = PassiveData.getEffectValue("출격", skill.getLevel());
                    player.increaseHp(bonusHp);
                    break;

                case "중갑":
                    int armor = PassiveData.getEffectValue("중갑", skill.getLevel());
                    // TODO: player.addArmor(armor);
                    break;

                case "의지":
                    int resist = PassiveData.getEffectValue("의지", skill.getLevel());
                    // TODO: player.addWill(resist);
                    break;
            }
        }
    }

    public static void onHitEnemy(PlayerCharacter player, EnemyDefinition enemy) {
        for (PassiveSkill skill : player.getPassives()) {
            switch (skill.getName()) {
                case "흡혈":
                    int percent = PassiveData.getEffectValue("흡혈", skill.getLevel());
                    // TODO: damage 기반 회복 계산 후 player.increaseHp(...);
                    break;

                case "침독":
                    int poison = PassiveData.getEffectValue("침독", skill.getLevel());
                    // TODO: enemy.applyPoison(poison);
                    break;
            }
        }
    }

    public static void onTurnStart(PlayerCharacter player) {
        for (PassiveSkill skill : player.getPassives()) {
            if ("생존".equals(skill.getName())) {
                int heal = PassiveData.getEffectValue("생존", skill.getLevel());
                player.increaseHp(heal);
            }
        }
    }

    public static void onTurnEnd(PlayerCharacter player, List<EnemyDefinition> enemies) {
        for (PassiveSkill skill : player.getPassives()) {
            if ("방화".equals(skill.getName())) {
                int fire = PassiveData.getEffectValue("방화", skill.getLevel());
                for (EnemyDefinition enemy : enemies) {
                    enemy.takeFireDamage(fire);
                    // 화염 피해는 누적되며, 매턴마다 새로운 화염 피해가 들어오지 않으면
                    // 기존 화염 피해량이 5%씩(최소 1) 감소하는 별도 도트 처리 로직이 필요합니다.
                }
            }
        }
    }
}
