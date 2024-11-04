package algorithm.greedy;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 체육복 - 탐욕법 (미해결)
 * 전체 학생의 수 n, 체육복을 도난당한 학생들의 번호가 담긴 배열 lost, 
 * 여벌의 체육복을 가져온 학생들의 번호가 담긴 배열 reserve가 매개변수로 주어질 때, 
 * 체육수업을 들을 수 있는 학생의 최댓값을 return 하도록 solution 함수를 작성해주세요.
 *
 * 제한사항
 * 전체 학생의 수는 2명 이상 30명 이하입니다.
 * 체육복을 도난당한 학생의 수는 1명 이상 n명 이하이고 중복되는 번호는 없습니다.
 * 여벌의 체육복을 가져온 학생의 수는 1명 이상 n명 이하이고 중복되는 번호는 없습니다.
 * 여벌 체육복이 있는 학생만 다른 학생에게 체육복을 빌려줄 수 있습니다.
 * 여벌 체육복을 가져온 학생이 체육복을 도난당했을 수 있습니다. 이때 이 학생은 체육복을 하나만 도난당했다고 가정하며,
 * 남은 체육복이 하나이기에 다른 학생에게는 체육복을 빌려줄 수 없습니다.
 */
public class GymClothes {

    public static void main(String[] args) {
        int n = 5;
        int[] lost = {2,4};
        int[] reserve = {1,3,5};

        System.out.println(solution(n, lost, reserve));

    }

    public static int solution(int n, int[] lost, int[] reserve) {
        int answer = 0;
        int bind = n - lost.length; // 당장 입을옷 있는 학생 수
        // 프로그래머스용
//        List<Integer> reserveList = new ArrayList<>();
//        for (int num : reserve) {
//            reserveList.add(num);  // int를 Integer로 자동 변환하여 추가
//        }
        List<Integer> reserveList = Arrays.stream(reserve).boxed().collect(Collectors.toList());
        // 여분 빌려줄사람 찾기
        for (int i=0; i<lost.length; i++) {
            //System.out.println(lost[i]);
            if (reserveList.contains(lost[i])) {
                int index = reserveList.indexOf(lost[i]);
                reserveList.remove(index);
                continue;
            }
            if (reserveList.contains(lost[i]-1)) {
                bind++;
                int index = reserveList.indexOf(lost[i]-1);
                reserveList.remove(index);
                continue;
            }
            if ( reserveList.contains(lost[i]+1)) {
                bind++;
                int index = reserveList.indexOf(lost[i]+1);
                reserveList.remove(index);
                continue;
            }
        }
        answer = bind;
        return answer;
    }
}
