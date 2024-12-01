package algorithm.sort;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * 달리기 경주
 * 문제 설명
 * 얀에서는 매년 달리기 경주가 열립니다. 해설진들은 선수들이 자기 바로 앞의 선수를 추월할 때 추월한 선수의 이름을 부릅니다.
 * 예를 들어 1등부터 3등까지 "mumu", "soe", "poe" 선수들이 순서대로 달리고 있을 때, 해설진이 "soe"선수를 불렀다면 2등인 "soe" 선수가 1등인 "mumu" 선수를 추월했다는 것입니다.
 * 즉 "soe" 선수가 1등, "mumu" 선수가 2등으로 바뀝니다.
 *
 * 선수들의 이름이 1등부터 현재 등수 순서대로 담긴 문자열 배열 players와 해설진이 부른 이름을 담은 문자열 배열 callings가 매개변수로 주어질 때,
 * 경주가 끝났을 때 선수들의 이름을 1등부터 등수 순서대로 배열에 담아 return 하는 solution 함수를 완성해주세요.
 *
 * 제한사항
 * 5 ≤ players의 길이 ≤ 50,000
 * players[i]는 i번째 선수의 이름을 의미합니다.
 * players의 원소들은 알파벳 소문자로만 이루어져 있습니다.
 * players에는 중복된 값이 들어가 있지 않습니다.
 * 3 ≤ players[i]의 길이 ≤ 10
 * 2 ≤ callings의 길이 ≤ 1,000,000
 * callings는 players의 원소들로만 이루어져 있습니다.
 * 경주 진행중 1등인 선수의 이름은 불리지 않습니다.
 * 입출력 예
 * players	callings	result
 * ["mumu", "soe", "poe", "kai", "mine"]	["kai", "kai", "mine", "mine"]	["mumu", "kai", "mine", "soe", "poe"]
 */
public class RunningRace {

    public static void main(String[] args) {
        String[] players = {"mumu", "soe", "poe", "kai", "mine"};
        String[] callings = {"kai", "kai", "mine", "mine"};

        String[] result = solution2(players,callings);
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + " ");
        }
    }

    // 시간 초과
    public static String[] solution(String[] players, String[] callings) {
        String[] answer = {};

        // 추가, 삭제가 용이한 LinkedList 사용
        LinkedList<String> playerList = new LinkedList<>(Arrays.asList(players));
        int index = -1;
        String temp = null;

        for (String call : callings) {
            index = IntStream.range(0, playerList.size())
                    .filter(i -> playerList.get(i).equals(call))
                    .findFirst()
                    .orElse(-1);

            if (index == -1) {
                throw new NullPointerException("Calling " + call + " was not found");
            }

            temp = playerList.get(index-1);
            playerList.set(index-1, playerList.get(index));
            playerList.set(index, temp);
        }

        answer = playerList.toArray(answer);
        return answer;
    }

    public static String[] solution2(String[] players, String[] callings) {
        String[] answer = {};

        // 배열을 LinkedList로 변환
        LinkedList<String> playerList = new LinkedList<>(Arrays.asList(players));
        // 플레이어 이름과 인덱스를 매핑하는 HashMap 생성
        Map<String, Integer> playerIndexMap = new HashMap<>();
        for (int i = 0; i < players.length; i++) {
            playerIndexMap.put(players[i], i);
        }

        for (String call : callings) {
            // 호출된 플레이어의 현재 인덱스
            int index = playerIndexMap.get(call);
            // 호출된 플레이어가 이미 맨 앞에 있는 경우 무시
            if (index == 0) continue;
            // 이전 플레이어와 위치 교환
            String previousPlayer = playerList.get(index - 1);
            playerList.set(index - 1, call);
            playerList.set(index, previousPlayer);

            // HashMap의 인덱스 업데이트
            playerIndexMap.put(call, index - 1);
            playerIndexMap.put(previousPlayer, index);
        }

        answer = playerList.toArray(answer);
        return answer;
    }
}
