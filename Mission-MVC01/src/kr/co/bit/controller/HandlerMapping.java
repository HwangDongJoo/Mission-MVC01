package kr.co.bit.controller;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class HandlerMapping {
	private Map<String, Controller> mappings = null;
	
	/*public HandlerMapping() {
		mappings = new HashMap<>();
		mappings.put("/list.do", new ListController());
		mappings.put("/loginForm.do", new LoginFormController());
		mappings.put("/login.do", new LoginController());
		mappings.put("/logout.do", new LogoutController());
		mappings.put("/writeForm.do", new WriteFormController());
		mappings.put("/write.do", new WriteController());
	}*/
	
	public HandlerMapping(String configName) {
		mappings = new HashMap<>();
		Properties prop = new Properties();
		try {
			InputStream inStream = new FileInputStream(configName);
			prop.load(inStream);
			
			//System.out.println(prop.getProperty("/list.do"));
			Set<Object> keys = prop.keySet();
			for(Object key : keys){
				String className = prop.getProperty(key.toString());
				System.out.println(key.toString() + " : " + className);
				Class<?> clz = Class.forName(className);
				mappings.put(key.toString(), (Controller)clz.newInstance());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public Controller getController(String uri){
		return mappings.get(uri);
	}
	
}
