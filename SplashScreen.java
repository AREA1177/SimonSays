/*
Aidan Ang
Mrs Krasteva, ICS3U3
Due January 14, 2020
ISP - SplashScreen

    This thread will display an interesting animated splash screen to the user

    This program contains 2 methods:

	public void display ()
	public void SplashScreen ()
	public void run ()

    ////////////////////////////////////////////////////////
    Global Variable Dictionary:

    Variable Name       Variable Type   Variable Description

    N/A                 N/A             N/A
    ////////////////////////////////////////////////////////
*/

import java.awt.*; // Accesses java console library
import hsa.Console; // Accesses file
import java.lang.*;     // to access Thread class

// The "SplashScreen" class
public class SplashScreen extends Thread // Creates SplashScreen class
{
    private Console c;

    public void display ()
    {
	Color customColor;

	// Draws each segment of SimonSays color wheel sequentially;
	// pauses in execution produce illusion of wheel unfolding from a single arc
	// Black outline of color wheel is a larger black circle behind
	c.setColor (Color.black);
	c.fillOval (71, 1, 498, 498);
	// Smaller white circle drawn to simulate background over black outline
	c.setColor (Color.white);
	c.fillOval (75, 5, 490, 490);
	// Draws arcs at the default brightness (0.8)
	for (float x = 0 ; x < 1 ; x += 0.125)
	{
	    customColor = Color.getHSBColor (x + 0.0f, 1f, 0.8f);
	    // http://www.java2s.com/Tutorials/Java/java.awt/Color/Java_Color_getHSBColor_float_h_float_s_float_b_.htm
	    c.setColor (customColor);
	    c.fillArc (75, 5, 490, 490, (int) (x * 360), -45);
	    // 0.4 second pause in display for "animation"
	    try
	    {
		Thread.sleep (400);
	    }
	    catch (Exception e)
	    {
	    }
	}

	// Draws white lines segmenting the color wheel, seperates color arcs
	for (int x = 0 ; x < 360 ; x += 45)
	{
	    c.setColor (Color.white);
	    c.drawLine (320, 250, (int) (245 * Math.sin (x * Math.PI / 180)) + 320, (int) (245 * Math.cos (x * Math.PI / 180)) + 250);
	    c.drawLine (319, 250, (int) (245 * Math.sin (x * Math.PI / 180)) + 319, (int) (245 * Math.cos (x * Math.PI / 180)) + 250);
	    c.drawLine (320, 249, (int) (245 * Math.sin (x * Math.PI / 180)) + 320, (int) (245 * Math.cos (x * Math.PI / 180)) + 249);
	}

	// Highlights each segment of SimonSays color wheel sequentially;
	// pause in execution produce illusion of wheel spinning
	// Lights each segment up sequentially (brightness of color increased from 0.8 to 1)
	for (int z = 0 ; z < 10 ; z++)
	{
	    for (float x = 0 ; x < 1 ; x += 0.125)
	    {
		customColor = Color.getHSBColor (x + 0.0f, 1f, 1f);
		// http://www.java2s.com/Tutorials/Java/java.awt/Color/Java_Color_getHSBColor_float_h_float_s_float_b_.htm
		c.setColor (customColor);
		// Highlights arc
		c.fillArc (75, 5, 490, 490, (int) (x * 360), -45);
		// Redraws white dividers
		for (int y = 0 ; y < 360 ; y += 45)
		{
		    c.setColor (Color.white);
		    c.drawLine (320, 250, (int) (245 * Math.sin (y * Math.PI / 180)) + 320, (int) (245 * Math.cos (y * Math.PI / 180)) + 250);
		    c.drawLine (319, 250, (int) (245 * Math.sin (y * Math.PI / 180)) + 319, (int) (245 * Math.cos (y * Math.PI / 180)) + 250);
		    c.drawLine (320, 249, (int) (245 * Math.sin (y * Math.PI / 180)) + 320, (int) (245 * Math.cos (y * Math.PI / 180)) + 249);
		}

		// 0.05 second pause in display for "animation"
		try
		{
		    Thread.sleep (50);
		}
		catch (Exception e)
		{
		}

		customColor = Color.getHSBColor (x + 0.0f, 1f, 0.8f);
		// http://www.java2s.com/Tutorials/Java/java.awt/Color/Java_Color_getHSBColor_float_h_float_s_float_b_.htm
		c.setColor (customColor);
		// Resets arc to default brightness
		c.fillArc (75, 5, 490, 490, (int) (x * 360), -45);
		// Redraws white dividers
		for (int y = 0 ; y < 360 ; y += 45)
		{
		    c.setColor (Color.white);
		    c.drawLine (320, 250, (int) (245 * Math.sin (y * Math.PI / 180)) + 320, (int) (245 * Math.cos (y * Math.PI / 180)) + 250);
		    c.drawLine (319, 250, (int) (245 * Math.sin (y * Math.PI / 180)) + 319, (int) (245 * Math.cos (y * Math.PI / 180)) + 250);
		    c.drawLine (320, 249, (int) (245 * Math.sin (y * Math.PI / 180)) + 320, (int) (245 * Math.cos (y * Math.PI / 180)) + 249);

		}
	    }
	}

	// Flashes color wheel on and off
	for (int y = 0 ; y < 5 ; y++)
	{
	    c.setColor (Color.black);
	    c.fillOval (71, 1, 498, 498);
	    c.setColor (Color.white);
	    c.fillOval (75, 5, 490, 490);
	    // Draws all segements of color wheel
	    for (float x = 0 ; x < 1 ; x += 0.125)
	    {
		// if y is even, darken color wheel
		if (y % 2 == 0)
		{
		    customColor = Color.getHSBColor (x + 0.0f, 1f, 0.5f);
		}
		// else, brighten color wheel
		else
		{
		    customColor = Color.getHSBColor (x + 0.0f, 1f, 1f);
		}

		// http://www.java2s.com/Tutorials/Java/java.awt/Color/Java_Color_getHSBColor_float_h_float_s_float_b_.htm
		c.setColor (customColor);
		c.fillArc (75, 5, 490, 490, (int) (x * 360), -45);
	    }

	    // Redraws white dividers
	    for (int x = 0 ; x < 360 ; x += 45)
	    {
		c.setColor (Color.white);
		c.drawLine (320, 250, (int) (245 * Math.sin (x * Math.PI / 180)) + 320, (int) (245 * Math.cos (x * Math.PI / 180)) + 250);
		c.drawLine (319, 250, (int) (245 * Math.sin (x * Math.PI / 180)) + 319, (int) (245 * Math.cos (x * Math.PI / 180)) + 250);
		c.drawLine (320, 249, (int) (245 * Math.sin (x * Math.PI / 180)) + 320, (int) (245 * Math.cos (x * Math.PI / 180)) + 249);
	    }
	    // 1 second pause in program for "animation"
	    try
	    {
		Thread.sleep (1000);
	    }
	    catch (Exception e)
	    {
	    }
	}

	// Draws title over splashscreen
	c.setColor (Color.yellow);
	c.setFont (new Font ("Stencil", Font.PLAIN, 64));
	c.drawString ("SIMON SAYS", 140, 270);

	// 2 second pause in program for "watchability"
	try
	{
	    Thread.sleep (2000);
	}
	catch (Exception e)
	{
	}

	// Fades out splashscreen by covering screen in a decreasingly transparent black rectangle
	for (int x = 0 ; x <= 48 ; x++)
	{
	    c.setColor (new Color (0, 0, 0, x));
	    c.fillRect (0, 0, 640, 500);
	}

	// 0.01 second pause in program for "fade effect"
	try
	{
	    Thread.sleep (10);
	}
	catch (Exception e)
	{
	}
    }


    // Creates splashscreen thread at the console given
    public SplashScreen (Console con)
    {
	c = con;
    }


    // Runs display method
    public void run ()
    {
	display ();
    }
}
