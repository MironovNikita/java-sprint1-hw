import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StepTracker stepTracker = new StepTracker();

        System.out.println("Добро пожаловать в \"Счётчик калорий\"!");
        //Работа с меню
        while (true) {
            printMenu();
            int userCommand; //Вводимая пользователем команда
            System.out.print("Введите команду (значение от 0 до 3): ");
            //Проверка вводимых данных с помощью функции checkIntInput
            userCommand = checkIntInput(scanner);

            //Обработка команд
            if (userCommand == 1) {
                System.out.println(); //Для отступа
                System.out.println("Выбрано: Ввести количество шагов за определённый день");
                while(true) {
                    System.out.print("Введите месяц (1-12): ");
                    int month = checkIntInput(scanner);
                    while (month < 1 || month > 12) {
                        System.out.print("Ошибка! Такого месяца не существует. Введите значение от 1 до 12: ");
                        month = checkIntInput(scanner);
                    }
                    System.out.print("Введите день (1-30): ");
                    int day = checkIntInput(scanner);
                    while (day < 1 || day > 30) {
                        System.out.print("Ошибка! Такого дня не существует. Введите значение от 1 до 30: ");
                        day = checkIntInput(scanner);
                    }
                    System.out.print("Введите количество шагов: ");
                    int steps = checkIntInput(scanner);
                    while (steps < 0) {
                        System.out.print("Вы идёте назад? :) Введите, пожалуйста, положительное значение: ");
                        steps = checkIntInput(scanner);
                    }
                    //Вызов команды изменения количества шагов за определённый день
                    stepTracker.changeStepsAmount(month, day, steps);
                    System.out.println("Успешно!");
                    System.out.println(); //Для отступа
                    System.out.print("Введите любое число, чтобы продолжить или введите 0, если хотите завершить внесение данных: ");
                    int cont = checkIntInput(scanner);
                    if (cont == 0) break;
                    else continue;
                }

            } else if (userCommand == 2) {
                System.out.println(); //Для отступа
                System.out.println("Выбрано: Напечатать статистику за определённый месяц");
                System.out.print("За какой месяц Вы хотите отобразить статистику (1-12): ");

                //Проверка месяца на int c дальнейшей проверкой верного номера месяца
                int month = checkIntInput(scanner);
                while (month < 1 || month > 12) {
                    System.out.print("Ошибка! Такого месяца не существует. Введите значение от 1 до 12: ");
                    month = checkIntInput(scanner);
                }
                //Вызов команды вывода статистики за определённый месяц
                stepTracker.printStatistics(month);

            } else if (userCommand == 3) {
                System.out.println(); //Для отступа
                System.out.println("Выбрано: Изменить цель по количеству шагов в день");
                System.out.println("Текущая цель по количеству шагов в день: " + stepTracker.stepAim);
                System.out.print("Введите новую цель: ");
                stepTracker.changeStepAim(checkIntInput(scanner));
                while (stepTracker.stepAim < 0) {
                    System.out.print("Ошибка! Введите положительное значение: ");
                    stepTracker.changeStepAim(checkIntInput(scanner));
                }
                System.out.println("Успешно!");
                System.out.println("Ваша новая цель по количеству шагов: " + stepTracker.stepAim);
            } else if(userCommand == 0) {
                System.out.println("Выход");
                break;
            }
            else System.out.println("Извините, такая команда отсутствует :с");
        }
    }

    //Вывод меню
    private static void printMenu() {
        System.out.println("Меню: ");
        System.out.println("1 - Ввести количество шагов за определённый день");
        System.out.println("2 - Напечатать статистику за определённый месяц");
        System.out.println("3 - Изменить цель по количеству шагов в день");
        System.out.println("0 - Выход");
    }

    //Проверка вводимых целочисленных данных
    public static int checkIntInput(Scanner scanner) {
        boolean isInt; //Для проверки введения целого числа
        int num = 0;
        do {
            if (scanner.hasNextInt()){
                num = scanner.nextInt();
                isInt = true;
                break;
            }
            else {
                System.out.print("Не могу распознать число. Введите числовое значение: ");
                isInt = false;
                scanner.next();
            }
        } while (!(isInt));
        return num;
    }
    /*Семён, здравствуйте! Приятно познакомиться :)
    Есть у меня такая привычка, писать себе везде и всюду комментарии) Есть почему-то чувство, что в каком-нибудь месте
    нужно написать комментарий, а то вдруг я забуду, что это и зачем (хотя Вы правы, в большинстве своём в данной
    программе всё понятно). Наверное, это придёт с опытом) Постараюсь учесть на будущее данный совет :)
    Спасибо за статью)
    Исправил Converter: без объявления новой переменной
    По поводу ТЗ... Вот это я невнимательный... Надеюсь, я правильно понял ошибку!
    */

}
