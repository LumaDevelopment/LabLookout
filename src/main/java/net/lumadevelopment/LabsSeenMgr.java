package net.lumadevelopment;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * File management class. This class is responsible for
 * adding, removing, and retrieving lab IDs that the
 * bot has seen.
 */
public class LabsSeenMgr {

   private final File labsSeen;

   public LabsSeenMgr() {

      labsSeen = new File(Config.DATA_FILE);

      // Basically, create the file if it doesn't already exist
      if (!labsSeen.exists()) {

         try {

            labsSeen.createNewFile();

         } catch (IOException e) {

            System.err.println(
                  "IOException while trying to create labs seen file at " + Config.DATA_FILE);
            e.printStackTrace();

         }

      }

   }

   // Add new seen lab ID to file
   public void addLabID(String labId) {

      System.out.println("Adding lab " + labId + " to " + Config.DATA_FILE + "...");

      List<String> seenLabIDs = getSeenLabIDs();
      seenLabIDs.add(labId);
      writeToFile(seenLabIDs);

      System.out.println("Lab " + labId + " added to " + Config.DATA_FILE + "!");

   }

   // Remove seen lab ID from file
   public void removeLabID(String labId) {

      System.out.println("Removing lab " + labId + " from " + Config.DATA_FILE);

      List<String> seenLabIDs = getSeenLabIDs();
      seenLabIDs.remove(labId);
      writeToFile(seenLabIDs);

      System.out.println("Lab " + labId + " removed from " + Config.DATA_FILE + "!");

   }

   private void writeToFile(List<String> seenLabIDs) {

      // Avoid appending to existing file
      if (labsSeen.exists()) {
         labsSeen.delete();
      }

      try {

         PrintWriter outFile = new PrintWriter(labsSeen);

         // Write lab IDs to file again, this time with the removed one missing
         for (String seenLabId : seenLabIDs) {
            outFile.println(seenLabId);
         }

         // Close up our writer (good practice)
         outFile.close();

      } catch (FileNotFoundException e) {

         System.err.println("FileNotFoundException while trying to write to labs seen file.");
         e.printStackTrace();

      }

   }

   // Read all lab IDs from file
   public List<String> getSeenLabIDs() {

      System.out.println("Reading seen lab IDs from " + Config.DATA_FILE);

      // Always instantiate so that if there's an error we don't return null
      List<String> seenLabIDs = new ArrayList<>();

      try {

         Scanner scanner = new Scanner(labsSeen);

         while (scanner.hasNextLine()) {

            String line = scanner.nextLine();

            // Don't include empty lines as lab ID
            if (!line.equals("")) {
               seenLabIDs.add(line);
            }

         }

         System.out.println(seenLabIDs.size() + " seen lab IDs read from " + Config.DATA_FILE);

      } catch (FileNotFoundException e) {

         System.err.println("FileNotFoundException while trying to get seen lab IDs");
         e.printStackTrace();

      }

      return seenLabIDs;

   }

}
