import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        // Сделать ограничение на ввод имени продукта не более 20 символов, а также чтобы выдавал ошибку при пустой строке (ранее это не было реализовано). Сделал.
        // Сделать свою иерархию исключений. Сделал для исключений с именем продукта, неправильных цены и количества от ShablonException (для случая отрицательных чисел). Но при парсинге строк в числа используется NumberFormatException (Это верно?)
        // Указать, что именно ввёл пользователь неправильно. Хз, правильно ли реализовал. Особенно для NumberFormatException и Exception.

        // дз на минуте 23.

        // Прочитать все продукты из файла File перенести их в список тех продуктов, что отвечают нужным условиям, либо просто перенести в список и уже из него выбирать нужные продукты
        // Из этого списка записать продукты в новый файл в формате.
        /* ________________
        Продукт:
        Цена:
        Количество:
        Общая стоимость запасов:
         */

        //  arbuz: 50 r, 2 sht

        /* Первая реализация работы программы
        System.out.println("Vvedi info po producty soglasno shablona - \"<text1>: <chislo1> r, <chislo2> sht\"");
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        WorkingWithString.checkString(str);
        WorkingWithString.readStringsFromFile("File");
        ArrayList<String> stringProductList = WorkingWithString.readStringsFromFileToCopyToList("File 2");
        WorkingWithString.writeStringsToFileFromStringProductList("File 3", stringProductList);
        */

        //Вторая реализация работы программы

        ReadString readString1 = new ReadString();
        ArrayList<String> listOfStrings = readString1.readStringsFromFileToCopyToStringList("File");


        TransformStringToProduct transformStringToProduct1 = new TransformStringToProduct();

        for (String x : listOfStrings) {
            ValidateString validateString = new ValidateString();
            System.out.println();
            System.out.println(x);
            System.out.println(validateString.validateString(x));
        }


        ArrayList<Product> listOfProducts = transformStringToProduct1.transformStringListToProductList(listOfStrings);

        System.out.println(listOfProducts.toString());
        System.out.println();

        WriteProduct writeProduct1 = new WriteProduct();
        writeProduct1.writeProductListToFile("File 4", listOfProducts);
    }
}