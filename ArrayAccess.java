/*
Andrew J Wood
COP-3252
Assignment 6
April 10, 2018

ArrayAccess is the main program that displays a simple GUI window that allows a user to:
    -Enter elements into an array
    -Search the array by index value
    -Search the array by value
The result will display or an exception will be displayed, defined in NumberNotFoundException.java
*/

// ArrayAccess.java
import apple.laf.JRSUIUtils;
import jdk.nashorn.internal.runtime.arrays.ArrayIndex;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.List;
import java.util.ListIterator;

public class ArrayAccess extends JFrame 
{
   private JTextField inputField;
   private JTextField retrieveField1;
   private JTextField retrieveField2;
   private JTextField outputField;
   private JPanel inputArea; 
   private JPanel retrieveArea;
   private JPanel outputArea;

   private int num;
   private int index = 0;
   private int array[] = new int[ 10 ];
   private String result;
   
   // set up GUI
   public ArrayAccess()
   {
      super( "Accessing Array values" );
      setLayout( new FlowLayout() );
      
      // set up input Panel
      inputArea = new JPanel();
      inputArea.add( new JLabel( "Enter array elements here" ) );
      inputField = new JTextField( 10 );
      inputArea.add( inputField );
      inputField.addActionListener( 
         new ActionListener()
         {
            public void actionPerformed( ActionEvent e )
            {
               /* Create a try block in which the application reads the number
                  entered in the inputField and assigns it to the next index 
                  in the array, then increments instance variable index. */

               try {
                   //Read the input value as a string
                   String valueRead = e.getActionCommand();

                   //The value may be incorrect - may throw NumberFormatException
                   int integerVal = Integer.parseInt(valueRead);

                   //add the integer to the array
                   array[index] = integerVal;
                   ++index; //increment index
               }
               /* Write catch handlers that catch the two types of exceptions
                  that the previous try block might throw (NumberFormatException
                  and ArrayIndexOutOfBoundsException), and display appropriate
                  messages in error message dialogs. */
               catch (NumberFormatException exception)
               {
                   //Display message instructing user to enter only integers
                   JOptionPane.showMessageDialog(null,"Please enter only integer values",
                           "Invalid Input",JOptionPane.ERROR_MESSAGE);
               }
               catch (ArrayIndexOutOfBoundsException exception)
               {
                   //Display message indicating the array capacity has been reached
                   JOptionPane.showMessageDialog(null,"Array may contain only 10 elements",
                           "Array Full",JOptionPane.ERROR_MESSAGE);
               }
               inputField.setText( "" );
            } // end method actionPerformed
         } // end anonymous inner class
      ); // end call to addActionListener
      
      // set up retrieve Panel
      retrieveArea = new JPanel( new GridLayout( 2, 2 ) );
      retrieveArea.add( new JLabel( "Enter number to retrieve" ) );
      retrieveField1 = new JTextField( 10 );
      retrieveArea.add( retrieveField1 );
      retrieveField1.addActionListener( 
         new ActionListener() 
         {
            public void actionPerformed( ActionEvent event ) 
            {
               /* Create a try block in which the application reads from 
                  retrieveField1 the number the user wants to find in the 
                  array, then searches the current array contents for the number.
                  If the number is found, the outputField should display all the 
                  indices in which the number was found. If the number is not 
                  found, a NumberNotFoundException should be thrown. */
               try {
                   String valueRead = event.getActionCommand();
                   int searchValue = Integer.parseInt(valueRead);

                   //NOTE - I did the below the way I did so I could practice Generic data structures.
                   //It could have been done using only primitive types using much less code.

                   List<Integer> intList = new ArrayList<>();

                   //add all array elements to ArrayList, primitive array types can't be boxed with asList
                   for (int i : array)
                   {
                       intList.add(i);
                   }

                   //now search the intList for the value entered
                   boolean valueFound = intList.contains(searchValue);
                   if (!valueFound)
                       throw new NumberNotFoundException();

                   //the value was found, add to a list
                   //create a list that will hold all of the index values
                   ArrayList<Integer> indexList = new ArrayList<>();
                   //create an iterator to iterate through list

                   ListIterator<Integer> iterator = intList.listIterator();

                   //add the index values that match to the indexList
                   while(iterator.hasNext())
                   {
                       int nextIndex = iterator.nextIndex();
                       int index = iterator.next();
                       if (iterator.next().equals(searchValue))
                       {
                           indexList.add(nextIndex);
                       }
                   }

                   //Now, display the values in the user box
                   System.out.print(indexList);
                   outputField.setText(indexList.toString());




               }
               catch (NumberFormatException exception)
               {
                   //Display message instructing user to enter only integers
                   JOptionPane.showMessageDialog(null,"Please enter only integer values",
                           "Invalid Input",JOptionPane.ERROR_MESSAGE);
               }
               catch (NumberNotFoundException exception)
               {
                   //Display message indicating the number was not found
                   JOptionPane.showMessageDialog(null,exception.getMessage(),
                           "Not Found",JOptionPane.ERROR_MESSAGE);
               }



               
               /* Write catch handlers that catch the two types of exceptions that
                  the try block might throw (NumberFormatException and 
                  NumberNotFoundException), and display appropriate messages 
                  in error message dialogs. */
                
               retrieveField1.setText( "" );
            } // end method actionPerformed
         } // end anonymous inner class
      ); // end call to addActionListener
            
      retrieveArea.add( new JLabel( "Enter index to retrieve" ) );
      retrieveField2 = new JTextField( 10 );
      retrieveArea.add( retrieveField2 );
      retrieveField2.addActionListener(
         new ActionListener() 
         {
            public void actionPerformed( ActionEvent event )
            {
               /* Create a try block in which the application reads from 
                  retrieveField2 the index of a value in the array, then 
                  displays the value at that index in the outputField. If the index
                  input by the user is not a number a NumberFormatException should
                  be thrown. If the number input by the user is outside the array 
                  bounds or represents an element in which the application has not
                  stored a value, an ArrayIndexOutOfBoundsException should 
                  be thrown. */
               
               /* Write catch handlers that catch the two types of exceptions
                  the try block might throw (NumberFormatException and 
                  ArrayIndexOutOfBoundsException), and display appropriate 
                  messages in error message dialogs. */
               
               retrieveField2.setText( "" );
            } // end anonymous inner class
         } // end new ActionListener
      ); // end call to addActionListener
      
      // set up output Panel
      outputArea = new JPanel();
      outputArea.add( new JLabel( "Result" ) );
      outputField = new JTextField( 30 );
      outputField.setEditable( false );
      outputArea.add( outputField );

      add( inputArea );
      add( retrieveArea );
      add( outputArea );
   }  // end constructor
} // end class ArrayAccess