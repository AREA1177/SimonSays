/*
Aidan Ang
Mrs Krasteva, ICS3U3
Due January 14, 2020
ISP - SimonSays

    This program is a fully functioning interpretation of the game Simon Says.

	The program uses keyboard inputs to enable user interaction within menus.

	The general flow of the game is as follows:
	    When the user first enters the game, he or she may press any button to begin playing.
	    A sequence of numbers will then be displayed;
	    the number of indexes within the sequence and the speed of which the sequence will be displayed
	    is based upon the difficulty selected by the user.
	    Next, the screen is wiped and the user is asked to input the sequence from memory.
	    If the input is correct, another sequence is displayed at a slightly higher speed;
	    if the input is incorrect, the game is over and the program returns to the main menu.
	    At a certain point, determined by difficulty, a user can win the game.

    The program contains these recurring screens:
	    1. (splashScreen ()) A splash screen is displayed. The splash screen contains the:
		various animations
	    2. (mainMenu ()) The centered title is at the top. A prompt is given along with five options to proceed through the program.
	    3. (game () part 1) A large color wheel is displayed, which flashes a random sequence based upon numerous variables.
	    4. (game () part 2) The centered title is at the top. The user is prompted to input the sequence just displayed.
		A small color wheel is displayed beneath the prompt for reference.
	    5. (instructions()) The centered title is at the top. A text block of instructions is outputted.
		The pauseProgram method is used to create a buffer between this screen and the next, allowing the user to view the screen at his or her own pace.
	    6. (highScores () part 1) The centered title is at the top. The user is asked whether he or she wishes to erase the current highScores.file file.
	    7. (highScores () part 2) The centered title is at the top. All recorded high scores are displayed below, along with the corr4esponding names and difficulty.
		All high scores are derived from the highScores.file file.
	    8. (levelSelection ()) The centered title is at the top. The user is prompted with three options for difficulty: easy, medium or hard.
	    9. (goodbye () part 1) A goodbye message is shown. After a five second pause, the program execution stops and the console is closed.

    This program contains 11 methods:

	public void title ()
	public void splashScreen ()
	public void pauseProgram ()

	public void mainMenu ()
	private void newFile ()
	public int [] highScores(String name, int score, int level)

	public void instructions ()
	private int[] randomGenerator (int numRandoms)
	public void levelSelection ()

	public void game ()
	public void goodbye ()


    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    Global Variable Dictionary:

    Variable Name       Variable Type   Variable Description

    screen              char            Stores which option the user selected in the main menu;
					used in main method to determine how the program should proceed (according to the user input).

    screenChars         final int       Stores the number of spaces within the console in a constant;
					used to clear screen in errortraps.

    difficulty          int             Stores the current difficulty the user wishes to play at;
					can be changed by user with levelSelection method;
					used for calculations and game flow in game method.
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
*/

import java.io.*; // Accesess library
import java.awt.*; // Accesses java console library
import hsa.Console; // Accesses file
import javax.swing.JOptionPane;  // Accesses file

// The "SimonSays" class
public class SimonSays // Creates SimonSays class
{
    Console c; // Creates instance variable of Console class so output window can be made
    Console b;

    char screen = '1'; // Declares and initializes char variable "screen" as 1, used to direct execution of program according to user input.
    // Initialized to initiate while loop in main method
    final int screenChars = 2000; // Declares and initializes final int variable "screenChars" as 2000
    int difficulty = 2; // Declares and initializes int variable "difficulty" as 2, the default setting (medium)

    public SimonSays ()  // SimonSays class constructor
    {
	c = new Console ("Simon Says"); // Creates new Console window with the title "Simon Says"
    }


    /*
    title method
    - clears output screen
    - prints spaced title
    */

    public void title ()
    {
	c.clear (); // clears screen
	c.print (' ', 35); // centers title
	c.println ("Simon Says\n"); // prints title
    }


    /*
    splashScreen method
    - adds the splashScreen thread to SimonSays
    */

    public void splashScreen ()
    {
	// Creates thread
	SplashScreen s = new SplashScreen (c);
	// Commences thread execution
	s.run ();
    }


    /*
    pauseProgram method
    - pauses program by prompting user for an input to continue
    - allows user to progress through program at own pace
    */

    public void pauseProgram ()
    {
	c.print ("\nPlease press any key to continue: "); // user input prompt
	c.getChar (); // pauses program by asking user for input
    }


    /*
    mainMenu method
    - Welcomes user to program
    - Offers input options to user to proceed with
	1. Play Simon Says;");
	2. Choose your difficulty;");
	3. Read the instructions;");
	4. Access the high scores;");
	5. Exit the program.");
    - Prompts for user input
    - Errortraps user input
    - While structure used to errortrap input:
	- while loop:
	    execute while true (forever, until break;)
	    used to prompt for user input (inputString) for menu choice
    - Sets global variable "screen" to user input

    ///////////////////////////////////////////////////////////////////////
    Local Variable Dictionary:

    Variable Name   Variable Type   Variable Description

    inputChar       char            Takes input as char for errortrapping
    ///////////////////////////////////////////////////////////////////////
    */

