import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        //2+3
        //X+V=XV
        Converter converter = new Converter();
        String[] actions = {"+", "-", "/", "*"};
        String[] regexActions = {"\\+", "-", "/", "\\*"};
        Scanner scn = new Scanner(System.in);
        System.out.print("Введите выражение: ");
        String exp = scn.nextLine();
        //Определяем арифметическое действие:
        int cointZnak = 0;//количество действий в выражении
        char[] array = exp.toCharArray();
        for (int i = 0; i < array.length; i++) //проверяем количество действий
        {
            if (array[i] == '+' | array[i] == '-' | array[i] == '*' | array[i] == '/')
            {
                ++cointZnak;
               // System.out.println(cointZnak + " количество знаков");
            }
        }

        int actionIndex = -1;
        for (int i = 0; i < actions.length; i++)
        {
            if (exp.contains(actions[i]))
            {
                actionIndex = i;
                System.out.println(actionIndex);
                break;
            }
        }



        //Если не нашли арифметического действия, завершаем программу
        if (actionIndex==-1| cointZnak>1)
        {
            System.out.println("Некорректное выражение");
            return;
        }

        //Делим строчку по найденному арифметическому знаку


        String[] data = exp.split(regexActions[actionIndex]);
        //Определяем, находятся ли числа в одном формате (оба римские или оба арабские)
        if (converter.isRoman(data[0]) == converter.isRoman(data[1]))
        {
            int a, b;
            //Определяем, римские ли это числа
            boolean isRoman = converter.isRoman(data[0]);
            if (isRoman)
            {
                //если римские, то конвертируем их в арабские
                //X+V
                //x-10
                //v - 5
                a = converter.romanToInt(data[0]);
                b = converter.romanToInt(data[1]);

            }
            else
            {
                //если арабские, конвертируем их из строки в число
                a = Integer.parseInt(data[0]);
                b = Integer.parseInt(data[1]);
            }
            if (a > 10 | b > 10)
            {
                System.out.println("Числа не могут быть больше 10!");
            }
            else
            {
                //выполняем с числами арифметическое действие
                int result;
                switch (actions[actionIndex])
                {
                    case "+":
                        result = a + b;
                        break;
                    case "-":
                        result = a - b;
                        break;
                    case "*":
                        result = a * b;
                        break;
                    default:
                        result = a / b;
                        break;
                }
                //15->XV


                if (isRoman)
                {
                    //если числа были римские, возвращаем результат в римском числе
                    System.out.println(converter.intToRoman(result ));
                }
                if (!isRoman)
                {
                    //если числа были арабские, возвращаем результат в арабском числе
                    System.out.println(result +" ответ");
                }




            }
        }
        else
        {
            System.out.println("Числа должны быть в одном формате");
        }


    }
}
