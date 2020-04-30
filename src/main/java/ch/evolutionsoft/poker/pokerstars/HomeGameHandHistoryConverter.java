package ch.evolutionsoft.poker.pokerstars;

public class HomeGameHandHistoryConverter {

  public static final String CLUB_ID_PART = " {Club #3524603}";
  public static final String CLUB_ID_PART_REPLACEMENT = "";
  
  public static final String HAND_NAME_PART = "PokerStars Home Game Hand";
  public static final String HAND_NAME_PART_RELACEMENT = "PokerStars Hand";
  
  public static final String BUY_IN_PART = "\\d+[+]\\d+";
  public static final String BUY_IN_PART_REPLACEMENT = "\\$50+\\$50+\\$0 USD";
  
  private HomeGameHandHistoryConverter() {
    // Hide constructor
  }
  
  public static String convertHomeGameHands(String inputFileString) {
    
    String resultString = inputFileString.replace(CLUB_ID_PART, CLUB_ID_PART_REPLACEMENT);
    resultString = resultString.replace(HAND_NAME_PART, HAND_NAME_PART_RELACEMENT);
    
    return resultString.replaceAll(BUY_IN_PART, BUY_IN_PART_REPLACEMENT);
  }
}
