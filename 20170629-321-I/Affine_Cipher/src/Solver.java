import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;

public class Solver {

	
	public static int[] ciphToNum(String a) {
		// changes the string of ciphertext to a array of numbers
		char[] temp = a.toCharArray();
		int[] thing = new int[a.length()];
		for (int i = 0; i < temp.length; i++) {
			thing[i] = temp[i] - 'a' + 0;
			// -64 is a space
		}
		
		return thing;
	}
	
	public static String numToPlain(int[] b) {
		
		String temp = "";
		for (int i = 0; i < b.length; i++) {
				temp = temp + String.valueOf((char) (b[i] + 'a' + 0));
		}
		
		return temp;
	}
	
	public static int[] calculation(int[] c, int a, int b) {
		/*
		 *  P = ( aC + b ) mod 26
		 *  P = a(C - b) mod 26
		 *  a can ONLY be 1, 3, 5, 7, 9, 11, 15, 17, 19, 21, 23, 25
		 *  
		 */
		int[] fVal = new int[c.length];
		for (int i = 0; i < c.length; i++) {
			fVal[i] = (a*(c[i] - b))%26;
			if (fVal[i] < 0) fVal[i] = fVal[i]+26;
			if (c[i] == -65) fVal[i] = -65;
		}
		
		return fVal;
	}
	
	public static String decoded(int[] f) {
		return numToPlain(f);
	}
	
	public static HashSet<String> initList() throws FileNotFoundException {
		// initalizes the hashset to compare the wordlist
		HashSet<String> words = new HashSet<String>();
		Scanner file = new Scanner(new File("enable1.txt"));
		while (file.hasNext()) {
			words.add(file.next().trim().toLowerCase());
		}
		file.close();
		
		return words;
	}
	
	public static String[] sToWordArray(String s) {
		// takes a string and returns it in a word-separated array
		int death = 0;
		String[] suffering = new String[StringUtils.countMatches(s, " ")];
		for (int i = 0; i <= (StringUtils.countMatches(s, " ")); i++) {
			suffering[i] = s.substring(0, s.indexOf(" "));
			death = i; System.out.println(death);
			s = s.substring(s.indexOf(" ")+1);
		}
		suffering[death+1] = s;
		System.out.println(Arrays.toString(suffering));
		
		return suffering;
	}
	
	public static String[] permutator(String[] s) throws FileNotFoundException {
		// takes a string[] of ciphertext
		// permutes through possible cipher settings, and compares to the wordlist.
		// if a word is found, move to next word in the array
		
		String[] words = s;
		HashSet<String> list = initList();
		
		int[] a = {1, 3, 5, 7, 9, 11, 15, 17, 19, 21, 23, 25};
		int[] b = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40}; // you disgust me
		
		for (int o = 0; o <= words.length; o++) {
			if (words[o] == null) break;
			for (int i = 0; i < a.length; i++) {
				for (int j = 0; j < b.length; j++) {
					words[o] = decoded(calculation(ciphToNum(words[o]), a[i], b[j]));
					if (list.contains(words[o])) {
						// found a word with the a/b combo. check the next word
						String tword = decoded(calculation(ciphToNum(words[o+1]), a[i], b[j]));
						
						if (list.contains(tword)) {
							// found the second word with the same a/b combo
							System.out.println("Found two: " + words[o] + " & " + tword);
						}
						
						System.out.println(o + ") found word: " + words[o] + ", with a=" + a[i] + " and b=" + b[j]);
					}
				}
			}
		}
		
		
		return words;
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		String code = "YEQ LKCV BDK XCGK EZ BDK UEXLVM QPLQGWSKMB QPLQGWSKMB QPLQGWSKMB QPLQGWSKMB QPLQGWSKMB QPLQGWSKMB QPLQGWSKMB QPLQGWSKMB ";
		int[] as = {1, 3, 5, 7, 9, 11, 15, 17, 19, 21, 23, 25};
		int ta = 21; int tb = 8;
		/*
		System.out.println(Arrays.toString(ciphToNum(TextCleaner.clean(code))));
		System.out.println(numToPlain(ciphToNum(TextCleaner.clean(code))));
		System.out.println(Arrays.toString(calculation(ciphToNum(TextCleaner.clean(code)), ta, tb)));
		System.out.println(decoded(calculation(ciphToNum(TextCleaner.clean(code)), ta, tb)));
		 */
		
		System.out.println(Arrays.toString(permutator(sToWordArray(TextCleaner.clean(code)))));
		
	}
}
