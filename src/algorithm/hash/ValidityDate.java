package algorithm.hash;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * 개인정보 수집 유효기간
 *
 * 문제 설명
 * 고객의 약관 동의를 얻어서 수집된 1~n번으로 분류되는 개인정보 n개가 있습니다.
 * 약관 종류는 여러 가지 있으며 각 약관마다 개인정보 보관 유효기간이 정해져 있습니다.
 * 당신은 각 개인정보가 어떤 약관으로 수집됐는지 알고 있습니다.
 * 수집된 개인정보는 유효기간 전까지만 보관 가능하며, 유효기간이 지났다면 반드시 파기해야 합니다.
 *
 * 예를 들어, A라는 약관의 유효기간이 12 달이고, 2021년 1월 5일에 수집된 개인정보가 A약관으로 수집되었다면
 * 해당 개인정보는 2022년 1월 4일까지 보관 가능하며 2022년 1월 5일부터 파기해야 할 개인정보입니다.
 * 당신은 오늘 날짜로 파기해야 할 개인정보 번호들을 구하려 합니다.
 *
 * 입출력 예
 * today	terms	privacies	result
 * "2022.05.19"	["A 6", "B 12", "C 3"]	["2021.05.02 A", "2021.07.01 B", "2022.02.19 C", "2022.02.20 C"]	[1, 3]
 * "2020.01.01"	["Z 3", "D 5"]	["2019.01.01 D", "2019.11.15 Z", "2019.08.02 D", "2019.07.01 D", "2018.12.28 Z"]	[1, 4, 5]
 */
public class ValidityDate {

    public static void main(String[] args) {
//        String today = "2022.05.19";
//        String[] terms = {"A 6", "B 12", "C 3"};
//        String[] privacies = {"2021.05.02 A", "2021.07.01 B", "2022.02.19 C", "2022.02.20 C"};
        String today = "2020.01.01";
        String[] terms = {"Z 3", "D 5"};
        String[] privacies = {"2019.01.01 D", "2019.11.15 Z", "2019.08.02 D", "2019.07.01 D", "2018.12.28 Z"};

        int[] result = solution(today, terms, privacies);

        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + " ");
        }
    }

    public static int[] solution(String today, String[] terms, String[] privacies) {
        int[] answer = {};

        // 날짜 비교하기 쉽게 localDate 형식으로 변경
        LocalDate todayDate = LocalDate.parse(today, DateTimeFormatter.ofPattern("yyyy.MM.dd"));

        Map<String, Integer> term = new HashMap<String, Integer>();
        for (int i = 0; i < terms.length; i++) {
            term.put(terms[i].split(" ")[0], Integer.parseInt(terms[i].split(" ")[1]));
        }

        List<LocalDate> dates = new ArrayList<LocalDate>();

        for (int i = 0; i < privacies.length; i++) {
            dates.add(LocalDate.parse(privacies[i].split(" ")[0], DateTimeFormatter.ofPattern("yyyy.MM.dd")).plusMonths(term.get(privacies[i].split(" ")[1])));
        }

        List<Integer> result = new ArrayList<Integer>();
        for (int i=0; i<dates.size(); i++) {
            if (todayDate.isAfter(dates.get(i)) || todayDate.equals(dates.get(i)) ) {
                result.add(i+1);
            }
        }

        answer = result.stream().mapToInt(i -> i).toArray();

        return answer;
    }
}
