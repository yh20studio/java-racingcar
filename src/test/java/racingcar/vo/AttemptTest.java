package racingcar.vo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.view.ErrorMessage;

class AttemptTest {

    @DisplayName("new Attempt() 테스트")
    @Test
    void constructor_test() {
        String input = "3";
        Attempt attempt = new Attempt(input);
        assertThat(attempt.getAttempt()).isEqualTo(3);
    }

    @DisplayName("isLeft() 테스트")
    @Test
    void isLeft_test() {
        String inputZero = "0";
        String inputOne = "1";
        Attempt attemptZero = new Attempt(inputZero);
        Attempt attemptOne = new Attempt(inputOne);
        assertThat(attemptZero.isLeft()).isFalse();
        assertThat(attemptOne.isLeft()).isTrue();
    }

    @DisplayName("decrease() 테스트")
    @Test
    void decrease_test() {
        String input = "3";
        Attempt attempt = new Attempt(input);
        assertThat(attempt.getAttempt()).isEqualTo(3);
        attempt = attempt.decrease();
        assertThat(attempt.getAttempt()).isEqualTo(2);
        attempt = attempt.decrease();
        assertThat(attempt.getAttempt()).isEqualTo(1);
        attempt = attempt.decrease();
        assertThat(attempt.getAttempt()).isZero();
    }

    @DisplayName("validNumberFormat() 입력 값이 숫자가 아닌 경우 예외 테스트")
    @Test
    void constructor_NonNumber_exception_test() {
        String input = "abc";
        assertThatThrownBy(() -> new Attempt(input))
                .hasMessageContaining("For input string: " + "\"" + input + "\"");
    }

    @DisplayName("validNegative() 입력 값이 음수인 경우 예외 테스트")
    @Test
    void constructor_negativeNumber_exception_test() {
        String input = "-3";
        assertThatThrownBy(() -> new Attempt(input))
                .hasMessageContaining(ErrorMessage.NUMBER_NEGATIVE.getMessage());
    }
}