    public void mainMenu ()
    {
	c.setTextBackgroundColor (new Color (200, 200, 200)); // sets the background text color of all text to a light grey
	char inputChar = 'x'; // Declares and initializes local String variable "inputString" to "x"
	// used for errortrapping input

	// repeat code until break
	while (true)
	{
	    try // attempts to execute code
	    {
		c.setCursor (1, 1);
		/*
		if statement used to determine how much output to clear
		    IF this is the first input prompt, erase first screen
		*/
		if (inputChar == 'x')
		{
		    c.print (' ', screenChars); // clears first screen
		    // ELSE (not first input prompt), erase last prompt screen
		}
		else
		{
		    c.print (' ', 641); // clears original input screen plus all user input
		}
		c.setCursor (1, 1); // sets cursor to top of screen
		c.print (' ', 35); // centers title
		c.println ("Simon Says\n"); // prints title
		c.println ("Welcome to Simon Says!"); // prints text body
		c.println ("Please enter '1' to play Simon Says;");
		c.println ("    or enter '2' to choose your difficulty;");
		c.println ("    or enter '3' to read the instructions;");
		c.println ("    or enter '4' to access the high scores;");
		c.println ("    or enter '5' to exit the program.");
		inputChar = c.getChar (); // sets inputString variable to user input
		// if inputString is not a number from 1 - 5 as required, throw an exception
		if (Integer.parseInt (String.valueOf (inputChar)) < 1 || Integer.parseInt (String.valueOf (inputChar)) > 5)
		{
		    throw new Exception (); // throws exception
		}
		break; // break (stop) while loop
	    }
	    catch (Exception e)  // catches all errors
	    {
		JOptionPane.showMessageDialog (null, "Please enter a valid menu option", "Error", JOptionPane.ERROR_MESSAGE); // sends error message to user
	    }
	}
	screen = inputChar; // sets screen to user input
    }


    /*
    newFile method
    - Clears highScores.file file by overwriting the old one

    /////////////////////////////////////////////////////////////////////////////////////////////////
    Local Variable Dictionary:

    Variable Name   Variable Type   Variable Description

    writer          PrintWriter     Used as reference to create and close a new highScores.file file.
    /////////////////////////////////////////////////////////////////////////////////////////////////
    */


    private void newFile ()
    {
	while (true)
	    try
	    {
		PrintWriter writer = new PrintWriter (new FileWriter ("highScores.file")); // assigns a new printwriter to the printwriter called writer
		// throws an ioexception if there is an error in writing or creating the file
		writer.close (); // close to write to the file
		break;
	    }
	catch (IOException e)  // catches errors
	{
	    System.out.println (e); // jOptionPane
	}
    }


    /*
    highScores method
    - Uses if statement to discern execution
	 IF screen equals 4 (high scores screen),
	    - prompt user for if he or she wishes to clear the highScores.file file
	    - IF yes,
		- clear highScores.file file
    - Reads highScores.file file to the highScoreBoard array
    - IF called in game
	- searches for a suitable place to insert score
	- inserts score
      ELSE
	- Display file on console

    /////////////////////////////////////////////////////////////////////////////////////////////////
    Local Variable Dictionary:

    Variable Name       Variable Type   Variable Description

    insertLoc           int             Used to store where the current score can be replaced

    highScoreBoard      String [] []    Used to store high score data from file;
					also used in display process.

    replaceScoreBoard   String [] []    Used to update the hgih score data and highScoreBoard

    lines               int             Used to store the amount of lines in the current open file

    inputString         String          Used to errortrap user input for
					if he or she wishes to clear the highScores.file file
    /////////////////////////////////////////////////////////////////////////////////////////////////
    */

