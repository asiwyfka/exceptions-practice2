import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadString {

    public ArrayList<String> readStringsFromFileToCopyToStringList(String fileName) {
        System.out.println();
        ArrayList<String> listOfStrings = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(fileName))) {
            System.out.println("Sleduyshie stroki zapisany v stringList iz faila " + "'" + fileName + "'" + ":");
            String line;
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                System.out.println(line);
                listOfStrings.add(line);
            }
        } catch (IOException e) {
            System.err.println("Oshibka pri chtenii iz faila: " + e.getMessage());
        }
        return listOfStrings;
    }

}
