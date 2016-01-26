package TestNG_Package;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

public class TestNG_Class {
  @Test
  public void f() {
	  System.out.println("\nCheking");
  }
  @BeforeMethod
  public void beforeMethod() 
  {
	  System.out.println("\n IN Before Method");
  }

}
