# Lab Lookout

### Purpose
Lab Lookout is a Discord bot I made to track when a new lab was posted for my CSE 1002 class. 
At regular intervals it grabs the source code of the lab index page and checks all the labs that are posted.
- If there is a new lab it hasn't seen before, it will add that to its list of seen labs and make a Discord post about it.
- If it has seen a lab before, it won't do anything.
- If a lab that it has seen before is removed, it removes that lab from the list of seen labs (just in case there are two labs with the same ID).

### Usage
To use this bot, simply modify all the values in 
<a href="https://github.com/LumaDevelopment/LabLookout/blob/master/src/main/java/net/lumadevelopment/Config.java">Config.java</a> 
and run the program.

### To-Dos
- Sometimes when labs are posted on the index page they are still marked as Forbidden an inaccessible. 
One possible addition could be to have the bot not mark the lab as seen if the lab is still Forbidden.
- Better error handling for unexpected page content (could be useful if the server is down).