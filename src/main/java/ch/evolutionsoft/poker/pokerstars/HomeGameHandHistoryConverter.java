package ch.evolutionsoft.poker.pokerstars;

public class HomeGameHandHistoryConverter {

  /**
   * Club Id part is removed
   */
  public static final String CLUB_ID_PREFIX = " {Club #";
  public static final String CLUB_ID_SUFFIX = "}";
  public static final String EMPTY_REPLACEMENT = "";
  
  /**
   * Hold'em Pot Limit gets replaced by Omaha Limit to
   * support Small Stakes Omaha Hold'em Manager
   */
  public static final String POT_PART = "Hold'em Pot";
  public static final String POT_PART_REPLACEMENT = "Omaha";

  /**
   * Remove Home Game part for each Hand
   */
  public static final String HAND_NAME_PART = "PokerStars Home Game Hand";
  public static final String HAND_NAME_PART_RELACEMENT = "PokerStars Hand";

  /**
   * Replace Play Money Buy In by fix amount of 50$ + 0$ Rake.
   */
  public static final String BUY_IN_PART = "\\d+[+]\\d+";
  public static final String BUY_IN_PART_REPLACEMENT = "\\$50+\\$0 USD";
  
  public static final String DEFAULT_CLUB_ID = "3524603"; 
  protected String clubId = DEFAULT_CLUB_ID;
  
  public HomeGameHandHistoryConverter(String clubId) {
    
    this.clubId = clubId;
  }
  
  public String convertHomeGameHands(String inputFileString) {
    
    String resultString = inputFileString.replace(CLUB_ID_PREFIX + clubId + CLUB_ID_SUFFIX, EMPTY_REPLACEMENT);
    resultString = resultString.replace(HAND_NAME_PART, HAND_NAME_PART_RELACEMENT);
    resultString = resultString.replace(POT_PART, POT_PART_REPLACEMENT);
    
    return resultString.replaceAll(BUY_IN_PART, BUY_IN_PART_REPLACEMENT);
  }
}
