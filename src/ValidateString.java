import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateString {

    public boolean validateString(String str) throws ShablonException {
        Pattern pattern = Pattern.compile("^(.*): (.*) r, (.*) sht$");
        Matcher matcher = pattern.matcher(str);

        if (!matcher.matches())
            throw new ShablonException("Stroka vvedena ne soglasno shablony.");

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
        return true;
    }
}
