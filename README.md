# A5 Recursive Maze Explorer

Your readme should include the following information. Each student needs to submit their own reflection, even when pair programming.  Please write N/A if an item does not apply.

## Basic Information

Your Name: Abigail Lei

Other collaborators you worked with, including TAs (and feel free to give a shoutout to anyone who was particularly helpful): Everyone at office hours

Any references used besides JavaDoc and course materials:
https://www.geeksforgeeks.org/java/switch-statement-in-java/
https://www.w3schools.com/java/java_files_read.asp
https://www.geeksforgeeks.org/java/iterate-over-the-characters-of-a-string-in-java/?st_source=ai_mode 
https://www.geeksforgeeks.org/java/multidimensional-arrays-in-java/ 

## Reflection

Do you have any remaining questions about this assignment?
The maze information runs correctly in VSCode / locally, but not on Gradescope, any reason for this?

Reflection on your experience with this assignment:
I thought that it was very fun to be able to see the recursionn running because even though I have done it in the past, it can still be hard for me to visualize. It was very interesting to see my code run live. I thought that it was quite hard to figure out the file parsing and I ran into a lot of issues with that. I also had some issues with iteractions between maze and solveMaze but after I added some more functions into Maze, it worked out well (like setFinish or setContent)


solve:
base cases:
1) if its finish -> return true
2) if its unexplorable -> return false

if its not any of them, go to recursive call:
- explore to North, East, West, South