    public void highScores (String name, int score, int level)
    {
	int insertLoc = -1;
	String highScoreBoard[] [] = new String [10] [3];
	String replaceScoreBoard[] [] = highScoreBoard;
	int lines = 0; // used to store the number of lines in the file
	String inputString;

	// prompt user for if he or she wishes to clear the highScores.file file
	if (screen == '4')
	{
	    inputString = "x"; // Declares and initializes local String variable "inputString" to "x"
	    // used for errortrapping input

	    // repeat code until break
	    while (true)
	    {
		try // attempts to execute code
		{
		    c.setCursor (1, 1); // sets cursor to top of screen
		    /*
		    if statement used to determine how much output to clear
			IF this is the first input prompt, erase first screen
		    */
		    if (inputString.equals ("x"))
		    {
			c.print (' ', screenChars); // clears first screen
			// ELSE (not first input prompt), erase last prompt screen
		    }
		    else
		    {
			c.print (' ', 480 + inputString.length () + 1); // clears original input screen plus all user input
		    }
		    c.setCursor (1, 1); // sets cursor to top of screen
		    c.print (' ', 35); // centers title
		    c.println ("Simon Says\n"); // prints title
		    c.println ("High Scores\n"); // prints text body
		    c.println ("Do you wish to clear the high scores file?");
		    c.println ("Please enter 'Y' if yes, and 'N' if no.");
		    inputString = c.readLine (); // sets inputString variable to user input
		    // if inputString is not 1 character as required, throw an exception
		    if (!inputString.equals ("Y") && !inputString.equals ("N"))
		    {
			throw new Exception (); // throws exception
		    }
		    break; // break (stop) while loop
		}
		catch (Exception e)  // catches all errors
		{
		    JOptionPane.showMessageDialog (null, "Please enter a valid selection (Y/N)", "Error", JOptionPane.ERROR_MESSAGE); // sends error message to user
		}
	    }

	    // If the user selected "Y" (Yes), clear the file
	    if (inputString.equals ("Y"))
	    {
		newFile (); // clears file by overwriting old file with new blank
	    }
	}

	// determines number of filled lines currently in file
	while (true) // run until the loop is exited
	{
	    try
	    {
		BufferedReader readFile = new BufferedReader (new FileReader ("highScores.file")); //  creates a new bufferedreader
		// throws ioexcpetion if the file cannot be read to/ other error occurs
		while (readFile.readLine () != null) // while the file has text on its current line
		{
		    lines++; // increase the count of lines
		}
		readFile.close (); // closes file
		break; // exit the loop if this part is reached
	    }
	    catch (IOException e)  // if the file cannot be read to / other error occurs
	    {
		JOptionPane.showMessageDialog (null, e, "Error", JOptionPane.ERROR_MESSAGE); // display this error message
	    }
	}

	// reads file to 2D array for later use
	try
	{
	    BufferedReader readFile = new BufferedReader (new FileReader ("highScores.file")); //creates a buffered reader to read from this file throws an io
	    // exception if the file doesn't exist / error occurs
	    // for loop assigns the values from the file to an array
	    for (int x = 0 ; x < (lines / 3) ; x++)
	    {
		// nested for loop used to assign values two-dimensionally to the 2D array
		for (int y = 0 ; y < 3 ; y++)
		{
		    highScoreBoard [x] [y] = readFile.readLine (); // Assigns the next line in the file to a variable index in highScoreBoard
		}
	    }
	    readFile.close (); // closes the file
	}
	catch (IOException e)  // catch the io exception
	{
	    JOptionPane.showMessageDialog (null, e, "Error", JOptionPane.ERROR_MESSAGE); // display this error message
	}

	// searches for a suitable place to insert score
	// inserts score
	// if in game mode
	if (screen == '1')
	{
	    replaceScoreBoard = highScoreBoard; // sets replaceScoreBoard as highScoreBoard
	    // For loop searches for highest score in file smaller than current score by looping through all score values from lowest to highest
	    for (int x = 9 ; x >= 0 ; x--)
	    {
		try
		{
		    // If the score passed to highScores is larger than the one being evaluated, set insertLoc to the current x index
		    if (score > Integer.parseInt (highScoreBoard [x] [1]))
		    {
			insertLoc = x;
		    }
		}
		catch (Exception e)
		{
		}
	    }
	    // if an insert location was not found and there are a number lines empty, set insertLoc to highest possible unoccupied location
	    if (insertLoc == -1 && lines < 30)
	    {
		// If no lines are filled, set insertLoc to the first line
		if (lines == 0)
		{
		    insertLoc = 0;
		}
		// else, set insertLoc to the first available line
		else
		{
		    insertLoc = (int) (lines / 3);
		}
	    }

	    // if insertLoc was resolved, write name, score, level to file
	    if (insertLoc != -1)
	    {
		while (true)
		{
		    try
		    {
			PrintWriter writer = new PrintWriter (new FileWriter ("highScores.file")); // assigns a new printwriter to the printwriter called writer
			// throws an ioexception if there is an error in writing or creating the file

			try
			{
			    // for loop prints values to file from replaceScoreBoard from 0 until the index to be modified
			    for (int x = 0 ; x < insertLoc ; x++)
			    {
				// nested for loop used to access y (2nd) dimension of array
				for (int y = 0 ; y < 3 ; y++)
				{
				    if (replaceScoreBoard [x] [y] != null)
				    {
					writer.println (replaceScoreBoard [x] [y]);
				    }
				}
			    }
			}
			catch (Exception e)  // catches errors
			{
			}

			// modifies at the insertLoc
			writer.println (name); // prints this to the file
			writer.println (score); // prints this to the file
			writer.println (level); // prints this to the file

			try
			{
			    // for loop prints values to file from replaceScoreBoard from the index to be modified until the end of the array
			    for (int x = insertLoc ; x < 9 ; x++)
			    {
				// nested for loop used to access y (2nd) dimension of array
				for (int y = 0 ; y < 3 ; y++)
				{
				    if (replaceScoreBoard [x] [y] != null)
				    {
					writer.println (replaceScoreBoard [x] [y]);
				    }
				}
			    }
			    writer.close (); // close to write to the file
			    break;
			}
			catch (Exception e)  // catches errors
			{
			}
		    }
		    catch (Exception e)  // catches errors
		    {
		    }
		}
	    }
	}
	// else (if called other than in game (in highScores)), display file
	else
	{
	    title ();
	
	    // heading
	    c.setCursor (3, 1);
	    c.print (' ', 160);
	    c.setCursor (3, 1);
	    c.println ("High Scores\n");

	    // subheadings
	    c.print ("Name");
	    c.print (' ', 21);
	    c.print ("Score");
	    c.print (' ', 20);
	    c.println ("Level\n");

	    // prints all data from highScoreBoard array
	    for (int x = 0 ; x < lines / 3 ; x++)
	    {
		// nested for loop used to access y (2nd) dimension of array
		for (int y = 0 ; y < 3 ; y++)
		{
		    c.print (highScoreBoard [x] [y]);
		    c.print (' ', 25 - highScoreBoard [x] [y].length ());
		}
		// spacing
		c.println ();
	    }
	    // spacing
	    c.println ();
	    // Prompts user for an input, allowing him or her to continue through the program at his or her own pace
	    pauseProgram ();
	}
    }



