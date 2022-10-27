package net.lumadevelopment;

import org.javacord.api.DiscordApi;

import java.io.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;
import java.util.regex.Pattern;

/**
 * Web request code from https://zetcode.com/java/readwebpage/
 */
public class LabsQuery extends TimerTask {

   private final HttpClient client;
   private final LabsSeenMgr seenMgr;
   private final NewLabWorker newLabWorker;

   public LabsQuery(DiscordApi api) {
      // Instantiate all the objects we'll need for this.
      client = HttpClient.newHttpClient();
      seenMgr = new LabsSeenMgr();
      newLabWorker = new NewLabWorker(api);

      System.out.println("LabsQuery initialized!");
   }

   public void run() {

      HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(Config.LABS_URL))
            .GET()
            .build();

      try {

         // Attempt to get the content of the web page
         HttpResponse<String> response =
               client.send(request, HttpResponse.BodyHandlers.ofString());

         System.out.println("Http Response obtained for " + Config.LABS_URL);

         // If successful
         String pageContent = response.body();

         List<String> readLabIDs = new ArrayList<>();

         /*
         This loop goes through every lab ID on the page by
         looping through every instance of the lab ID prefix.

         Pattern.quote idea from https://stackoverflow.com/a/39038181
          */
         for (int i = 0; i < pageContent.split(Pattern.quote(Config.LAB_ID_PREFIX)).length - 1; i++) {

            String labId = pageContent
                  .split(Pattern.quote(Config.LAB_ID_PREFIX))[i + 1]
                  .split(Pattern.quote(Config.LAB_ID_SUFFIX))[0];

            readLabIDs.add(labId);

            System.out.println("Lab id read: " + labId);

         }

         // These are lab IDs we've already seen in the past
         List<String> seenLabIDs = seenMgr.getSeenLabIDs();

         for (String seenLabId : seenLabIDs) {

            // This triggers if a lab is taken down
            if (!readLabIDs.contains(seenLabId)) {

               // We remove the lab from our seen list, just
               // in case two labs have the same ID (unlikely)
               seenMgr.removeLabID(seenLabId);

               System.out.println("Lab " + seenLabId + " no longer on page!");

            }

         }

         // These are the lab IDs we saw on the page
         for (String readLabId : readLabIDs) {

            // If we've never seen this lab before (it's new)
            if (!seenLabIDs.contains(readLabId)) {

               // Add to our seen labs file.
               seenMgr.addLabID(readLabId);

               // Send off messaging to other object
               System.out.println("New lab! Sending to new lab worker.");
               newLabWorker.newLab(readLabId);

            }

         }

      } catch (IOException e) {

         System.err.println(
               "IOException while attempted to query labs page in LabsQuery thread!");
         e.printStackTrace();

      } catch (InterruptedException e) {

         System.err.println(
               "InterruptedException while attempted to query labs page in LabsQuery thread!");
         e.printStackTrace();

      }

   }

}
