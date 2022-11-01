public class StepTracker {
    int stepAim = 10_000; //Целевое количество шагов
    Converter converter = new Converter(75, 50);

    MonthData[] monthToData;
    public StepTracker() {
        monthToData = new MonthData[12];
        for (int i = 0; i < monthToData.length; i++) {
            monthToData[i] = new MonthData();
        }
    }

    //Вложенный класс с количеством дней в каждом месяце (30)
    public class MonthData {
        int[] monthDays = new int[30];

        int printDays() {
            for(int i = 0; i<monthDays.length; i++) {
                System.out.print((i+1) + " день: " + monthDays[i] + ", ");
            }
            return 0;
        }
    }

    //Сохранение количества шагов за определённый день
    void changeStepsAmount(int month, int day, int steps) {
        monthToData[month - 1].monthDays[day - 1] = steps;
        //-1 необходим, т.к. для пользователя 1-ый месяц - позиция 1, а для массива - позиция 0
    }

    //Изменение целевого количества шагов
    void changeStepAim (int aim) {
        stepAim = aim;
    }

    //Вывод статистики
    void printStatistics(int month) {
        System.out.println(); //Для отступа
        System.out.println("Статистика за " + (month) + " месяц.");
        System.out.println("Количество шагов по дням: ");
        System.out.println(monthToData[month-1].printDays());
        System.out.print("Общее количество шагов: ");
        int commonSteps = countMonthSteps(month - 1); //чтобы в дальнейшем не считать общее число шагов в других методах
        System.out.println(commonSteps);
        System.out.print("Максимальное количество шагов: ");
        System.out.println(maxMonthSteps(month - 1));
        System.out.print("Среднее количество шагов: ");
        System.out.println(averageMonthSteps((month - 1), commonSteps));
        System.out.print("Пройденная дистанция: ");
        System.out.println(converter.countDistance(commonSteps) + " (км)");
        System.out.print("Количество сожжённых калорий: ");
        System.out.println(converter.countKilocalories(commonSteps) + " (ккал)");
        System.out.println("Ваша лучшая серия длилась: " + bestSeries(month - 1) + " дней");
        System.out.println(); //Для отступа
    }

    //Подсчёт количества шагов за месяц
    int countMonthSteps (int month) {
        int monthSteps = 0;
        for(int i = 0; i < monthToData[month].monthDays.length; i++) {
            if(monthToData[month].monthDays[i] > 0){
                monthSteps += monthToData[month].monthDays[i];
            }
        }
        return monthSteps;
    }

    //Максимальное количество шагов за месяц
    int maxMonthSteps (int month) {
        int maxSteps = 0;
        for(int i = 0; i < monthToData[month].monthDays.length; i++) {
            if (monthToData[month].monthDays[i] > maxSteps) {
                maxSteps = monthToData[month].monthDays[i];
            }
        }
        return maxSteps;
    }

    //Среднее количество шагов за месяц
    int averageMonthSteps (int month, int commonSteps) {
        int aveSteps = commonSteps /= monthToData[month].monthDays.length;
        return aveSteps;
        //Использовал int, т.к. шаги нужно считать целыми (324,9843 шага - неподходящий формат для измерения именно шагов)
    }

    //Лучшая серия (количество подряд идущих дней, когда кол-во шагов было больше или больше и равно целевого)
    int bestSeries (int month) {
        int best = 0;       //Для сохранения лучшего значения серий по итогу всего месяца
        int secondBest = 0; //Для сохранения значения текущей серии
        for(int i = 0; i < monthToData[month].monthDays.length; i++) {
            if(monthToData[month].monthDays[i] >= stepAim) {
                secondBest++;
                if(i != (monthToData[month].monthDays.length - 1)) {
                    if (monthToData[month].monthDays[i + 1] >= stepAim) continue;
                    else {
                        if (secondBest > best) best = secondBest;
                        secondBest = 0;
                        i++; //Чтобы повторно не проверять день, который мы уже проверили
                    }
                }
                if (secondBest > best) best = secondBest;
            }
        }
        return best;
    }
}
