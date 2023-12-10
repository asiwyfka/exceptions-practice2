import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {

        //  Арбуз: 10 р, 3 шт

        System.out.println("Введи информацию по продукту согласно шаблона - \"<текст1>: <число1> р, <число2> шт/кг\"");
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        checkString1(str);
        System.out.println("");
        checkString2(str);
    }

    private static void checkString1(String str) {
        try {
            Pattern pattern = Pattern.compile("^(.*): (.*) р, (.*) шт$");
            Matcher matcher = pattern.matcher(str);
            if (!matcher.matches())
                throw new LineFormatException("Строка введена не согласно шаблона.");
            Product product = new Product(matcher.group(1), Integer.parseInt(matcher.group(2)), Integer.parseInt(matcher.group(3)));
            if ((product.getPrice() < 0) | (product.getAmount() < 0))
                throw new LineFormatException("В цене и/или количестве не должны быть введены отрицательные цифры.");
            System.out.println(product.toString());
            System.out.println("Строка корректна.");
        } catch (LineFormatException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("В цене и/или количестве должны быть введены цифры. " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Случилось непредвиденное исключение. " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void checkString2(String s) {
        try {
            Pattern pattern = Pattern.compile("([:] )|( [р][,] )|( [ш][т])");
            String[] words = (pattern.split(s));
            System.out.println(Arrays.toString(words));
            System.out.println(words[0]);
            System.out.println(words[1]);
            System.out.println(words[2]);
            Product product = new Product(words[0], Integer.parseInt(words[1]), Integer.parseInt(words[2]));
            if (words[0].isBlank()) //TODO: Я не пойму как тут сделать так, чтобы можно было делать проверку для всего шаблона, когда я разделил конкретные элементы товара с помощью сплита. Кажется этот сопсоб нерациональный.
                throw new LineFormatException(("Вы не ввели название товара"));
            if ((product.getPrice() < 0) | (product.getAmount() < 0)) {
                throw new LineFormatException("В цене и/или количестве не должны быть введены отрицательные цифры.");
            }
            System.out.println(product.toString());
            System.out.println("Строка корректна.");
        } catch (LineFormatException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("В цене и/или количестве должны быть введены цифры. " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Случилось непредвиденное исключение. " + e.getMessage());
            e.printStackTrace();
        }
    }
}