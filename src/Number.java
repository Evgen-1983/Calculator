class Number {

    private int value;
    private NumberType type;

    Number(int value, NumberType type) {
        this.value = value; // возвращаем значение вызвавшему
        this.type = type; // возвращаем тип вызвавшему
    }

    int getValue() {
        return value;
    }

    NumberType getType() {
        return type;
    }
}