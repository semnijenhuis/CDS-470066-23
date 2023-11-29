package tests.menuTests;

import modal.Objects.Station;
import modal.menu.Printer;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class PrinterTest {

    @Test
    void mainMenu() {

        Printer printer = new Printer();

        // Simulate user input "42\n"
        String userInput = "2\n";
        InputStream inputStream = new ByteArrayInputStream(userInput.getBytes());

        // Redirect the standard input to use the inputStream
        System.setIn(inputStream);

        // Call the inputIntScanner method
        int result = printer.mainMenu();

        // Reset the standard input to the original value
        System.setIn(System.in);

        System.out.println(result);
        // Assert the result
        assertEquals(2, result);

    }

    @Test
    void mainMenuBW() {

        Printer printer = new Printer();
        String userInput = "a\n";
        InputStream inputStream = new ByteArrayInputStream(userInput.getBytes());

        // Redirect the standard input to use the inputStream
        System.setIn(inputStream);

        // Assert that the code throws a NoSuchElementException
        assertThrows(NoSuchElementException.class, () -> {
            printer.inputIntScanner(0, 3);
        });

        // Reset the standard input to the original value
        System.setIn(System.in);


    }

    @Test
    void routeMenu() {
        Printer printer = new Printer();

        String userInput = "3\n";
        InputStream inputStream = new ByteArrayInputStream(userInput.getBytes());

        // Redirect the standard input to use the inputStream
        System.setIn(inputStream);

        // Call the inputIntScanner method
        int result = printer.routeMenu("A", "B");

        // Reset the standard input to the original value
        System.setIn(System.in);

        System.out.println(result);
        // Assert the result
        assertEquals(3, result);

    }

    @Test
    void dijkstraMenu() {
        Printer printer = new Printer();


        String userInput = "2\n";
        InputStream inputStream = new ByteArrayInputStream(userInput.getBytes());

        // Redirect the standard input to use the inputStream
        System.setIn(inputStream);

        // Call the inputIntScanner method
        int result = printer.dijkstraMenu();

        // Reset the standard input to the original value
        System.setIn(System.in);

        System.out.println(result);
        // Assert the result
        assertEquals(2, result);
    }

    @Test
    void stationMenu() {
        Printer printer = new Printer();

        String userInput = "1\n";
        InputStream inputStream = new ByteArrayInputStream(userInput.getBytes());

        // Redirect the standard input to use the inputStream
        System.setIn(inputStream);

        // Call the inputIntScanner method
        int result = printer.stationMenu();

        // Reset the standard input to the original value
        System.setIn(System.in);

        System.out.println(result);
        // Assert the result
        assertEquals(1, result);
    }

    @Test
    void dataMenu() {
        Printer printer = new Printer();

        String userInput = "3\n";
        InputStream inputStream = new ByteArrayInputStream(userInput.getBytes());

        // Redirect the standard input to use the inputStream
        System.setIn(inputStream);

        // Call the inputIntScanner method
        int result = printer.dataMenu();

        // Reset the standard input to the original value
        System.setIn(System.in);

        System.out.println(result);
        // Assert the result
        assertEquals(3, result);
    }

    @Test
    void sortingMenu() {
        Printer printer = new Printer();

        String userInput = "4\n";
        InputStream inputStream = new ByteArrayInputStream(userInput.getBytes());

        // Redirect the standard input to use the inputStream
        System.setIn(inputStream);

        // Call the inputIntScanner method
        int result = printer.sortingMenu();

        // Reset the standard input to the original value
        System.setIn(System.in);

        System.out.println(result);
        // Assert the result
        assertEquals(4, result);
    }

    @Test
    void foundStationMenu() {
        Printer printer = new Printer();


        String userInput = "2\n";
        InputStream inputStream = new ByteArrayInputStream(userInput.getBytes());

        // Redirect the standard input to use the inputStream
        System.setIn(inputStream);

        Station station = new Station(1, "a", 1, "D", "E", "F", "G", "H", "I", 5,6);
        // Call the inputIntScanner method
        int result = printer.foundStationMenu(station);

        // Reset the standard input to the original value
        System.setIn(System.in);

        System.out.println(result);
        // Assert the result
        assertEquals(2, result);
    }

    @Test
    void stationOptionsSorting() {
        Printer printer = new Printer();

        String userInput = "8\n";
        InputStream inputStream = new ByteArrayInputStream(userInput.getBytes());

        // Redirect the standard input to use the inputStream
        System.setIn(inputStream);

        // Call the inputIntScanner method
        int result = printer.stationOptionsSorting();

        // Reset the standard input to the original value
        System.setIn(System.in);

        System.out.println(result);
        // Assert the result
        assertEquals(8, result);
    }

    @Test
    void findStationID() {
        Printer printer = new Printer();

        String userInput = "899\n";
        InputStream inputStream = new ByteArrayInputStream(userInput.getBytes());

        // Redirect the standard input to use the inputStream
        System.setIn(inputStream);

        // Call the inputIntScanner method
        int result = printer.findStationID();

        // Reset the standard input to the original value
        System.setIn(System.in);

        System.out.println(result);
        // Assert the result
        assertEquals(899, result);
    }

    @Test
    void findStationName() {
        Printer printer = new Printer();

        String userInput = "Amsterdam\n";
        InputStream inputStream = new ByteArrayInputStream(userInput.getBytes());

        // Redirect the standard input to use the inputStream
        System.setIn(inputStream);

        // Call the inputIntScanner method
        String result = printer.findStationName();

        // Reset the standard input to the original value
        System.setIn(System.in);

        System.out.println(result);
        // Assert the result
        assertEquals("Amsterdam", result);

    }

    @Test
    void findStationCode() {
        Printer printer = new Printer();

        String userInput = "AMS\n";
        InputStream inputStream = new ByteArrayInputStream(userInput.getBytes());

        // Redirect the standard input to use the inputStream
        System.setIn(inputStream);

        // Call the inputIntScanner method
        String result = printer.findStationCode();

        // Reset the standard input to the original value
        System.setIn(System.in);

        System.out.println(result);
        // Assert the result
        assertEquals("AMS", result);
    }

    @Test
    void findStationOn() {
        Printer printer = new Printer();

        String userInput = "1\n";
        InputStream inputStream = new ByteArrayInputStream(userInput.getBytes());

        // Redirect the standard input to use the inputStream
        System.setIn(inputStream);

        // Call the inputIntScanner method
        int result = printer.findStationOn();

        // Reset the standard input to the original value
        System.setIn(System.in);

        System.out.println(result);
        // Assert the result
        assertEquals(1, result);
    }

    @Test
    void findStationAVL() {
        Printer printer = new Printer();


        String userInput = "0\n";
        InputStream inputStream = new ByteArrayInputStream(userInput.getBytes());

        // Redirect the standard input to use the inputStream
        System.setIn(inputStream);

        // Call the inputIntScanner method
        int result = printer.findStationAVL();

        // Reset the standard input to the original value
        System.setIn(System.in);

        System.out.println(result);
        // Assert the result
        assertEquals(0, result);
    }

    @Test
    void searchOptions() {
        Printer printer = new Printer();

        String userInput = "2\n";
        InputStream inputStream = new ByteArrayInputStream(userInput.getBytes());

        // Redirect the standard input to use the inputStream
        System.setIn(inputStream);

        // Call the inputIntScanner method
        int result = printer.searchOptions();

        // Reset the standard input to the original value
        System.setIn(System.in);

        System.out.println(result);
        // Assert the result
        assertEquals(2, result);
    }

    @Test
    void inputIntScanner() {
        Printer printer = new Printer();

        String userInput = "50\n";
        InputStream inputStream = new ByteArrayInputStream(userInput.getBytes());

        // Redirect the standard input to use the inputStream
        System.setIn(inputStream);

        // Call the inputIntScanner method
        int result = printer.inputIntScanner(0, 557);

        // Reset the standard input to the original value
        System.setIn(System.in);

        System.out.println(result);
        // Assert the result
        assertEquals(50, result);
    }

    @Test
    void inputStringScanner() {
        Printer printer = new Printer();

        String userInput = "Random\n";
        InputStream inputStream = new ByteArrayInputStream(userInput.getBytes());

        // Redirect the standard input to use the inputStream
        System.setIn(inputStream);

        // Call the inputIntScanner method
        String result = printer.inputStringScanner();

        // Reset the standard input to the original value
        System.setIn(System.in);

        System.out.println(result);
        // Assert the result
        assertEquals("Random", result);
    }

    @Test
    void whichSort() {
        Printer printer = new Printer();

        // Simulate user input "42\n"
        String userInput = "1\n";
        InputStream inputStream = new ByteArrayInputStream(userInput.getBytes());

        // Redirect the standard input to use the inputStream
        System.setIn(inputStream);

        // Call the inputIntScanner method
        int result = printer.whichSort();

        // Reset the standard input to the original value
        System.setIn(System.in);

        System.out.println(result);
        // Assert the result
        assertEquals(1, result);
    }
}