    /*
    instructions method
    - Calls title method
    - Welcomes user to program
    - Informs user of program function
    - Informs user on how to operate program (play game)
    - Calls pauseProgram method
    */

    public void instructions ()
    {
	title (); // Executes title method

	// Instructions title
	c.println ("Simon Says Instructions\n");
	// Program Function
	c.println ("Welcome! This program was made to provide you, the user,");
	c.println ("a fun game of Simon Says!\n");
	// Operating Program
	c.println ("To navigate the menus within the program,");
	c.println ("you will need to use keyboard inputs,");
	c.println ("while following the prompts accordingly.\n");
	// Playing Game
	c.println ("By the way, playing Simon Says isn't so hard:");
	c.println ("When you first enter the game,");
	c.println ("press any button to begin once you are ready.");
	c.println ("A sequence will be displayed - make sure you know it well!");
	c.println ("On the next screen, you will be asked to input the sequence.");
	c.println ("If you get it right, another sequence, slightly faster, will be displayed.");
	c.println ("Depending on your difficulty, if you answer");
	c.println ("a certain amount of questions in one playthrough you will win the game!");

	// Prompts user for an input, allowing him or her to continue through the program at his or her own pace
	pauseProgram (); // Executes pauseProgram method
    }


    /*
    randomGenerator method
    - Generates x random numbers, according to the numRandoms parameter
    - Returns int array of x random numbers

    /////////////////////////////////////////////////////////////////////////////////////////////////
    Local Variable Dictionary:

    Variable Name   Variable Type   Variable Description

    output          int []          Used to store random numbers for output;
				    also used for output
    /////////////////////////////////////////////////////////////////////////////////////////////////
    */

    private int[] randomGenerator (int numRandoms)
    {
	int output[] = new int [numRandoms]; // sets output array to the length specified as the passed parameter
	// for loop generates a random number x times according to the passed parameter
	for (int x = 0 ; x < numRandoms ; x++)
	{
	    output [x] = (int) (Math.random () * (7 - (0) + 1) + (0)); // generates number between 0 and 7 (inclusive)
	}

	// returns output array
	return output;
    }


    /*
    levelSelection method
    - Prompts user to select a difficulty for gameplay
	1 (Easy)
	2 (Medium) (default)
	3 (Hard)
    - Prompts for user input
    - Errortraps user input
    - While structure used to errortrap input:
	- while loop:
	    execute while true (forever, until break;)
	    used to prompt for user input (inputString) for menu choice
    - Sets global variable "difficulty" to user input

    ///////////////////////////////////////////////////////////////////////
    Local Variable Dictionary:

    Variable Name   Variable Type   Variable Description

    inputChar       char            Takes input as char for errortrapping
    ///////////////////////////////////////////////////////////////////////
    */

