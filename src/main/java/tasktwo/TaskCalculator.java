package tasktwo;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayDeque;
import java.util.Deque;

public class TaskCalculator {

    public BigDecimal calculateValue(String task) {

        Deque<BigDecimal> numbers = new ArrayDeque<>();
        Deque<Character> operators = new ArrayDeque<>();

        for (int i = 0; i < task.length(); i++) {
            char character = task.charAt(i);
            if (Character.isDigit(character)) {
                BigDecimal number = BigDecimal.ZERO;
                while (Character.isDigit(character)) {
                    number = number.multiply(BigDecimal.TEN).add(new BigDecimal(new char[]{character}));
                    i++;
                    if (i < task.length())
                        character = task.charAt(i);
                    else
                        break;
                }
                i--;
                numbers.push(number);
            } else if (isOperator(character)) {
                while (!operators.isEmpty() && precedence(character) <= precedence(operators.peek())) {
                    var res = doOperation(numbers, operators);
                    numbers.push(res);
                }
                operators.push(character);
            }
        }

        while (!operators.isEmpty()) {
            var res = doOperation(numbers, operators);
            numbers.push(res);
        }
        return numbers.pop();
    }

    private int precedence(char character) {
        return switch (character) {
            case '*', '/' -> 2;
            case '+', '-' -> 1;
            default -> -1;
        };
    }

    private BigDecimal doOperation(Deque<BigDecimal> numbers, Deque<Character> operations) {
        var a = numbers.pop();
        var b = numbers.pop();
        char operation = operations.pop();
        return switch (operation) {
            case '-' -> b.subtract(a);
            case '+' -> a.add(b);
            case '/' -> b.divide(a, 3, RoundingMode.HALF_DOWN);
            case '*' -> a.multiply(b);
            default -> BigDecimal.ZERO;
        };

    }

    private boolean isOperator(char character) {
        return character == '/' || character == '*' || character == '+' || character == '-';
    }
}