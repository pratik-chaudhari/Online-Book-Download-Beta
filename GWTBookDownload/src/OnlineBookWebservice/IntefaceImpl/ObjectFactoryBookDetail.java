package OnlineBookWebservice.IntefaceImpl;

public class ObjectFactoryBookDetail {

	public static  Object getIntance(String str) {
		Object object=new Object();
		
		switch (str) {
		case key:
			object=detailsDAOImpl;
			return object;
		

		default:
			return object;
		
		}
	}
	private final static String key="bd";
	private final static BookDetailsDAOImpl detailsDAOImpl=new BookDetailsDAOImpl();	 
}
