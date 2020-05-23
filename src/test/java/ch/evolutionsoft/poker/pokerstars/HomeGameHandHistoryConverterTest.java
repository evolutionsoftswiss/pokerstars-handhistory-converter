package ch.evolutionsoft.poker.pokerstars;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

public class HomeGameHandHistoryConverterTest {

  @Test
  public void testConvertHomeGameHands() throws IOException {
    
    File inputFile = new File("src/main/resources/HH20200426 T2881162815 Hold'em-Omaha 17K + 3,000.txt");
    
    String inputFileString = FileUtils.readFileToString(inputFile, StandardCharsets.UTF_8);
    
    String expectedFirstLine = "PokerStars Hand #212773957045: Tournament #2881162815, "
        + "$0.5+$0 USD Mixed PLH/PLO (Omaha Limit) - Level I (10/20) - "
        + "2020/04/26 20:15:00 CET [2020/04/26 14:15:00 ET]";
    String convertedHandHistory =
        new HomeGameHandHistoryConverter(HomeGameHandHistoryConverter.DEFAULT_CLUB_ID)
        .convertHomeGameHands(inputFileString);
    
    //Additional character in front of first line from file
    String convertedHandHistoryFirstLine = convertedHandHistory.substring(1, convertedHandHistory.indexOf('\r'));
    
    assertEquals(expectedFirstLine.length(), convertedHandHistoryFirstLine.length());
    assertEquals(expectedFirstLine, convertedHandHistoryFirstLine);
  }
}
