import java.util.Scanner; // подключаем библиотеку java

public class Calculator {
    public static void main(String[] args) { // вход в программу

        Scanner in = new Scanner(System.in); // подключеам класс

        while (true) {

            System.out.println("Для выхода введите 'Выход'");
            System.out.println("Введите выражение: ");
            String str = in.nextLine(); // ожидание ввода строки используя метод nextLine()

            if (str.equals("Выход")) { // проверяем хочет ли пользователь выйти
                exitCalculator();
                break; // прерываем выполнение
            }

            try { // отслеживаемый блок
                str = str.replaceAll(" ", ""); // убираем все пробелы, для возможности ввода 1+2 и 1 + 2
                if (str.indexOf("+") != -1) { // определяем наличие символа
                    str = str.replace("+", " + "); // добавляем по одному пробелу с каждой стороны от оператора
                } else if (str.indexOf("-") != -1) {
                    str = str.replace("-", " - ");
                } else if (str.indexOf("*") != -1) {
                    str = str.replace("*", " * ");
                } else if (str.indexOf("/") != -1) {
                    str = str.replace("/", " / ");
                }
                else  throw new Exception("Оператор не опознан! Могут использоваться только операторы +,-,*,/. Строка не является математической операцией."); // вызываем исключение

                String[] symbols = str.split(" "); // разделяем строку на операнды
                if (symbols.length > 3) throw new Exception("Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");  // проверяем количество операндов и вызываем исключение

                Number firstNumber = NumberService.parseAndValidate(symbols[0]); // проверяем значение
                Number secondNumber = NumberService.parseAndValidate(symbols[2], firstNumber.getType()); //проверяем значение и тип значения
                String result = ActionService.calculate(firstNumber, secondNumber, symbols[1]); // вычисление
                System.out.println("Результат: " + result + "\n");

            } catch (Exception e) { // исключение
                System.out.println(e.getMessage()); // получаем ошибку и выводем ее пользователю
                exitCalculator();
                break;
            }
        }

        in.close();// закрываем сканер
    }

    private static void exitCalculator() {

        System.out.println("До скорых встреч! Мне с Вами было интересно.");

    }
}


