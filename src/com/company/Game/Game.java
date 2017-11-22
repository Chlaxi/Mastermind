package com.company.Game;

import com.company.Gui.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;


/**
 * Mastermind Created by Chloe Parbst on 04-11-2015.
 */
public class Game
{

    public static int turnNum = 0;      //The current turn number.
    public static GUI gui = new GUI();  //Declares the gui.


    public static String[] color = new String[] {"Red","Blue","Green","Yellow","Purple","Orange"};  //An array of valid colors

    public static String[] guesses = new String[4]; //Defines the number of guesses per turn.. This is the object, the user manipulates
    public static ArrayList<String> whatToGuess = new ArrayList<>();    //The ArrayList of the answers, the user should guess.

    //Guesses means the user's guesses, while answers(whatToGuess) means the randomized color, that the user should try to match.

//Setup function. Should only be called once per game (Upon restart).
    public static void setup()
    {
        gui = new GUI();    //This creates a new GUI, based on the static gui variable above.
        whatToGuess.clear(); //Makes sure, that the answers are cleared.
        turnNum = 0;         //Makes sure, that you always start at turn 0.

        //This for loop fills the answers.
        for (int i = 0; i < guesses.length; i++)
        {
            Random random = new Random();   //Creates a new Random
            whatToGuess.add(color[random.nextInt(color.length)]);  //And chooses a random color, based on the Random, which is added to the answers ArrayList.
            guesses[i] = color[0];      //Sets a default color of the guesses to color's first value, so the games doesn't crashes.
        }
        //System.out.print(whatToGuess+" "); //Debugging, to see the answers.
        gui.guiSetup();     //Runs the setup of the GUI (GUI line 51).
    }


    //This function will compare the user's input, with whatToGuess, and grant feedback.
    public void check()     //(Called from MyActionListener line 41)
    {
        String text;            //The input in the popUp
        int correctPlace = 0;   //Declares the correct placement counter.

        //This array makes sure, that the checked guess isn't used as a reference already.
        boolean[] used = new boolean[guesses.length];
        for (int i = 0; i < used.length; i++) {
            used[i] = false;        //Fills the used array with false, so it resets upon each check.
        }

    //Compares the user's colors, with the answer's colors. i represents the user's guesses, j represents the answers.
        for (int i = 0; i < guesses.length; i++)  //Goes through the user's colors..
        {
            for (int j = 0; j < guesses.length; j++)  //..and compares the user's color with the answers..
            {
                if(guesses[i].equals(whatToGuess.get(j)) && !used[j]) //..if the answer hasn't been compared yet..
                {                                                         //..and the color is the same..
                    used[j]=true;   //The answer has been used as a reference, and used for that position is therefore true..
                    gui.feedColor(Color.yellow,j);  //Since the color is correct, mark it with yellow (GUI line 144).
                    break;
                }
            }
            //If the checked button's color matches the answers color..
            if(guesses[i].equals(whatToGuess.get(i)))
            {
                gui.feedColor(Color.green,i);   //.. the feedback is marked with a green label. (GUI line 144)..
                correctPlace++; //.. And the value of correct placements are increased.
            }
        }

        gui.visualColor();   //Calls the visualColor function, so the user can get feedback (GUI line 131);

    //This is the line, which determines, whenever the game is over or not.
        if(correctPlace == guesses.length)  //If the number of correct placements is the same as the length of the guesses..
        {
            text = "You did it! You guessed the combination!";
            gui.displayText(text);      //Display a message in a popUp textbox (GUI line 176).
        }
        else    //..otherwise
        {

            turnNum++;  //Increases the turn number..
            if(turnNum == 10)   //And if it equals 10 (the number of rows)..
            {
                text = "You failed. Would you like to try again?";
                gui.displayText(text);      //..The popUp textbox also appears, but with a different message.
            }
        }
    }

}
