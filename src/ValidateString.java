import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateString {

    public boolean validateString(String str) {
        Pattern pattern = Pattern.compile("^(.*): (.*) r, (.*) sht$");
        Matcher matcher = pattern.matcher(str);

        if (!matcher.matches())
            return false;

        String nameProduct = matcher.group(1);
        int price = Integer.parseInt(matcher.group(2));
        int amount = Integer.parseInt(matcher.group(3));

        if ((nameProduct.isEmpty()) || (nameProduct.length() > 20) || (price < 0) || (amount < 0))
            return false;
        return true;
    }
}