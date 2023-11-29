package tests.utilsTests;

import modal.utils.RegularExpression;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class RegexFilterTest {

    @Test
    void filterStation() throws IOException {
        RegularExpression regex = new RegularExpression();

        String validID = "123";
        String validCode = "ABC";
        String validUIC = "456789";
        String validLatitude = "12.345";
        String validLongitude = "-98.765";

        assertTrue(regex.filterStation(validID, validCode, validUIC, validLatitude, validLongitude));

    }
    @Test
    void filterStationBW() throws IOException {
        RegularExpression regex = new RegularExpression();

        String invalidID = "A1";
        String validCode = "ABC";
        String invalidUIC = "45A789";
        String validLatitude = "12.345";
        String validLongitude = "-98.765";

        assertFalse(regex.filterStation(invalidID, validCode, invalidUIC, validLatitude, validLongitude));
    }

    @Test
    void filterTrack() throws IOException {
        RegularExpression regex = new RegularExpression();

        String validCodeFrom = "ABC";
        String validCodeTo = "EED";
        String validDistance = "123";

        assertTrue(regex.filterTrack(validCodeFrom, validCodeTo, validDistance));
    }

    @Test
    void filterTrackBW() throws IOException {
        RegularExpression regex = new RegularExpression();

        String invalidCodeFrom = "A1";
        String validCodeTo = "EED";
        String invalidDistance = "123A";

        assertFalse(regex.filterTrack(invalidCodeFrom, validCodeTo, invalidDistance));
    }

}
