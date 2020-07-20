package test;

public class CustomSingleton {
	private static CustomSingleton singleton; 
	
	public static CustomSingleton getInstance() {
		if(singleton == null) singleton = new CustomSingleton();
		return singleton; 
	}
}
