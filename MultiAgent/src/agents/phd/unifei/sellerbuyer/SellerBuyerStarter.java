package agents.phd.unifei.sellerbuyer;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class SellerBuyerStarter extends Thread {

	private final String jadeBoot_CLASS_NAME = "jade.Boot";

	private final String MAIN_METHOD_NAME = "main";
	
	private final String PACKAGE = "agents.phd.unifei";

	private final String ACTOR_NAMES_args = "seller:agents.phd.unifei.sellerbuyer.BookSellerAgent;buyer:agents.phd.unifei.sellerbuyer.BookBuyerAgent(The-Lord-of-the-rings)";		

	private final String GUI_args = "-gui";

	private final Class<?> secondClass;

	private final Method main;

	private final String[] params;

	public SellerBuyerStarter() throws ClassNotFoundException, SecurityException, NoSuchMethodException {
		secondClass = Class.forName(jadeBoot_CLASS_NAME);
		main = secondClass.getMethod(MAIN_METHOD_NAME, String[].class);
		params = new String[] { GUI_args, ACTOR_NAMES_args };
	}

	@Override
	public void run() {
		try {
			main.invoke(null, new Object[] { params });
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
			ex.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		try {
			SellerBuyerStarter jade = new SellerBuyerStarter();
			jade.run();
		} catch (ClassNotFoundException | SecurityException | NoSuchMethodException e) {
			e.printStackTrace();
		}		
	}	
}