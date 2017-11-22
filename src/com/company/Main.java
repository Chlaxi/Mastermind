package com.company;

import com.company.Game.*;
import com.company.Gui.*;

/*      Created by: Chloe Parbst
        Greetings! This is my take, on the mini-project, Mastermind, as part of the Essential Computing course
        I made some references in the comments, to make it easier to navigate in the code.
        Please note, that the given line, is only a proximity of the actual line.
*/

public class Main {

    public static void main(String[] args) {

        Game.setup();   //Calls the game startup        (Game line 30)
        GUI.a();       //This initializes the ActionListeners, for the buttons. (GUI line 40)
    }
}
