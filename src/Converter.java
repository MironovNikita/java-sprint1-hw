public class Converter {
    float distance;
    float kilocalories;
    Converter (float dis, float kilo) {
        distance = dis;
        kilocalories = kilo;
    }

    //Перевод шагов в километры
    float countDistance (int steps) {
        float kilometers = (steps * distance)/100_000f;
        return kilometers;
    }

    //Перевод шагов в потраченные килокалории
    float countKilocalories (int steps) {
        float calories = (steps * kilocalories)/1_000;
        return calories;
    }
}