    public void levelSelection ()
    {
	char inputChar = 'x'; // Declares and initializes local char variable "inputString" to 'x'
	// used for errortrapping input

	// repeat code until break
	while (true)
	{
	    try // attempts to execute code
	    {
		c.setCursor (1, 1);
		/*
		if statement used to determine how much output to clear
		    IF this is the first input prompt, erase first screen
		*/
		if (inputChar == 'x')
		{
		    c.print (' ', screenChars); // clears first screen
		    // ELSE (not first input prompt), erase last prompt screen
		}
		else
		{
		    c.print (' ', 721); // clears original input screen plus all user input
		}
		c.setCursor (1, 1); // sets cursor to top of screen
		c.print (' ', 35); // centers title
		c.println ("Simon Says\n"); // prints title
		c.println ("Level Selection\n"); // prints text body
		c.println ("Please enter the integer difficulty you would like to play at:");
		c.println ("    1 (Easy)");
		c.println ("    2 (Medium) (default)");
		c.println ("    3 (Hard)");
		inputChar = c.getChar (); // receives user input
		// if inputString is not 1, 2 or 3 as required, throw an exception
		if (Integer.parseInt (String.valueOf (inputChar)) < 1 || Integer.parseInt (String.valueOf (inputChar)) > 3)
		{
		    throw new Exception (); // throws exception
		}
		break; // break (stop) while loop
	    }
	    catch (Exception e)  // catches all errors
	    {
		JOptionPane.showMessageDialog (null, "Please enter a valid selection (1/2/3)", "Error", JOptionPane.ERROR_MESSAGE); // sends error message to user
	    }
	}

	// sets difficulty to user input
	difficulty = Integer.parseInt (String.valueOf (inputChar));
    }


    /*
    game method
    - Prompts user to enter a name
    - Stores name in String name
    - Displays a sequence of numbers; the number of indexes within the sequence and the speed of which the sequence will be displayed
	is based upon the difficulty selected by the user.
    - Screen is wiped, user prompted to input sequence from memory.
    - If the input is correct, another sequence is displayed at a slightly higher speed;
    - If the input is incorrect, the game is over and the program returns to the main menu.
    - At a certain point, determined by difficulty, a user can win the game.

    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    Local Variable Dictionary:

    Variable Name   Variable Type   Variable Description

    userAnswer      int []          Used to store the user's guesses in an array

    randomDisplay   int []          Used to store the segments of the circle to be displayed,
				    as well as the correct answers

    answersCorrect  boolean         Used to conduct program flow;
				    stores whether the user has entered the correct inputs

    iteration       int             Used for score calculation and as the delay between flashes of the game;
				    is reduced after each solved sequence;

    customColor     Color           Used for color wheel graphic

    name            String          Used to store user's name;
				    is passed to highScores method for potential use in highscore file

    win             boolean         Used to conduct program flow;
				    stores whether the user has won the game

    equalOrNot      boolean         Used in the process of comparing the userAnswer and randomDisplay arrays

    inputChar       char            Used for errortrapping the user's Simon Says guesses

    inputString     String          Used for errortrapping the user's name input

    lightArc        Color           Used for illuminating arcs during the game

    resetArc        Color           Used for resetting arc colors during the game
    /////////////////////////////////////////////////////////////////////////////////////////////////////////
    */

