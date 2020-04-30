package ch.evolutionsoft.poker.pokerstars;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.FileUtils;

public class HandHistoryFileConverter {

  public final static String CONVERTED_DIRECTORY_NAME = "HandHistoryConverted";
  
  public static void main(String[] args) throws IOException, InterruptedException {
    
    while (true) {
      
      File originalHandHistory = new File(args[0]);
      String parentDirectoryName = originalHandHistory.getParent();
      System.out.println("parentDirectory " + parentDirectoryName);
      File parentDirectory = new File(parentDirectoryName);
      
      String copiedHandHistoryDirectoryName = parentDirectory.getParent() + File.separator + CONVERTED_DIRECTORY_NAME;
      System.out.println("copiedDirectory " + copiedHandHistoryDirectoryName);
      
      String fileName = originalHandHistory.getName();
      String copiedHandHistoryName = fileName.substring(fileName.indexOf("T"), fileName.indexOf("Hold") -  1);
      String copiedHandHistoryFullName = copiedHandHistoryDirectoryName + File.separator + copiedHandHistoryName;
      System.out.println("copiedHandHistoryFullName" + copiedHandHistoryFullName);
      
      File copiedHandHistory = new File(copiedHandHistoryFullName);
      
      FileUtils.copyFile(originalHandHistory, copiedHandHistory);
      
      String convertedHandHistory = 
          HomeGameHandHistoryConverter.convertHomeGameHands(
              FileUtils.readFileToString(copiedHandHistory, StandardCharsets.UTF_8));
      FileUtils.write(copiedHandHistory, convertedHandHistory, StandardCharsets.UTF_8);
      
      Thread.sleep(20000L);
    }
  }

}
