package ch.evolutionsoft.poker.pokerstars;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.FileUtils;

public class HandHistoryFileConverter {

  public final static String CONVERTED_DIRECTORY_NAME = "HandHistoryConverted";
  
  public static final long THIRTY_SECONDS = 30000L;
  
  public static void main(String[] arguments) throws IOException, InterruptedException {

    String clubId = checkArguments(arguments);
    
    HomeGameHandHistoryConverter handHistoryConverter = 
        new HomeGameHandHistoryConverter(clubId);
    
    while (true) {
      
      File originalHandHistoryFolder = new File(arguments[0]);
      System.out.println("Using HandHistory Folder " + originalHandHistoryFolder);
      
      String copiedHandHistoryDirectoryName = originalHandHistoryFolder.getParent() + File.separator + CONVERTED_DIRECTORY_NAME;
      System.out.println("copiedDirectory " + copiedHandHistoryDirectoryName);
     
      for (String fileName : originalHandHistoryFolder.list()) {
        
        String copiedHandHistoryName = fileName.substring(0, fileName.indexOf("Hold") -  1) + " Omaha 0.5$ + 0$.txt";
        String copiedHandHistoryFullName = copiedHandHistoryDirectoryName + File.separator + copiedHandHistoryName;
        System.out.println("copiedHandHistoryFullName" + copiedHandHistoryFullName);
        
        File sourceFile = new File (originalHandHistoryFolder.getAbsolutePath() + File.separator + fileName);
        File copiedHandHistory = new File(copiedHandHistoryFullName);
        
        FileUtils.copyFile(sourceFile, copiedHandHistory);
        
        String convertedHandHistory = handHistoryConverter.convertHomeGameHands(
                FileUtils.readFileToString(copiedHandHistory, StandardCharsets.UTF_8));
        FileUtils.write(copiedHandHistory, convertedHandHistory, StandardCharsets.UTF_8);
      }
        
      Thread.sleep(THIRTY_SECONDS);
    }
  }

  protected static String checkArguments(String[] args) {
    
    if (null == args || 0 >= args.length) {
      
      throw new IllegalArgumentException("Please give absolute path of your PokerStars HandHistory folder"
          + " as first Java program argument.");
    }
    
    if (1 == args.length) {
      
      System.out.println("Using default club id " + HomeGameHandHistoryConverter.DEFAULT_CLUB_ID
          + " Please use adapted clubId with second program argument if necessary.");
      
      if (!new File(args[0]).isDirectory() ) {
        
        throw new IllegalArgumentException("Please use a directory as first Java program argument.");
      }
      
      return HomeGameHandHistoryConverter.DEFAULT_CLUB_ID;
    }
    
    return args[1];
  }

}
