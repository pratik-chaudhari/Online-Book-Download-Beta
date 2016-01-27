package TestNG_Package.TestNG_Classes;

import java.math.BigInteger;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import OnlineBookWebservice.Model.BooksTag;
import sun.security.util.BigInt;

public class Class_test {
	BooksTag bt=new BooksTag();
	BigInt myInteger=new BigInt(2332543);
	
	@Test
	public void addData()
	{
		System.out.println("\nAdding data Book ISBN");
		bt.setIsbn(myInteger);
	}
	
	@Test(dependsOnMethods={"addData"})
	public void retrieveData()
	{
		System.out.println("\nRetrieving data Book ISBN");
		BigInt actualData=bt.getIsbn();
		Assert.assertEquals(bt.getIsbn(),actualData);
	}
}
