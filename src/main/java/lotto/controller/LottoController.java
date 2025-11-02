package lotto.controller;

import lotto.model.Lotto;
import lotto.model.Rank;
import lotto.view.InputView;
import lotto.view.OutputView;


import java.util.*;


public class LottoController {

    public void powerOn(){
        int price = validatePrice();
        List<Lotto> lottos = buyLotto(price/1000);
        Lotto lottoNumber = validateLottoNumber();
        int bonusNumber = validateBonusNumber();
        showStatisticResult(lottos, lottoNumber, bonusNumber);
    }

    public void showStatisticResult(List<Lotto> lottos, Lotto lottoNumber, int bonusNumber){
        Map<Rank, Integer> ranks = new EnumMap<>(Rank.class);
        for (Rank rank : Rank.values()) {
            ranks.put(rank, 0);
        }
        List<Integer> lottoNum = lottoNumber.getNumbers();
        for(Lotto lotto : lottos){
            Rank rank = checkSameNum(lotto, lottoNum, bonusNumber);
            if(rank == Rank.NONE) continue;
            ranks.put(rank, ranks.get(rank) + 1);
        }
        int sum = ranks.values().stream().mapToInt(Integer::intValue).sum();
        double percent = ((double)sum / (double)lottos.size()) * 100;
        percent = Math.round(percent * 100) / 100.0;
        OutputView.printStatistic(ranks, percent);
    }

    public Rank checkSameNum(Lotto lotto, List<Integer> lottoNum, int bonusNumber){
        int matches = 0;
        boolean flag = false;
        for(Integer num : lotto.getNumbers()){
            if(lottoNum.contains(num)){
                matches++;
            }
            if(num == bonusNumber){
                flag = true;
            }
        }
        return Rank.getRank(matches, flag);
    }

    public List<Lotto> buyLotto(int number){
        OutputView.printBuyLotto(number);
        List<Lotto> lottos = new ArrayList<>();
        for(int i = 0; i < number; i++){
            Lotto lotto = Lotto.makeRandomLotto();
            OutputView.printLottoNumber(lotto);
            lottos.add(lotto);
        }
        return lottos;
    }

    public Lotto validateLottoNumber(){
        List<Integer> numbers = new ArrayList<>();
        InputView.getLottoNumber();
        while(true){
            try{
                String input = InputView.readInput();
                hasInvalidChar(input);
                String[] inputs = input.split(",");
                numbers.addAll(check(inputs));
                return new Lotto(numbers);
            }catch(IllegalArgumentException e){
                System.out.println(e.getMessage());
                numbers.clear();
            }
        }
    }

    public static void hasInvalidChar(String input) {
        if(!input.matches("[0-9,]*")){
            throw new IllegalArgumentException("[ERROR] 입력에는 숫자 또는 쉼표만 존재해야 합니다");
        }
    }

    public List<Integer> check(String[] input){
        List<Integer> list = new ArrayList<>();
        int num;
        for(String s : input){
            num = validateNum(s);
            list.add(num);
        }
        return list;
    }


    public int validateBonusNumber(){
        InputView.getBonusNumber();
        while(true){
            try{
                String input = InputView.readInput();
                return validateNum(input);
            }catch(IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
        }
    }


    public int validatePrice(){
        InputView.getPrice();
        while(true){
            try{
                String input = InputView.readInput();
                return validateNum(input);
            }catch(IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
        }
    }

    public int validateNum(String input){
        try{
            int num = Integer.parseInt(input);
            if(num % 1000 != 0){
                throw new ArithmeticException();
            }
            return Integer.parseInt(input);
        } catch(NumberFormatException e){
            throw new IllegalArgumentException("[ERROR] 정수값이 아닙니다.");
        } catch(ArithmeticException e){
            throw new IllegalArgumentException("[ERROR] 로또 구입 금액은 1000원 단위로 입력하셔야 합니다");
        }
    }

}
