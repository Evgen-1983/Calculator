import java.util.Map;
import java.util.TreeMap;

class NumberService {

    private final static TreeMap<Integer, String> romanString = new TreeMap<>(); // подключаем дерево

    static {
        romanString.put(1, "I"); // добавляем значение в дерево
        romanString.put(4, "IV");
        romanString.put(5, "V");
        romanString.put(9, "IX");
        romanString.put(10, "X");
        romanString.put(40, "XL");
        romanString.put(50, "L");
        romanString.put(90, "XC");
        romanString.put(100, "C");
    }

    static Number parseAndValidate(String symbol) throws Exception { // класс проверки значения

        int value,tmp;
        NumberType type;
        try {
            String[] symbols = symbol.split("\\.|\\,");
            tmp = symbols.length;
            if (tmp!=1) throw new Exception("Используйте только целые числа от 1 до 10 включительно.");

            value = Integer.parseInt(symbol); //
            type = NumberType.ARABIC;
        } catch (NumberFormatException e) {
            value = toArabicNumber(symbol);
            type = NumberType.ROMAN;
        }

        if (value < 1 || value > 10) { // ограничение не более 10
            throw new Exception("Используйте числа от 1 до 10 включительно");
        }

        return new Number(value, type);
    }

    static Number parseAndValidate(String symbol, NumberType type) throws Exception {

        Number number = parseAndValidate(symbol);
        if (number.getType() != type) {
            throw new Exception("Используются одновременно разные системы счисления, используйте один тип счисления в вводных значениях");
        }

        return number;
    }

    private static int letterToNumber(char letter) {

        int result = -1;

        for (Map.Entry<Integer, String> entry : romanString.entrySet()) {
            if (entry.getValue().equals(String.valueOf(letter))) result = entry.getKey();
        }
        return result;
    }

    static String toRomanNumber(int number) throws Exception {

        if (number < 0) throw new Exception("В римской системе нет отрицательных чисел.");

        int i = romanString.floorKey(number);

        if (number == i) {
            return romanString.get(number);
        }
        return romanString.get(i) + toRomanNumber(number - i);
    }

    static int toArabicNumber(String roman) throws Exception {
        int result = 0;

        int i = 0, I = 0, II = 0, III = 0, IV = 0, V = 0, VI = 0, VII = 0, VIII = 0, IX = 0, X = 0;
        while (i < roman.length()) {
            char letter = roman.charAt(i);
            int num = letterToNumber(letter);
            switch (num) { // Выбираем операнд
                case 1:
                    if (I == 3)
                        throw new Exception("Неверное римское значение, правило запрещает употребление одной и той же цифры более 3 раз подряд.");
                    I++;
                    break;
                case 2:
                    if (II == 3)
                        throw new Exception("Неверное римское значение, правило запрещает употребление одной и той же цифры более 3 раз подряд.");
                    II++;
                    break;
                case 3:
                    if (III == 3)
                        throw new Exception("Неверное римское значение, правило запрещает употребление одной и той же цифры более 3 раз подряд.");
                    III++;
                    break;
                case 4:
                    if (IV == 3)
                        throw new Exception("Неверное римское значение, правило запрещает употребление одной и той же цифры более 3 раз подряд.");
                    IV++;
                    break;
                case 5:
                    if (V == 3)
                        throw new Exception("Неверное римское значение, правило запрещает употребление одной и той же цифры более 3 раз подряд.");
                    V++;
                    break;
                case 6:
                    if (VI == 3)
                        throw new Exception("Неверное римское значение, правило запрещает употребление одной и той же цифры более 3 раз подряд.");
                    VI++;
                    break;
                case 7:
                    if (VII == 3)
                        throw new Exception("Неверное римское значение, правило запрещает употребление одной и той же цифры более 3 раз подряд.");
                    VII++;
                    break;
                case 8:
                    if (VIII == 3)
                        throw new Exception("Неверное римское значение, правило запрещает употребление одной и той же цифры более 3 раз подряд.");
                    VIII++;
                    break;
                case 9:
                    if (IX == 3)
                        throw new Exception("Неверное римское значение, правило запрещает употребление одной и той же цифры более 3 раз подряд.");
                    IX++;
                    break;
                case 10:
                    if (X == 3)
                        throw new Exception("Неверное римское значение, правило запрещает употребление одной и той же цифры более 3 раз подряд.");
                    X++;
                    break;
            }


            if (num < 0) throw new Exception("Неверный римский символ");

            i++;
            if (i == roman.length()) {
                result += num;
            } else {
                int nextNum = letterToNumber(roman.charAt(i));
                if (nextNum > num) {
                    result += (nextNum - num);
                    i++;
                } else result += num;
            }
        }
        return result;
    }
}