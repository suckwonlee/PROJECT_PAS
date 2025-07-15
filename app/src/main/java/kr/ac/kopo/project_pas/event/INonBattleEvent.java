//package kr.ac.kopo.project_pas.event;
//
//import java.util.List;
//
//public interface INonBattleEvent extends IChapterEvent {
//
//    /**
//     * 이벤트 설명 텍스트 (예: "어느 교단이 치유 목적으로 선교를 나왔다...")
//     */
//    String getDescription();
//
//    /**
//     * 현재 캐릭터 ID를 기준으로 표시 가능한 선택지 리스트를 반환
//     * @param characterId 선택한 캐릭터 ID (예: "성직자", "마법사", "이계종")
//     * @return 선택지 리스트
//     */
//    List<EventChoice> getAvailableChoices(String characterId);
//
//    /**
//     * 특정 선택지를 선택했을 때 결과를 반환
//     * @param choiceIndex 선택지 번호 (1, 2, 3)
//     * @param characterId 현재 플레이어 캐릭터 ID
//     * @return 선택 결과 (텍스트 + 결과 타입)
//     */
//    EventResult choose(int choiceIndex, String characterId);
//}
