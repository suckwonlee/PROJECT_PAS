package kr.ac.kopo.project_pas.reward;

import kr.ac.kopo.project_pas.playerdata.PlayerInventory;

public interface IReward {
    String getId();
    String getName();
    String getDescription();
    void applyToPlayer(PlayerInventory inventory);
}

