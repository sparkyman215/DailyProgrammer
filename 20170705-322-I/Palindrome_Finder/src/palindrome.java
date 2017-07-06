import java.math.BigDecimal;
import java.math.BigInteger;

public class palindrome {
	static int count = 0;
	public static void main(String[] args) {
		Double test = new Double(0);
		long startTime = System.nanoTime();
		
		int len = 7;
		
		System.out.println("Your palindrome is " + new BigDecimal(pal(len).toString()).stripTrailingZeros().toPlainString());
		long endTime = (System.nanoTime() - startTime);	double secondTime = (double)endTime / 1000000000;
		System.out.println("Took, " + count + " iterations over " + secondTime + " seconds");
	}
	public static Double pal(int len) {
		Double p = new Double(45);
		Double m1 = new Double(Math.pow(10, len)); Double m2 = new Double(Math.pow(10, len));
		
		if (len%2 == 0) {
			return evenP(len);
		}
			Double t1 = m1; Double t2 = m2;
			for (Double i = t1; i >= t1*.8; i--) {
				
				for (Double j = t2; j >= t2*.8; j--) {
					System.out.println(i + " " + j + " = " + i*j);
					p = i*j;
					count++;
					if (p.equals(reverse(p))) {
						return p;
					}
				}
				
			}
		
		
		
		return p;
	}
	public static Double reverse(Double start) {
		String temp = new BigDecimal(start.toString()).stripTrailingZeros().toPlainString();
		String t2 = "";
		for (int i = temp.length()-1; i >= 0; i--) {
			t2 = t2 + temp.charAt(i);
		}
		Double f = new Double(t2);
		
		return f;
	}
	public static Double evenP(int len) {
		Double temp = new Double(0);
		String nines = "";
		for (int i = 0; i < len/2; i++) { nines = nines + "9"; }
		for (int i = 0; i < len; i++) { nines = nines + "0"; }
		for (int i = 0; i < len/2; i++) { nines = nines + "9"; }
		
		temp = Double.parseDouble(nines);
		System.out.println(temp);
		
		return temp;
	}

}
