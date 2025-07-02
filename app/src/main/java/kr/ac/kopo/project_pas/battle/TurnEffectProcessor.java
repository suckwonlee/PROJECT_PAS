package kr.ac.kopo.project_pas.battle;

import java.util.List;

public class TurnEffectProcessor {

    public static void processBurnDots(List<IBattleEntity> entities) {
        for (IBattleEntity entity : entities) {
            if (!entity.isBurnedThisTurn() && entity.getCurrentBurnDamage() > 0) {
                int reduced = Math.max(1, (int)(entity.getCurrentBurnDamage() * 0.05));
                entity.setCurrentBurnDamage(entity.getCurrentBurnDamage() - reduced);
            }

            if (entity.getCurrentBurnDamage() > 0) {
                entity.reduceHp(entity.getCurrentBurnDamage());
            }

            entity.setBurnedThisTurn(false);
        }
    }
}
