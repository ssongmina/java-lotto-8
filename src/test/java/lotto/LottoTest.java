package lotto;

import lotto.controller.LottoController;
import lotto.model.Lotto;
import lotto.model.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class LottoTest {

    private final LottoController controller = new LottoController();

    @Test
    void 로또_번호의_개수가_6개가_넘어가면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void 로또_번호에_중복된_숫자가_있으면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    // TODO: 추가 기능 구현에 따른 테스트 코드 작성
    @DisplayName("입력(string)이 숫자라면 int값으로 반환한다")
    @Test
    void 입력이_숫자라면_int값으로_반환한다(){
        int num = controller.validateNum("1234");
        assertThat(num).isEqualTo(1234);
    }

    @DisplayName("입력이 숫자가 아니라면 예외가 발생한다.")
    @Test
    void 입력이_숫자가_아니라면_예외가_발생한다(){
        assertThatThrownBy(() -> controller.validateNum("@2#$"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("구매한 로또와 로또 번호가 일치하는 개수를 센다")
    @Test
    void 로또번호와_구매한_로또의_일치하는_개수를_센다(){
        List<Integer> buyLotto = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        Lotto lotto = new Lotto(buyLotto);
        List<Integer> rightLotto = new ArrayList<>(Arrays.asList(1, 2, 3, 7, 8, 9));
        Rank rank = controller.checkSameNum(lotto, rightLotto, 7);
        assertThat(rank).isEqualTo(Rank.FIFTH);
    }
}
