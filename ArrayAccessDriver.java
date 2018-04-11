/*
Andrew J Wood
COP-3252
Assignment 6
April 10, 2018

The driver program for ArrayAccess.
*/

// ArrayAccessDriver.java
import javax.swing.JFrame;

public class ArrayAccessDriver
{   
   // execute application
   public static void main( String args[] )
   {
      ArrayAccess application = new ArrayAccess();
      application.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
      application.setSize( 500, 200 );
      application.setVisible( true );
   }
} // end class ArrayAccessDriver