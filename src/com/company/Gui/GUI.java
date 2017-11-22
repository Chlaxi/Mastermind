package com.company.Gui;

import com.company.Game.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Mastermind Created by Chloe Parbst on 11-11-2015.
 */
public class GUI
{

    JFrame f = new JFrame();            //This declares the frame, in which the GUI is drawn.

    //ArrayLists, which contains the buttons, used for feedback.
    ArrayList<JButton> visualiseArray = new ArrayList<>();    //ArrayList, containing the visual buttons.
    ArrayList<JButton> feedbackArray = new ArrayList<>();    //ArrayList, containing the feedback.

    public static MyActionListener Action = new MyActionListener();   //Declares the ActionListener



    //These integers, are used to get the specific number, for the ArrayLists.
    public int validateInt = 0;
    public int feedbackInt = 0;


//Declares the buttons, which the user is able to use.
    public static JButton btn0 = new JButton("Guess 1");
    public static JButton btn1 = new JButton("Guess 2");
    public static JButton btn2 = new JButton("Guess 3");
    public static JButton btn3 = new JButton("Guess 4");
    public static JButton validate = new JButton("Validate");
    public static JButton tryAgain = new JButton("Try again");

    /*Assigns the ActionListeners to the buttons, so the user can use the buttons.
    This should only run once upon startup, otherwise one click, would be counted as multiple.*/
    public static void a()      //This is ONLY called once from the main class!
    {
    btn0.addActionListener(Action);
    btn1.addActionListener(Action);
    btn2.addActionListener(Action);
    btn3.addActionListener(Action);
    validate.addActionListener(Action);
    tryAgain.addActionListener(Action);
    }

//The setup, which resets the GUI values, as they should be upon startup.
    public void guiSetup()  //(Called from Game line 44).
    {
        btn0.setBackground(Color.red);  //The color of the buttons, should fit.
        btn1.setBackground(Color.red);
        btn2.setBackground(Color.red);
        btn3.setBackground(Color.red);
        visualiseArray.clear();         //The arrayLists should be cleared, otherwise the feedback..
        feedbackArray.clear();          //.. and visualisation will be displayed wrong.
        validateInt = 0;                //And their counters reset, or they'll..
        feedbackInt = 0;                //.. start at a wrong place.
        gui();                          //And then the GUI can be drawn. (GUI line 65)
    }

//This function draws the GUI..
    public void gui()
    {
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //Actually closes the program, when you close it. (Otherwise, it'd run in the background, after you "close" it.)
        f.setSize(500, 750);                             //Sets the size of the window.
        f.setTitle("Mastermind! - By Chloe Parbst");       //Sets the window title.

        f.setLayout(new BorderLayout());          //The overall layout of the window, as a BorderLayout.
        JPanel grid = new JPanel();              //Declares a panel, called grid. This is where the visualisation is placed.
        grid.setLayout(new GridLayout(10, 5));   //Declares the layout for grid, as a GridLayout.
        JPanel topPanel = new JPanel();         //Declares a panel, called topPanel, in which the user elements are placed.
        topPanel.setLayout(new FlowLayout());   //Declares the layout for topPanel, as a FlowLayout.
        JPanel bottomPanel = new JPanel();         //Declares a panel, called grid. This is where the visualisation is placed.
        bottomPanel.setLayout(new BorderLayout());    //Declares the layout for bottomPanel, as a BorderLayout.

        f.add(topPanel, BorderLayout.NORTH);    //Places the topPanel at the top (NORTH) (See line 84).
        f.add(grid, BorderLayout.CENTER);       //Places the grid in the center (CENTER) (See line 92).
        f.add(bottomPanel, BorderLayout.SOUTH); //Places bottomPanel in the bottom (SOUTH) (See line 111).

    //NORTH - Adds the buttons, which the user can use, to the top of the window.
        topPanel.add(btn0);
        topPanel.add(btn1);
        topPanel.add(btn2);
        topPanel.add(btn3);
        topPanel.add(validate);

    //CENTER - The feedback to the user. This fills the window, as long as nothing else should be drawn.
        //This code snippet, handling the drawing of the grid, is inspired from the "Grid to Grid" file, uploaded on our Moodle, during a lesson.
        //This nested for-loop, creates 10 rows. each row, consists of..
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j <4 ; j++) {    //..4 buttons, which visualises the users input.
                JButton btn = new JButton(); //Declares the new button.
                visualiseArray.add(btn);     //The buttons are added to the arrayList.
                grid.add(btn);              //Adds the buttons to the grid.
            }

