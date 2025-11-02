package lotto.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    public static String readInput(){
        return Console.readLine();
    }

    public static void getPrice(){
        System.out.println("구입금액을 입력해 주세요.");
    }

    public static void getLottoNumber(){
        System.out.println("당첨 번호를 입력해 주세요.");
    }

    public static void getBonusNumber(){
        System.out.println("보너스 번호를 입력해 주세요.");
    }
}
