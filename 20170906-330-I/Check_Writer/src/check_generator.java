import java.util.Scanner;

public class check_generator {

    public static String result = "";
    public static String[] smallNames = {"One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten",
            "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen",
            "Nineteen"};
    public static String[] tens = {"Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    public static String[] seperators = {"Hundred", "Thousand", "Hundred Thousand", "Million", "Hundred Million"};
    public static void main(String[] args) {
        //Seven hundred forty two thousand, three hundred eighty eight dollars and fifteen cents.
        //742388.15
        //Twelve dollars and eleven cents.
        //12.11

        Scanner s = new Scanner(System.in);
        System.out.println("Please give me a number from 0.00 to 999,999,999.99");
        Double amount = new Double(s.nextDouble());

        if (amount < 20) {
            result = smallCheck(amount) + cents(amount);
        }
        for (int i = amount.toString().length(); i > 0; i--) {

        }

        System.out.println(result);
    }

    private static String smallCheck(Double amount) {
        return result = smallNames[amount.intValue()-1] + " dollars";
    }

    public static String cents(Double amount) {

        String sAmount = String.format(String.valueOf(amount));
        if (sAmount.substring(sAmount.indexOf('.')+1).equals("0")) {
            return "";
        }

        System.out.println(amount + " " + sAmount);
        String centAmt = " and ";
        if (Integer.valueOf(sAmount.substring(sAmount.indexOf('.')+1,sAmount.indexOf('.')+2)) > 0) {
            // check if tens place for cents is >0
            int tensPlace = Integer.parseInt(sAmount.substring(sAmount.indexOf('.')+1, sAmount.indexOf('.')+2));
            centAmt += tens[tensPlace-1].toLowerCase() + " ";
        }
        if (sAmount.substring(sAmount.indexOf('.')).length() > 2) {
            // check if there is a >0 number in the ones place for cents
            int onesPlace = Integer.parseInt(sAmount.substring(sAmount.indexOf('.') + 2));
            centAmt += smallNames[onesPlace-1].toLowerCase() + " ";
        }

        centAmt += "cents.";
        return centAmt;
    }
}
