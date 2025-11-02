package lotto.model;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.Collections;
import java.util.List;

public class Lotto {
    private final List<Integer> numbers;
    private final static int MIN = 1;
    private final static int MAX = 45;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 6개여야 합니다.");
        }
        boolean hasOutOfRange = numbers.stream()
                .anyMatch(num -> num < MIN || num > MAX);
        if (hasOutOfRange) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 1부터 45 사이여야 합니다.");
        }
        if (numbers.size() != numbers.stream().distinct().count()) {
            throw new IllegalArgumentException("[ERROR] 로또 번호에 중복된 숫자가 있습니다.");
        }
    }

    // TODO: 추가 기능 구현
    public static Lotto makeRandomLotto() {
        List<Integer> lotto = Randoms.pickUniqueNumbersInRange(1, 45, 6);
        Collections.sort(lotto);
        return new Lotto(lotto);
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

}
