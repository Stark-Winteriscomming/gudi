package test.user;

import com.spring.user.service.CustomDeamon;

public class Main {
	public static void main(String[] args) throws Exception {
		CustomDeamon c = new CustomDeamon();
		c.add("abc");
		c.add("bcd");
		Thread t = new Thread(c); 
		t.start();
		Thread.sleep(5000);
		c.release("abc");
		c.release("bcd");
		
	}
}
