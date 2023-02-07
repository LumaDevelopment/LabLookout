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
         "https://cs.fit.edu/~pkc/classes/ds/"; // where we're getting labs from
   public static final String LAB_ID_PREFIX = ".pdf\">HW"; // what comes right before the lab ID
   public static final String LAB_ID_SUFFIX = "</a> --- <a href=\"src/"; // what comes right after the lab ID
   public static final String HOMEWORK_SECTION_START = "Submit Server";
   public static final String HOMEWORK_SECTION_END = "!--";
   public static final Long LAB_CHECK_INTERVAL = 60L; // in seconds

   // Server, channel, and role to target for new lab messages
   public static final Long SERVER_ID = 1050271061272432641L;
   public static final Long CHANNEL_ID = 1072588481815130242L;
   public static final Long ROLE_ID = 1050271061272432642L;

}
