package com.lzj;

public class Test {

	/*public static void main(String[] args) {
		float open = (float) 11.02;
		
		for (int i = -10; i <= 10; i++) {
			System.out.println(open * (1+i*0.01));
			
		}
	}*/
	public static void main(String[] args) {
		int d = 88000;
		for (int i = 1; i <= 12; i++) {
			int f = (int)(d*Math.pow(1.25,i));
			System.out.println((i)+"     " +(int)(f));
		}
	}
}
