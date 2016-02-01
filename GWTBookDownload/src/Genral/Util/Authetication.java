package Genral.Util;

public class Authetication {
 public static boolean Login(String userName ,String Password) {
	if(userName.equals("testUser") && Password.equals("password"))
	   return true;
	else
	   return false;
}
}