    public void game ()
    {
	int userAnswer[] = new int [2 + difficulty]; // array length variable based on difficulty
	int randomDisplay[] = new int [2 + difficulty]; // array length variable based on difficulty
	boolean answersCorrect = true; // answers are correct until proven otherwise
	int iteration = 600 * (4 - difficulty); // iteration is variable based on difficulty
	Color customColor;
	String name;
	boolean win = false; // game is be won at beginning
	boolean equalOrNot;
	String inputString;
	Color lightArc;
	Color resetArc;

	// clear screen
	title ();

	inputString = "x"; // Declares and initializes local String variable "inputString" to "x"
	// used for errortrapping input
	// repeat code until break
	while (true)
	{
	    try // attempts to execute code
	    {
		c.setCursor (1, 1);
		/*
		if statement used to determine how much output to clear
		    IF this is the first input prompt, erase first screen
		*/
		if (inputString.equals ("x"))
		{
		    c.print (' ', screenChars); // clears first screen
		    // ELSE (not first input prompt), erase last prompt screen
		}
		else
		{
		    c.print (' ', 240 + inputString.length () + 1); // clears original input screen plus all user input
		}
		c.setCursor (1, 1); // sets cursor to top of screen
		c.print (' ', 35); // centers title
		c.println ("Simon Says\n"); // prints title
		c.println ("Before we begin, please enter your name:"); // prompt
		inputString = c.readLine (); // sets inputString variable to user input
		// if inputString is smaller than 1 character, throw an exception
		if (inputString.length () < 1)
		{
		    throw new Exception (); // throws exception
		}
		// if inputString is larger than 20 characters, throw an exception
		if (inputString.length () > 20)
		{
		    throw new Exception (); // throws exception
		}
		break; // break (stop) while loop
	    }
	    catch (Exception e)  // catches all errors
	    {
		JOptionPane.showMessageDialog (null, "Your must be between 1 and 20 characters. Please enter another name.", "Error", JOptionPane.ERROR_MESSAGE); // sends error message to user
	    }
	}


	name = inputString; // sets name to user input

	c.setCursor (1, 1);
	c.print (' ', screenChars);
	c.setCursor (1, 1);
	// draws original simon says game template
	c.setColor (Color.black);
	c.fillOval (71, 1, 498, 498);
	for (float x = 0 ; x < 1 ; x += 0.125)
	{
	    customColor = Color.getHSBColor (x + 0.0f, 1f, 0.5f);
	    // http://www.java2s.com/Tutorials/Java/java.awt/Color/Java_Color_getHSBColor_float_h_float_s_float_b_.htm
	    c.setColor (customColor);
	    c.fillArc (75, 5, 490, 490, (int) (x * 360), -45);
	}


	// draws dividers between colored segments
	for (int x = 0 ; x < 360 ; x += 45)
	{
	    c.setColor (Color.white);
	    c.drawLine (320, 250, (int) (245 * Math.sin (x * Math.PI / 180)) + 320, (int) (245 * Math.cos (x * Math.PI / 180)) + 250);
	    c.drawLine (319, 250, (int) (245 * Math.sin (x * Math.PI / 180)) + 319, (int) (245 * Math.cos (x * Math.PI / 180)) + 250);
	    c.drawLine (320, 249, (int) (245 * Math.sin (x * Math.PI / 180)) + 320, (int) (245 * Math.cos (x * Math.PI / 180)) + 249);
	}


	// prompt to allow user to begin at his or her leisure
	c.setColor (Color.yellow);
	c.setFont (new Font ("Stencil", Font.PLAIN, 64));
	c.drawString ("PRESS ANY KEY", 83, 235);
	c.drawString ("TO BEGIN", 180, 305);
	c.getChar ();

	// redraws original simon says game template
	c.setColor (Color.black);
	c.fillOval (71, 1, 498, 498);
	for (float x = 0 ; x < 1 ; x += 0.125)
	{
	    customColor = Color.getHSBColor (x + 0.0f, 1f, 0.5f);
	    // http://www.java2s.com/Tutorials/Java/java.awt/Color/Java_Color_getHSBColor_float_h_float_s_float_b_.htm
	    c.setColor (customColor);
	    c.fillArc (75, 5, 490, 490, (int) (x * 360), -45);
	}


	// redraws dividers between colored segments
	for (int x = 0 ; x < 360 ; x += 45)
	{
	    c.setColor (Color.white);
	    c.drawLine (320, 250, (int) (245 * Math.sin (x * Math.PI / 180)) + 320, (int) (245 * Math.cos (x * Math.PI / 180)) + 250);
	    c.drawLine (319, 250, (int) (245 * Math.sin (x * Math.PI / 180)) + 319, (int) (245 * Math.cos (x * Math.PI / 180)) + 250);
	    c.drawLine (320, 249, (int) (245 * Math.sin (x * Math.PI / 180)) + 320, (int) (245 * Math.cos (x * Math.PI / 180)) + 249);
	}


	// game
	while (answersCorrect == true)
	{
	    // uses black box return method to generate and store a number of random integers depending on difficulty
	    randomDisplay = randomGenerator (randomDisplay.length);
	    // displays a number of random flash outputs depending on difficulty
	    for (int x = 0 ; x < randomDisplay.length ; x++)
	    {
		// variable pause in game so as to increase difficulty as user progresses;
		// inversely scaled to the number of iterations the user has correctly entered
		try
		{
		    Thread.sleep (iteration);
		}
		catch (Exception e)
		{
		}

		// lights up arc
		lightArc = Color.getHSBColor ((float) (randomDisplay [x] * 0.125) + 0.0f, 1f, 1f);
		c.setColor (lightArc);
		c.fillArc (75, 5, 490, 490, (int) (randomDisplay [x] * 45), -45);

		// 0.2 second pause in display is "flash length"
		try
		{
		    Thread.sleep (200);
		}
		catch (Exception e)
		{
		}

		// resets arc to default coloring
		resetArc = Color.getHSBColor ((float) (randomDisplay [x] * 0.125) + 0.0f, 1f, 0.5f);
		c.setColor (resetArc);
		c.fillArc (75, 5, 490, 490, (int) (randomDisplay [x] * 45), -45);

		// redraws dividers between colored segments
		for (int y = 0 ; y < 360 ; y += 45)
		{
		    c.setColor (Color.white);
		    c.drawLine (320, 250, (int) (245 * Math.sin (y * Math.PI / 180)) + 320, (int) (245 * Math.cos (y * Math.PI / 180)) + 250);
		    c.drawLine (319, 250, (int) (245 * Math.sin (y * Math.PI / 180)) + 319, (int) (245 * Math.cos (y * Math.PI / 180)) + 250);
		    c.drawLine (320, 249, (int) (245 * Math.sin (y * Math.PI / 180)) + 320, (int) (245 * Math.cos (y * Math.PI / 180)) + 249);
		}
	    }

	    // resets userAnswer
	    for (int y = 0 ; y < randomDisplay.length ; y++)
	    {
		userAnswer [y] = 0;
	    }

	    // input
	    for (int x = 0 ; x < randomDisplay.length ; x++)
	    {
		char inputChar = 'x'; // Declares and initializes local char variable "inputString" to 'x'
		// used for errortrapping input

		// repeat code until break
		while (true)
		{
		    try // attempts to execute code
		    {
			/*
			if statement used to determine how much output to clear
			    IF this is the first input prompt, erase first screen
			*/
			if (inputChar == 'x')
			{
			    c.setCursor (1, 1);
			    c.print (' ', screenChars); // clears first screen
			    c.setCursor (1, 1);
			    c.print (' ', 35); // centers title
			    c.println ("Simon Says\n"); // prints title
			    c.println ("Please enter the corresponding number to the next color which appeared:");
			}
			else
			{
			    c.setCursor (c.getRow () - 2, 1);
			    c.print (' ', 80);

			    c.println ("Please enter the corresponding number to the next color which appeared:");
			    c.print (' ', 2);
			    c.setCursor (c.getRow (), 1);
			}

			// sample wheel
			for (float y = 0 ; y < 1 ; y += 0.125)
			{
			    customColor = Color.getHSBColor (y + 0.0f, 1f, 0.8f);
			    // http://www.java2s.com/Tutorials/Java/java.awt/Color/Java_Color_getHSBColor_float_h_float_s_float_b_.htm
			    c.setColor (customColor);
			    c.fillArc (220, 200, 200, 200, (int) (y * 360), -45);
			}

			// number labels
			c.setFont (new Font ("Stencil", Font.PLAIN, 20));
			c.setColor (Color.black);
			for (int y = 22 ; y < 360 ; y += 45)
			{
			    c.drawString (Integer.toString ((int) ((y - 22) / 45)), (int) (130 * Math.sin ((y + 45) * Math.PI / 180)) + 314, (int) (130 * Math.cos ((y + 45) * Math.PI / 180)) + 307);
			}

			// displays the user's inputs so far
			c.setFont (new Font ("Stencil", Font.PLAIN, 16));
			c.drawString ("Your answers: ", 25, 400);
			for (int y = 0 ; y < randomDisplay.length ; y++)
			{
			    c.drawString (Integer.toString (userAnswer [y]), 25 + y * 30, 425);
			}

			inputChar = c.getChar (); // receives user input
			// if user input is not between the integers 0 - 7, throw exception
			if (inputChar < 48 || inputChar > 55)
			{
			    throw new NumberFormatException (); // throws exception
			}
			userAnswer [x] = Integer.parseInt (String.valueOf (inputChar)); // sets a certain index of userAnswer to inputChar
			break; // break (stop) while loop
		    }
		    catch (NumberFormatException e)  // catches all errors
		    {
			JOptionPane.showMessageDialog (null, "Please enter a valid integer", "Error", JOptionPane.ERROR_MESSAGE); // sends error message to user
			userAnswer [x] = 0;
		    }
		}
	    }

	    // checking input
	    // https://javaconceptoftheday.com/how-to-check-the-equality-of-two-arrays-in-java/
	    equalOrNot = true;
	    if (randomDisplay.length == userAnswer.length)
	    {
		for (int i = 0 ; i < randomDisplay.length ; i++)
		{
		    if (randomDisplay [i] != userAnswer [i])
		    {
			equalOrNot = false;
		    }
		}
	    }
	    else
	    {
		equalOrNot = false;
	    }

	    // changing appropriate var if input is incorrect
	    if (!equalOrNot)
	    {
		answersCorrect = false;
	    }

	    // lowering gaps between flashes upon correct answer
	    else
	    {
		// if total sequences (intial iteration value - current iteration value / increment (15 * difficulty)) smaller than win condition (10 sequences)
		if (((600 * (4 - difficulty)) - iteration) / (15 * difficulty) < 10)
		{
		    iteration -= 15 * difficulty;

		    c.setCursor (1, 1);
		    c.print (' ', screenChars);

		    // redraws original simon says game template
		    c.setColor (Color.black);
		    c.fillOval (71, 1, 498, 498);
		    for (float x = 0 ; x < 1 ; x += 0.125)
		    {
			customColor = Color.getHSBColor (x + 0.0f, 1f, 0.5f);
			// http://www.java2s.com/Tutorials/Java/java.awt/Color/Java_Color_getHSBColor_float_h_float_s_float_b_.htm
			c.setColor (customColor);
			c.fillArc (75, 5, 490, 490, (int) (x * 360), -45);
		    }
		    for (int x = 0 ; x < 360 ; x += 45)
		    {
			c.setColor (Color.white);
			c.drawLine (320, 250, (int) (245 * Math.sin (x * Math.PI / 180)) + 320, (int) (245 * Math.cos (x * Math.PI / 180)) + 250);
			c.drawLine (319, 250, (int) (245 * Math.sin (x * Math.PI / 180)) + 319, (int) (245 * Math.cos (x * Math.PI / 180)) + 250);
			c.drawLine (320, 249, (int) (245 * Math.sin (x * Math.PI / 180)) + 320, (int) (245 * Math.cos (x * Math.PI / 180)) + 249);
		    }
		}
		// else, win is true
		else
		{
		    win = true;
		    break;
		}
		if (win)
		{
		    break;
		}
	    }
	}


	// if the user has won
	if (win)
	{
	    // creates win message
	    // Lightly shades over current screen
	    for (int x = 0 ; x <= 32 ; x++)
	    {
		c.setColor (new Color (255, 255, 255, x));
		c.fillRect (0, 0, 640, 500);
	    }

	    // sets new font
	    c.setFont (new Font ("Stencil", Font.PLAIN, 100));

	    // animates "YOU WIN" message with blinking color
	    for (int x = 0 ; x < 4 ; x++)
	    {
		c.setColor (Color.yellow);
		c.drawString ("YOU WIN", 120, 280);

		// 0.35 second pause in display to create blinking effect
		try
		{
		    Thread.sleep (350);
		}
		catch (Exception e)
		{
		}

		c.setColor (Color.green);
		c.drawString ("YOU WIN", 120, 280);

		// 0.5 second pause in display to create blinking effect
		try
		{
		    Thread.sleep (500);
		}
		catch (Exception e)
		{
		}
	    }

	    // fades "YOU WIN" message out slightly
	    for (int x = 64 ; x >= 32 ; x--)
	    {
		c.setColor (new Color (255, 255, 255, x));
		c.drawString ("YOU WIN", 120, 280);
		// 0.1 second pause in display to create blinking effect
		try
		{
		    Thread.sleep (100);
		}
		catch (Exception e)
		{
		}
	    }
	}


	// else the user has lost
	else
	{
	    // creates game over message
	    // Lightly shades over current screen
	    for (int x = 0 ; x <= 32 ; x++)
	    {
		c.setColor (new Color (0, 0, 0, x));
		c.fillRect (0, 0, 640, 500);
	    }

	    // sets new font
	    c.setFont (new Font ("Stencil", Font.PLAIN, 100));

	    // animates "YOU WIN" message with blinking color
	    for (int x = 0 ; x < 4 ; x++)
	    {
		c.setColor (Color.yellow);
		c.drawString ("GAME OVER", 50, 250);

		// 0.35 second pause in display to create blinking effect
		try
		{
		    Thread.sleep (350);
		}
		catch (Exception e)
		{
		}

		c.setColor (Color.red);
		c.drawString ("GAME OVER", 50, 250);

		// 0.5 second pause in display to create blinking effect
		try
		{
		    Thread.sleep (500);
		}
		catch (Exception e)
		{
		}
	    }

	    // fades "YOU WIN" message out slightly
	    for (int x = 64 ; x >= 32 ; x--)
	    {
		c.setColor (new Color (0, 0, 0, x));
		c.drawString ("GAME OVER", 50, 250);
		// 0.1 second pause in display to create blinking effect
		try
		{
		    Thread.sleep (100);
		}
		catch (Exception e)
		{
		}
	    }
	}

	// calls highScores function to store the current game data in the highscores file if it qualifies
	    // score = sequences solved multiplied by difficulty modifier
	highScores (name, ((600 * (4 - difficulty)) - iteration) / (15 * difficulty) * difficulty, difficulty);
    }


