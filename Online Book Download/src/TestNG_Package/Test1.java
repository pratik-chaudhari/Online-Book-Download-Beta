package TestNG_Package;

import Actual_Class_Package.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Test1 {
   String message = "Manisha";
   ChkClass messageUtil = new ChkClass(message);

   @Test
   public void testPrintMessage() {
      System.out.println("Inside testPrintMessage()");
      Assert.assertEquals(message, messageUtil.printMessage());
   }
}