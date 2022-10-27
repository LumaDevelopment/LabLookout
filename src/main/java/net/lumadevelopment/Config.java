package net.lumadevelopment;

public class Config {

   /*
   This could've been read from a file. However, seeing as this
   isn't the sort of program that would be widely distributed,
   I think we're good. One improvement that might be practical is
   to move TOKEN to a command line argument rather or a standard
   input request rather than a Config value, but this works.
    */

   public static final String TOKEN =
         "<REPLACE ME WITH BOT TOKEN>";
   public static final String DATA_FILE = "seenLabs.txt"; // where we're saving seen labs

   public static final String LABS_URL =
         "<REPLACE ME LABS PAGE TO CHECK>"; // where we're getting labs from
   public static final String LAB_ID_PREFIX = "<REPLACE ME WITH HTML BEFORE LAB ID>"; // what comes right before the lab ID
   public static final String LAB_ID_SUFFIX = "<REPLACE ME WITH HTML AFTER LAB ID>"; // what comes right after the lab ID
   public static final Long LAB_CHECK_INTERVAL = 60L; // in seconds

   // Server, channel, and role to target for new lab messages
   public static final Long SERVER_ID = 1011261947238436864L;
   public static final Long CHANNEL_ID = 1035304320993861702L;
   public static final Long ROLE_ID = 1035304194426556526L;

}