            JPanel panel = new JPanel();        //Declares a new panel..
            panel.setLayout(new GridLayout(2,2));   /*..Then creates a new grid, which gives the user
                                                    some clues, based on their guesses*/
            for (int j = 0; j < 4; j++) {
                JButton btnSmall = new JButton();
                feedbackArray.add(btnSmall);     //The buttons are added to the arrayList.
                panel.add(btnSmall);    //Adds the buttons to panel.
            }
            grid.add(panel);    //Panel is then placed inside grid.
        }

    //SOUTH - Contains the description.
        //The information text, which helps the player, so they know how to play the game.
        JTextPane infoText = new JTextPane();
        infoText.setEditable(false);            //The player shouldn't be able to edit this!
        infoText.setText("Welcome to Mastermind!\n" +
                "The computer has created a color code, which you must guess!\n" +
                "Click on the labeled buttons, at the top of the window, to change their color! When you are ready, click validate.\n" +
                "Once your answer has been validated, you will get feedback, which you can reflect on:\n" +
                "Yellow means, that the color is correct, but its placement is off,\n" +
                "while green means, that the color AND placement is correct.\n" +
                "Use your wits, and guess the code! Good luck!");
        //Adds the elements to the bottomPanel.
        bottomPanel.add(infoText, BorderLayout.CENTER); //Placed in the Center of the bottomPanel (it fills out the space).
        bottomPanel.add(tryAgain, BorderLayout.EAST);   //Placed in the eastern part of the bottomPanel.

    //Makes sure the frame is visible.
        f.setVisible(true);
    }

//This function colors the buttons(both feedback and visual), so the user can see his/her last input, and get feedback.
    public void visualColor()       //(Called from Game line 81).
    {
        for (int i = 0; i < Game.guesses.length; i++)
        {
            ButtonColor(visualiseArray.get(validateInt), i); //This makes sure, that the user can see what their previous input was.
            validateInt++;      //Increases the value, so the next visualButton, will be chosen
            feedbackInt++;      /*Increases the value, so it will properly aligned.
                                This isn't increased in the feedColor(), since
                                it otherwise made some bad alignment, and this was the most stable fix I found.*/
        }
    }

//This handles the specific feedback, and colours them.
    public void feedColor(Color c, int i)       //(called from Game line 68 & 77)
    {
        feedbackArray.get(feedbackInt+i).setBackground(c);  //Sets the color of the feedback at a given position, to the given color.
    }

//This function translates the guesses to colors, the button can understand.
    public void ButtonColor(JButton btn, int i)
    {
        switch(Game.guesses[i]) //The switch checks for the color at the given position.
        {
            case "Red":     //If it's red..
                btn.setBackground(Color.red); //..Change the buttons color to red..
                break;                      //End this switch, since the task is accomplished..
            case "Blue":        //..otherwise, check if it's blue, and so on.
                btn.setBackground(Color.blue);
                break;
            case "Green":
                btn.setBackground(Color.green);
                break;
            case "Yellow":
                btn.setBackground(Color.yellow);
                break;
            case "Purple":
                btn.setBackground(Color.magenta);
                break;
            case "Orange":
               btn.setBackground(Color.orange);
                break;
        }
    }

//The textbox, which pops up once the game is finished, or you press the try again button.
    public void displayText(String text)    //(Called from Game line 87 & 96, and MyActionListener line 48)
    {
        //Specifies allowed options (Yes & No buttons only).
        int result = JOptionPane.showConfirmDialog(null, text, "", JOptionPane.YES_NO_OPTION);

        if(result == JOptionPane.NO_OPTION) //If the user says "no" to the prompt..
        {
            System.exit(0); //..close the game
        }
        if(result == JOptionPane.YES_OPTION) //if the user says "yes" to the prompt..
        {
            f.dispose();    //..Get rid of the GUI window..
            Game.setup();   //..And run the Game.setup function, which restarts the game. (Game line 30)
        }
    }

}
