package com.company.Game;

import java.util.Scanner;

/**
 * Created by Stefan on 19-11-2015.
 */
public class ConsoleGame
{
    boolean gameOn; //is the row gameOn?
    Scanner input = new Scanner(System.in);

    Game game = new Game();

    //This is how I tested the game, using the console. Can be accesed, by typing "Consoletester" as your name.
    public void consoleGame()
    {

        System.out.println("Welcome to the console version of Mastermind!\n" +
                "You make your move, by choosing a color, followed by the position.\n" +
                "i.e. \"Red 2\" will place the color red in the second position.\n" +
                "It's VERY important, that you write the colors as shown, otherwise the program won't recognize it.\n" +
                "Good luck, have fun!");
        while(!gameOn)
        {
            int x = 0;    //x is just a counter, for the coloumns.
            try
            {
                System.out.println("\nChoose a color!\n Red, Blue, Green, Yellow, Purple, or Orange.");
                String[] answer = input.nextLine().split(" ");
                int pos = Integer.parseInt(answer[1]);

                for (int i = 0; i < Game.color.length; i++)
                {
                    if(answer[0].equals(Game.color[i]))
                    {
                        System.out.print("\n\tYou chose a valid color! " + Game.color[i]);
                        Game.guesses[pos-1] = answer[0];

                        break;
                    }
                }
                System.out.println("At position "+pos);
                for (int i = 0; i < Game.guesses.length; i++)
                {
                    if(Game.guesses[i] != null) x++;
                    System.out.print(Game.guesses[i] + "\t");
                }
                System.out.println(x+" coloumns filled.");
                System.out.println();
                if(x== Game.guesses.length) game.check();
            }
            catch(Exception e)  //If something goes wrong..
            {
                System.out.println("Error! "+e);    //Prints the error message.
                System.out.println();
                consoleGame();       //Runs the game function again.
            }
        }
    }
}
