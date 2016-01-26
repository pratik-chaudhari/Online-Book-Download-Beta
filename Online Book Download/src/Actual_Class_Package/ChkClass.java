package Actual_Class_Package;
/**
 * 
 */

/**
 * @author Pratik
 *
 */
public class ChkClass {
   private String message;

   // Constructor
   // @param message to be printed
   public ChkClass(String message) {
      this.message = message;
   }

   // prints the message
   public String printMessage() {
      System.out.println(message);
      return message;
   }

   // add "Hi!" to the message
   public String salutationMessage() {
      message = "Hi!" + message;
      System.out.println(message);
      return message;
   }
}
