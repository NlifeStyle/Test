package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

public class SessionService {
	private final static String BEAN_KEY = "beanHandlerName";

	public static BeanHandler getBeanHandler(HttpServletRequest req) {
		BeanHandler bh = (BeanHandler) req.getSession().getAttribute(
				getBeanHanderName());
		return bh;
	}

	private static String getBeanHanderName() {
		Properties pro = new Properties();
		try {
			InputStream is = SessionService.class
					.getResourceAsStream("/config/config.properties");
			pro.load(is);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pro.getProperty(BEAN_KEY);
	}

	public static void main(String args[]) {
		SessionService.getBeanHanderName();
	}
}
