package org.example;
import java.io.IOException;

public class converter {
    public static void main(String[] args) throws IOException {
        //Calling the guiBuilder class, and calling the method buttonListeners within the class.
        guiBuilder g = new guiBuilder();
        g.buttonListeners();
    }

}



