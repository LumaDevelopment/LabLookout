package net.lumadevelopment;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;

import java.util.Timer;

public class App {

   public static void main(String[] args) {

      /*
      My general philosophy with object-oriented programming is to make the
      main class as simple as possible. Obviously with some projects like
      Android apps, that's not possible. But with this one, it's easy!
      Just connect to the Discord api and schedule our lab querying task.
       */

      DiscordApi api = new DiscordApiBuilder().setToken(Config.TOKEN).login().join();
      System.out.println("Discord api connection initialized!");

      Timer timer = new Timer();

      // Sets the run() function in LabsQuery to run every
      // LAB_CHECK_INTERVAL seconds.
      timer.scheduleAtFixedRate(new LabsQuery(api), 0, Config.LAB_CHECK_INTERVAL * 1000);

      System.out.println("Lab query scheduled.");

   }

}