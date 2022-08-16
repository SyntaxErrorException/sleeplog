package test;

import java.text.DecimalFormat;
import java.time.Duration;
import java.time.LocalDateTime;

public class TestSomething {

	public static void main(String[] args) {
		LocalDateTime t1 = LocalDateTime.of(2022,8,14,23,5);
		LocalDateTime t2 = LocalDateTime.of(2022,8,15,7,35);
		long l = Duration.between(t1,t2).toMinutes();
		System.out.println(l);
		long h = l/60;
		long m = l%60;
		System.out.println(h + ":" + m);
		System.out.printf("%02d", h);
		System.out.println();
		DecimalFormat df = new DecimalFormat("00");
		System.out.println(df.format(h));
	}

}
