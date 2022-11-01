public class Converter {
    float distance;
    float kilocalories;
    Converter (float dis, float kilo) {
        distance = dis;
        kilocalories = kilo;
    }

    //Перевод шагов в километры
    float countDistance (int steps) {
        return (steps * distance)/100_000f;
    }

    //Перевод шагов в потраченные килокалории
    float countKilocalories (int steps) {
        return (steps * kilocalories)/1_000f;
    }
}
