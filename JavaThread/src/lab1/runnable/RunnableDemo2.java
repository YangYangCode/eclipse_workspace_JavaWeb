package lab1.runnable;

import java.text.SimpleDateFormat;
import java.util.Random;

public class RunnableDemo2 {
	public static void main(String[] args) {
		Runnable job1 = () -> {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S E");
		};
		
		Runnable job2 = () -> {
			System.out.println("電腦選號: " + new Random().nextInt(100));
		};
		
	}
	
	
}
