package TestNG_Package;

import Actual_Class_Package.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Test2 {
   String message = "Manisha";	
   ChkClass messageUtil = new ChkClass(message);
	 
   @Test
   public void testSalutationMessage() {
      System.out.println("Inside testSalutationMessage()");
      message = "Hi!" + "Manisha";
      Assert.assertEquals(message,messageUtil.salutationMessage());
   }
}
