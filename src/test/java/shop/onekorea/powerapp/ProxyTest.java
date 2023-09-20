package shop.onekorea.powerapp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProxyTest {

    @Test
    public void printerTest() {
        Printer printer = new Printer("hanwha");
        String message = "첫번째 출력입니다!";
        String printedChars = printer.print(message);

        String expected = "<hanwha>" + message + "</hanwha>";
        assertEquals(expected, printedChars);


    }

}
