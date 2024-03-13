import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TransformStringToProduct {

    private ValidateString validateString = new ValidateString();

    public ArrayList<Product> transformStringListToProductList(ArrayList<String> stringList) {
        System.out.println();
        ArrayList<Product> listOfProducts = new ArrayList<>();
        for (String str : stringList) {
            try {
                if (validateString.validateString(str)) {
                    Pattern pattern = Pattern.compile("^(.*): (.*) r, (.*) sht$");
                    Matcher matcher = pattern.matcher(str);
                    if (matcher.matches()) {
                        String nameProduct = matcher.group(1);
                        int price = Integer.parseInt(matcher.group(2));
                        int amount = Integer.parseInt(matcher.group(3));

                        Product product = new Product(nameProduct, price, amount);
                        listOfProducts.add(product);
                        System.out.println();
                        System.out.println("Validatsiya prowla yspeshno. Stroka dobalena v listOfProducts!");
                    }
                }
            } catch (ShablonException | NumberFormatException e) {
                System.out.println();
                System.out.println("Validatsiya ne prowla. Stroka ne dobalena v listOfProducts!");
                System.out.println(e.getMessage());
                e.printStackTrace();
            }


        }
        return listOfProducts;
    }
}