    /*
    goodbye method
    - Calls title method
    - Thanks user for using program
    - States author of program
    - Calls pauseProgram method
    - Recalls title method to wipe screen
    - Displays closing graphic

    - Waits 2 seconds to allow user to see finished goodbye graphic
    - Ends program execution
    */

    public void goodbye ()
    {
	title (); // Executes title method
	c.println ("Thank you for playing Simon Says!");
	c.println ("\nThis program was created by Aidan Ang,");
	c.println ("for the Toronto District School Board.");

	// 5 second pause in execution for "readability");
	try
	{
	    Thread.sleep (5000);
	}
	catch (Exception e)
	{
	}


	c.close ();
    }


    /* Main Method
    - Creates instance variable of class
    - Calls splashScreen method
    - Uses while statement to loop rest of code until user wishes to exit
	- Uses if statement within while statement to display correct methods based on user input
    */

    public static void main (String[] args)
    {
	SimonSays co = new SimonSays ();

	co.splashScreen ();
	while (true)
	{
	    co.mainMenu ();
	    // if the game option was selected, run the game
	    if (co.screen == '1')
	    {
		co.game ();
	    }
	    // else if the level selection option was selected, open level selection
	    else if (co.screen == '2')
	    {
		co.levelSelection ();
	    }
	    // else if the instructions option was selected, display the instructions
	    else if (co.screen == '3')
	    {
		co.instructions ();
	    }
	    // else if the high scores option was selected, open high scores
	    else if (co.screen == '4')
	    {
		co.highScores ("", 0, 0);
	    }
	    // else, initiate goodbye sequence
	    else
	    {
		co.goodbye ();
		// break out of while loop, moves to the end of the program
		break;
	    }
	}
    }
}



