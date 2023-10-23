package baseball.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Player {
    private static final int VALID_LENGTH = 3;
    private static final String ONLY_NUMBERS_FROM_ONE_TO_NINE_ALLOWED = "1 ~ 9 사이의 숫자만 입력이 가능합니다.";
    private static final String ONLY_NUMBERS_ALLOWED = "숫자만 입력이 가능합니다.";
    private static final String DUPLICATE_NUMBER_INPUT_NOT_ALLOWED = "중복 된 숫자는 입력할 수 없습니다.";
    private static final String ONLY_THREE_DIGITS_ALLOWED = "세 자리의 숫자만 입력이 가능합니다.";
    private final Numbers playerNumbers;

    public Player(String guessNumber) {
        validateInputNumber(guessNumber);
        this.playerNumbers = new Numbers(convertStringToIntegerList(guessNumber));
    }

    private void validateInputNumber(String guessNumber) {
        isDigits(guessNumber);
        isValidDigitLength(guessNumber);
        isUniqueDigits(guessNumber);
        isValidRange(guessNumber);
    }

    private List<Integer> convertStringToIntegerList(String guessNumber) {
        ArrayList<Integer> list = new ArrayList<>();
        for (char number : guessNumber.toCharArray()) {
            list.add(Character.getNumericValue(number));
        }
        return Collections.unmodifiableList(list);
    }

    public Numbers getPlayerNumbers() {
        return playerNumbers;
    }

    private static void isValidRange(String guessNumber) {
        for (char number : guessNumber.toCharArray()) {
            if (number == '0') {
                throw new IllegalArgumentException(ONLY_NUMBERS_FROM_ONE_TO_NINE_ALLOWED);
            }
        }
    }

    private static void isDigits(String guessNumber) {
        for (char number : guessNumber.toCharArray()) {
            if (!Character.isDigit(number)) {
                throw new IllegalArgumentException(ONLY_NUMBERS_ALLOWED);
            }
        }
    }

    private static void isUniqueDigits(String guessNumber) {
        Set<Character> duplicateSet = new HashSet<>();
        for (char number : guessNumber.toCharArray()) {
            duplicateSet.add(number);
        }
        if (duplicateSet.size() != guessNumber.length()) {
            throw new IllegalArgumentException(DUPLICATE_NUMBER_INPUT_NOT_ALLOWED);
        }
    }

    private static void isValidDigitLength(String guessNumber) {
        if (guessNumber.length() != VALID_LENGTH) {
            throw new IllegalArgumentException(ONLY_THREE_DIGITS_ALLOWED);
        }
    }
}