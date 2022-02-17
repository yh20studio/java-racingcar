package racingcar.vo;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import racingcar.numberGenerator.NumberGenerator;
import racingcar.numberGenerator.RandomNumberGenerator;
import racingcar.view.ErrorMessage;

public class Cars {

    private static final int NONE_DUPLICATION = 0;

    private final List<Car> cars;
    private final RaceResult raceResult;

    public Cars(String[] names) {
        cars = new ArrayList<>();
        raceResult = new RaceResult();
        createCarsWith(names);
    }

    private Cars(List<Car> cars, String raceAllResult) {
        this.cars = new ArrayList<>(cars);
        this.raceResult = new RaceResult(raceAllResult);
    }

    public Cars repeatRaceBy(Attempt attempt) {
        StringBuilder raceResultBuilder = new StringBuilder();
        List<Car> cars = new ArrayList<>(this.cars);
        NumberGenerator randomNumberGenerator = new RandomNumberGenerator();
        while (attempt.isLeft()) {
            cars = raceAll(cars, randomNumberGenerator);
            raceResultBuilder.append(getOnceResultWith(cars));
            attempt = attempt.decrease();
        }
        return new Cars(cars, raceResultBuilder.toString());
    }

    public Winners judgeWinners() {
        Car maxPositionCar = cars.stream()
                .max(Car::compareTo)
                .orElseThrow(() -> new NoSuchElementException(ErrorMessage.CAR_NOT_FOUND.toString()));
        return new Winners(cars.stream()
                .filter(car -> car.isSamePosition(maxPositionCar))
                .collect(Collectors.toList()));
    }

    public boolean isSameSize(int size) {
        return cars.size() == size;
    }

    private void createCarsWith(String[] names) {
        for (String name : names) {
            addCar(name);
        }
    }

    private void addCar(String name) {
        CarName carName = new CarName(name);
        Car car = new Car(carName);
        validateDuplicateCarName(car);
        cars.add(car);
    }

    private List<Car> raceAll(List<Car> cars, NumberGenerator numberGenerator) {
        List<Car> afterRaceCars = new ArrayList<>();
        for (Car car : cars) {
            Car movedCar = car.move(numberGenerator.generate());
            afterRaceCars.add(movedCar);
        }
        return afterRaceCars;
    }

    private String getOnceResultWith(List<Car> cars) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Car car : cars) {
            stringBuilder.append(car.toString());
            stringBuilder.append(System.lineSeparator());
        }
        stringBuilder.append(System.lineSeparator());
        return stringBuilder.toString();
    }

    private void validateDuplicateCarName(Car car) {
        long countDuplication = cars.stream()
                .filter(each -> each.isSameName(car))
                .count();
        if (countDuplication != NONE_DUPLICATION) {
            throw new RuntimeException(ErrorMessage.CAR_NAME_DUPLICATE.toString());
        }
    }

    public RaceResult getRaceResult() {
        return raceResult;
    }
}
