package kr.ac.kopo.project_pas.event;

import kr.ac.kopo.project_pas.character.PlayerCharacter;

public interface IChapterEvent {
    /** 이벤트 종류: "battle", "choice", "reward" 등 */
    String getType();

    /**
     * 이벤트 시작 시 호출되며, 실제 플레이어 캐릭터 인스턴스를 인자로 받습니다.
     * @param player 진행 중인 플레이어 캐릭터
     */
    void startEvent(PlayerCharacter player);

    /** 이벤트 완료 여부 반환 */
    boolean isComplete();

    /** 다음 이벤트를 연결할 수 있으면 반환, 없으면 null */
    IChapterEvent getNextEvent();
}
