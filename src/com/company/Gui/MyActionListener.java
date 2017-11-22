package com.company.Gui;

import com.company.Game.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Mastermind Created by Chloe Parbst on 18-11-2015.
 */
public class MyActionListener implements ActionListener
{

    Game game = new Game();
    GUI gui = new GUI();
    int[] x = new int[4];   //The buttons position. Array of 4, since there are four buttons.

 //This is the default ActionListener event function, which checks for clicks, on the specified buttons.
    public void actionPerformed(ActionEvent e)
    {

        if(e.getSource() == GUI.btn0)  //For instance: If btn0 is clicked...
        {
            ButtonValueChange(GUI.btn0, 0); //..Go to the  ButtonValueChange function (line 54), with the buttons name and position.
        }
        if(e.getSource() == GUI.btn1)
        {
            ButtonValueChange(GUI.btn1,1);
        }
        if(e.getSource() == GUI.btn2)
        {
            ButtonValueChange(GUI.btn2,2);
        }
        if(e.getSource() == GUI.btn3)
        {
            ButtonValueChange(GUI.btn3,3);
        }
        if(e.getSource() == GUI.validate)
        {
            game.check();   //The validate button should run Game.check (Game line 49).
        }
        if(e.getSource() == GUI.tryAgain)   //Should display a message, which prompts the user, to try again.
        {
            /*This one is a bit tricky. I need to call the Game's static gui's displaytext.
            otherwise, the creates a new window, without removing the previous window,
            since it's technically referring to another GUI object.*/
            Game.gui.displayText("Are you sure you want to reset?\n(No will exit the game.)");  //(GUI line 176).
        }
    }

/*This function handles the value (and color), for the buttons, at a given position.
Argument explanation: btn is the button, while i is the position (i.e. (btn1,1) means btn1, which is at position 1.*/
    public void ButtonValueChange(JButton btn, int i)
    {
    if(x[i] == Game.color.length-1)
        {
            x[i] = 0;   //If x[i] is about to go out of bounds, make it 0..
        }
        else
        {
            x[i]++;     //..otherwise just increase it as normal.
        }

        Game.guesses[i] = Game.color[x[i]]; //Change the guesses value, to the chosen color, at the buttons position.
        gui.ButtonColor(btn, i); //Finally, the color of the button is changed (GUI line 150):
    }
}

