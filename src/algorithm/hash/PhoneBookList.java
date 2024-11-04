package algorithm.hash;

import java.util.*;

/**
 * 전화번호 목록 - 해시
 * 문제 설명
 * 전화번호부에 적힌 전화번호 중, 한 번호가 다른 번호의 접두어인 경우가 있는지 확인하려 합니다.
 * 전화번호가 다음과 같을 경우, 구조대 전화번호는 영석이의 전화번호의 접두사입니다.
 *
 * 구조대 : 119
 * 박준영 : 97 674 223
 * 지영석 : 11 9552 4421
 * 전화번호부에 적힌 전화번호를 담은 배열 phone_book 이 solution 함수의 매개변수로 주어질 때,
 * 어떤 번호가 다른 번호의 접두어인 경우가 있으면 false를 그렇지 않으면 true를 return 하도록 solution 함수를 작성해주세요.
 *
 * 제한 사항
 * phone_book의 길이는 1 이상 1,000,000 이하입니다.
 * 각 전화번호의 길이는 1 이상 20 이하입니다.
 * 같은 전화번호가 중복해서 들어있지 않습니다.
 */
public class PhoneBookList {
    public static void main(String[] args) {
        String[] phone_book = {"12","123","1235","567","88"};
        System.out.println(solution02(phone_book));
    }

    // 방법1 : 정확성테스트 O, 효율성테스트 X
    public static boolean solution01(String[] phone_book) {
        boolean answer = true;

        // 번호를 짧은 길이순으로 정렬
        List<String> phoneBookList = new ArrayList<>(Arrays.asList(phone_book));
        phoneBookList.sort(Comparator.comparingInt(String::length));

        // 짧은 길이순서대로 해당 번호가 다음 길이의 번호에 일치하는 것이 있는지 찾기
        for (int i=0; i<phoneBookList.size()-1; i++) {
            for (int j=i+1; j<phoneBookList.size(); j++) {
                if (phoneBookList.get(i).equals(phoneBookList.get(j).substring(0,phoneBookList.get(i).length()))) {
                    answer = false;
                    break;
                }
            }
        }

        return answer;
    }

    // 방법2 : 정확성테스트 O, 효율성테스트 O
    public static boolean solution02(String[] phone_book) {
        boolean answer = true;

        // 문자열로 정렬하면 접두사가 근접한 애들끼리 모임
        Arrays.sort(phone_book);

        for (int i=0; i<phone_book.length-1; i++) {
            System.out.println("1." + phone_book[i+1]);
            System.out.println("2." + phone_book[i]);
            if (phone_book[i+1].startsWith(phone_book[i])) {
                answer = false;
                break;
            }
        }
        return answer;
    }

}
