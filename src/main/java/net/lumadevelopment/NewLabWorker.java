package net.lumadevelopment;

import org.javacord.api.DiscordApi;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.permission.Role;
import org.javacord.api.entity.server.Server;

/**
 * Responsible for sending new lab messages to Discord.
 */
public class NewLabWorker {

   DiscordApi api;

   public NewLabWorker(DiscordApi api) {
      this.api = api;
   }

   /**
    * Sends new lab found to config-defined channel, tagging config-defined role.
    * @param id ID of lab to announce.
    */
   public void newLab(String id) {

      /*
      JavaCord works with Comparables, which means that we
      can check if something actually exists before we
      attempt to work with it without throwing an error.
      Pretty neat!
       */

      if (!api.getServerById(Config.SERVER_ID).isPresent()) {
         System.err.println("Cannot get server!");
         return;
      }

      Server server = api.getServerById(Config.SERVER_ID).get(); // Server the channel is in

      if (!server.getTextChannelById(Config.CHANNEL_ID).isPresent()) {
         System.err.println("Cannot get channel!");
         return;
      }

      TextChannel channel = server.getTextChannelById(Config.CHANNEL_ID).get(); // Channel to send to

      if (!server.getRoleById(Config.ROLE_ID).isPresent()) {
         System.err.println("Cannot get role!");
      }

      Role role = server.getRoleById(Config.ROLE_ID).get(); // Role to ping

      /*
      We can know that his lab URL is accurate because the value
      we read as the lab ID is the <a href> value. So, just append
      the lab ID to the configured labs URL and add a slash.
       */

      // Nothing fancy, but it works.
      String message = "**HW" + id + " released!**\n"
            + role.getMentionTag();

      // And the bot has done its one job!
      channel.sendMessage(message);

      System.out.println("New lab message sent!");

   }

}
