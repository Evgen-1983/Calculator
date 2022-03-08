public class ActionService {

    public static String calculate(Number first, Number second, String action) throws Exception { //обработка с исключением

        int result;

        switch (action) { // Выбираем операнд
            case "+":
                result = first.getValue() + second.getValue(); // вычисление
                break;
            case "-":
                result = first.getValue() - second.getValue();
                break;
            case "*":
                result = first.getValue() * second.getValue();
                break;
            case "/":
                result = first.getValue() / second.getValue();
                break;
            default:
                throw new Exception("Оператор не опознан, используйте только +, -, *, /");
        }

        if (first.getType() == NumberType.ROMAN) {
            return NumberService.toRomanNumber(result);
        } else return String.valueOf(result);
    }
}