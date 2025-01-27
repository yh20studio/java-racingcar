package racingcar.vo;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PositionTest {

    @DisplayName("new Position() 테스트")
    @Test
    void constructor_test() {
        Position position = new Position();
        assertThat(position.getPosition()).isZero();
    }

    @DisplayName("increase() 테스트")
    @Test
    void increase_test() {
        Position position = new Position();
        position.increase();
        assertThat(position.getPosition()).isEqualTo(1);
    }

    @DisplayName("compareTo() 테스트")
    @Test
    void compareTo_test() {
        Position position = new Position();
        Position increasedPosition = new Position();
        increasedPosition.increase();
        assertThat(increasedPosition.compareTo(position)).isPositive();
    }

    @DisplayName("equals() 테스트")
    @Test
    void equals_test() {
        Position position = new Position();
        Position testPosition = new Position();
        assertThat(position.equals(testPosition)).isTrue();
    }
}
