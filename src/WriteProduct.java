import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class WriteProduct {
    public void writeProductListToFile(String fileName, ArrayList<Product> listOfProducts) {
        System.out.println();
        try (PrintWriter pw = new PrintWriter(new FileWriter(fileName))) {
            for (Product product : listOfProducts) {
                pw.write("________________");
                pw.println();
                pw.write("Product: " + product.getName());
                pw.println();
                pw.write("Cena: " + product.getPrice() + " r");
                pw.println();
                pw.write("Kolichestvo: " + product.getAmount() + " sht");
                pw.println();
                pw.write("Obshaya stoimost' zapasov: " + product.getPrice() * product.getAmount() + " r");
                pw.println();
            }
            System.out.println("Zapis' v file proshla yspeshno!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}