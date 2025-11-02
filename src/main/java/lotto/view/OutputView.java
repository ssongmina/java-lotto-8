package lotto.view;

import lotto.model.Lotto;
import lotto.model.Rank;

import java.sql.SQLOutput;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    public static void printBuyLotto(int number){
        System.out.println(number + "개를 구매했습니다.");
    }

    public static void printLottoNumber(Lotto lotto){
        String result = lotto.getNumbers().stream()
                .map(Object::toString)
                .collect(Collectors.joining(","));
        System.out.println("[" + result + "]");
    }

    public static void printStatistic(Map<Rank, Integer> ranks, double win){
        System.out.println("당첨 통계");
        System.out.println("---");
        printRankResult(Rank.FIFTH, ranks.get(Rank.FIFTH));
        printRankResult(Rank.FOURTH, ranks.get(Rank.FOURTH));
        printRankResult(Rank.THIRD, ranks.get(Rank.THIRD));
        printRankResult(Rank.SECOND, ranks.get(Rank.SECOND));
        printRankResult(Rank.FIRST, ranks.get(Rank.FIRST));
        System.out.println("총 수익률은 " + win + "%입니다.");
    }

    public static void printRankResult(Rank ranks, int num){
        System.out.println(Rank.getMessage(ranks) + num + "개");
    }
}
