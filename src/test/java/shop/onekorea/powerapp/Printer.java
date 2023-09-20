package shop.onekorea.powerapp;

public class Printer {

    private String printerName;

    public Printer(String printerName) {
        this.printerName = printerName;
    }

    public String print(String message) {
        System.out.println("Printing " + message);
        String printerChars = "<" + printerName + ">" + message + "</" + printerName + ">";

        return printerChars;
//        return null;
    }

}
