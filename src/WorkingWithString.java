import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WorkingWithString {

    static Product checkString(String str) {
        System.out.println();
        Product product = new Product(); // я правильно создаю продукт для возвращения его методом?
        try {
            Pattern pattern = Pattern.compile("^(.*): (.*) r, (.*) sht$");
            Matcher matcher = pattern.matcher(str);

            if (!matcher.matches()) {
                throw new ShablonException("Stroka vvedena ne soglasno shablony.");
            }

            String nameProduct = matcher.group(1);
            int price = Integer.parseInt(matcher.group(2));
            int amount = Integer.parseInt(matcher.group(3));

            if (nameProduct.isEmpty())
                throw new NullNameException("Nazvanie producta ne dolzho bit' pustim! (" + nameProduct + ") - eto vveli vi.");
            if (nameProduct.length() > 20)
                throw new InCorrectNameException("Dlina name producta dolzhna bit' men'she 20 simvolov! (" + nameProduct + ") - eto vveli vi.");
            if (price < 0)
                throw new InCorrectPriceException("Cena producta doolzhna bit' polozhitel'nim chislom! (" + price + ") - eto vveli vi.");
            if (amount < 0)
                throw new InCorrectAmountException("Kolichestvo producta doolzhna bit' polozhitel'nim chislom! (" + amount + ") - eto vveli vi.");
            product = new Product(nameProduct, price, amount);
            System.out.println("Stroka korrektna.");
        } catch (InCorrectPriceException | InCorrectAmountException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (InCorrectNameException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (ShablonException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("V cene/kolichestve dolzhny vvodit' polozhitel'niye chisla!" + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Sluchilos' nepredvidennoe iskluchenie " + e.getMessage());
            e.printStackTrace();
        }
        return product;
    }


    static void writeStringsToFileFromStringProductList(String fileName, ArrayList<String> stringProductList) {
        System.out.println();
        try (PrintWriter pw = new PrintWriter(new FileWriter(fileName))) {

            for (String x : stringProductList) {
                boolean string = true;
                Pattern pattern = Pattern.compile("^(.*): (.*) r, (.*) sht$");
                Matcher matcher = pattern.matcher(x);
                System.out.println("Schitivayem stroky.");
                System.out.println(x);
                if (!matcher.matches()) {
                    string = false;
                    System.out.println("Stroka nekorrektna. Mi ne zapisivaem eyo v fail.");
                    System.out.println();
                }
                if (matcher.matches()) {

                    String nameProduct = matcher.group(1);
                    int price = Integer.parseInt(matcher.group(2));
                    int amount = Integer.parseInt(matcher.group(3));

                    if (nameProduct.isEmpty())
                        string = false;
                    else if (nameProduct.length() > 20)
                        string = false;
                    else if (price < 0)
                        string = false;
                    else if (amount < 0)
                        string = false;
                    if (string == false) {
                        System.out.println("Stroka nekorrektna. Mi ne zapisivaem eyo v fail.");
                        System.out.println();
                    } else {
                        System.out.println("Stroka korrektna. Mi zapisivaem eyo v fail.");
                        System.out.println();
                        pw.write("________________");
                        pw.println();
                        pw.write("Product: " + nameProduct);
                        pw.println();
                        pw.write("Cena: " + price + " r");
                        pw.println();
                        pw.write("Kolichestvo: " + amount + " sht");
                        pw.println();
                        pw.write("Obshaya stoimost' zapasov: " + price * amount + " r");
                        pw.println();
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    static void readStringsFromFile(String fileName) {
        System.out.println();
        try (Scanner scanner = new Scanner(new File(fileName))) {
            System.out.println("Chtenie strok iz faila " + "'" + fileName + "'" + ":");
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.err.println("Oshibka pri chtenii iz faila: " + e.getMessage());
        }
    }

    static ArrayList<String> readStringsFromFileToCopyToList(String fileName) {
        System.out.println();
        ArrayList<String> listOfProduct = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(fileName))) {
            System.out.println("Sleduyshie stroki zapisany v stringProductList iz faila " + "'" + fileName + "'" + ":");
            String line;
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                System.out.println(line);
                listOfProduct.add(line);
            }
        } catch (IOException e) {
            System.err.println("Oshibka pri chtenii iz faila: " + e.getMessage());
        }
        return listOfProduct;
    }
}
