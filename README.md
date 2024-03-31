Hello All!

The purpose of this Java application is to convert one currency to another in a user friendly interface.
To run my code: 
  - simply clone, the source code and run the ".class" file. You do not need an IDE, and because it is a ".class" file it will run on both MacOS as well as Windows.

This application is a Maven project, and uses a GET call to get current conversion rates from Exchange Rate API where they have over 100 currencies updated every hour. Due to how active they are with
updating their database, you can be sure to get the latest dollar amount when converting prices.

For anyone interested in the programming logic behind this:
1. guiBuilder is where the GUI components are built.
2. I only use the main method to call the guiBuilder class, as well as the buttonListener method.
    KEEP IN MIND: There are MANY MANY other ways to implement this. It can be implemented within the same class. I simply did it this way because it would be far easier to build other functionalities if I'd like to.
3. I've added plenty of comments throughout my code explaining what I am doing in an effort to minimize confusion.
