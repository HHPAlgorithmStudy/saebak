package algorithm.hash;

/**
 * 동영상 재생기
 * 
 * 문제 설명
 * 당신은 동영상 재생기를 만들고 있습니다. 당신의 동영상 재생기는 10초 전으로 이동, 10초 후로 이동, 오프닝 건너뛰기 3가지 기능을 지원합니다. 
 * 각 기능이 수행하는 작업은 다음과 같습니다.
 *
 * 10초 전으로 이동: 사용자가 "prev" 명령을 입력할 경우 동영상의 재생 위치를 현재 위치에서 10초 전으로 이동합니다. 
 * 현재 위치가 10초 미만인 경우 영상의 처음 위치로 이동합니다. 영상의 처음 위치는 0분 0초입니다.
 * 10초 후로 이동: 사용자가 "next" 명령을 입력할 경우 동영상의 재생 위치를 현재 위치에서 10초 후로 이동합니다. 
 * 동영상의 남은 시간이 10초 미만일 경우 영상의 마지막 위치로 이동합니다. 영상의 마지막 위치는 동영상의 길이와 같습니다.
 * 오프닝 건너뛰기: 현재 재생 위치가 오프닝 구간(op_start ≤ 현재 재생 위치 ≤ op_end)인 경우 자동으로 오프닝이 끝나는 위치로 이동합니다.
 * 동영상의 길이를 나타내는 문자열 video_len, 기능이 수행되기 직전의 재생위치를 나타내는 문자열 pos, 
 * 오프닝 시작 시각을 나타내는 문자열 op_start, 오프닝이 끝나는 시각을 나타내는 문자열 op_end, 
 * 사용자의 입력을 나타내는 1차원 문자열 배열 commands가 매개변수로 주어집니다. 
 * 이때 사용자의 입력이 모두 끝난 후 동영상의 위치를 "mm:ss" 형식으로 return 하도록 solution 함수를 완성해 주세요.
 *
 * 제한사항
 * video_len의 길이 = pos의 길이 = op_start의 길이 = op_end의 길이 = 5
 * video_len, pos, op_start, op_end는 "mm:ss" 형식으로 mm분 ss초를 나타냅니다.
 * 0 ≤ mm ≤ 59
 * 0 ≤ ss ≤ 59
 * 분, 초가 한 자리일 경우 0을 붙여 두 자리로 나타냅니다.
 * 비디오의 현재 위치 혹은 오프닝이 끝나는 시각이 동영상의 범위 밖인 경우는 주어지지 않습니다.
 * 오프닝이 시작하는 시각은 항상 오프닝이 끝나는 시각보다 전입니다.
 * 1 ≤ commands의 길이 ≤ 100
 * commands의 원소는 "prev" 혹은 "next"입니다.
 * "prev"는 10초 전으로 이동하는 명령입니다.
 * "next"는 10초 후로 이동하는 명령입니다.
 * 
 * 입출력 예
 * video_len	pos	op_start	op_end	commands	result
 * "34:33"	"13:00"	"00:55"	"02:55"	["next", "prev"]	"13:00"
 * "10:55"	"00:05"	"00:15"	"06:55"	["prev", "next", "next"]	"06:55"
 * "07:22"	"04:05"	"00:15"	"04:07"	["next"]	"04:17"
 */
public class VideoPlayer {

    public static void main(String[] args) {
        String video_len = "07:22";
        String pos = "04:05";
        String op_start = "00:15";
        String op_end = "04:07";
        String[] commands = {"next"};

        System.out.println(solution(video_len,pos,op_start,op_end,commands));
    }

    public static String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {

        String answer = "";

        // 각 재생 시간을 숫자로 치환
        int videoTime = convertMinToSec(video_len);
        int posTime = convertMinToSec(pos);
        int opStartTime = convertMinToSec(op_start);
        int opEndTime = convertMinToSec(op_end);

        for (String command : commands) {
            // 오프닝 건너뛰기
            if ( (opStartTime <= posTime) && (posTime <= opEndTime)) posTime = opEndTime;

            // 10초 전으로 이동
            if ("prev".equals(command)) {
                posTime -= 10;
                if (posTime < 0) posTime = 0;
            }

            // 10초 뒤로 이동
            if ("next".equals(command)) {
                posTime += 10;
                if (posTime > videoTime) posTime = videoTime;
            }

            // 오프닝 건너뛰기
            if ( (opStartTime <= posTime) && (posTime <= opEndTime)) posTime = opEndTime;
        }

        answer = convertSecToMin(posTime);

        return answer;
    }

    private static int convertMinToSec (String playTime) {
        return (Integer.parseInt(playTime.split(":")[0])*60) + Integer.parseInt(playTime.split(":")[1]);
    }

    private static String convertSecToMin(int posTime) {
        String min = String.valueOf(posTime/60);
        if (min.length() == 1) min = "0" + min;

        String sec = String.valueOf(posTime%60);
        if (sec.length() == 1) sec = "0" + sec;

        return min + ":" + sec;
    }
}
