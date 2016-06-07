/*
작성자: 안우진 (woojin7.ahn@samsung.com)
작성일: 2016. 5. 19.
*/
package chap5;

import java.io.FileInputStream;
import java.util.Scanner;

class Div {
	public Div(char re, char up) {
		// TODO Auto-generated constructor stub
		this.re = re;
		this.up = up;
	}
	public char re; // 남은
	public char up; // 올림
}

public class Solution {
	static int testcases = 0;
	
	//static String[] input = new String[2];  
	static char[][] input = new char[2][21];
	static char[][] r_input = new char[2][21]; // 반전
	// 올림을 저장하기 위한 char array 정의
	static char[] up = new char[21];
	
	static char[] re = new char[21];
	static char[] Answer = new char[21];
	static char[] realAnswer = new char[21];
	static String s;
	
	/*
	 * static { for (int i = 0; i < up.length; i++ ) { up[i] = '0'; re[i] = '0';
	 * Answer[i] = '0'; } }
	 */
	
	static Div plus(char c1, char c2) {
		if(c1 == '0' && c2 == '0') {
			return new Div('0','0');
		} else if ((c1 == '+' && c2 == '0') || (c1 == '0' && c2 == '+')) {
			return new Div('+','0');
		} else if ((c1 == '-' && c2 == '0') || (c1 == '0' && c2 == '-')) {
			return new Div('-','0');
		} else if (c1 == '+' && c2 == '+') {
			return new Div('0','+');
		} else if (c1 == '-' && c2 == '-') {
			return new Div('0','-');
		} else if ((c1 == '+' && c2 == '-') || (c1 == '-' && c2 == '+')) {
			return new Div('0','0');
		} else {
			return null;
		}
	}
	
	public static void main(String[] args) throws Throwable {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("input.txt"));
		Scanner sc = new Scanner(System.in);
		
		testcases = sc.nextInt();
		for (int i = 0; i < testcases; i++) {
			for (int j = 0; j < up.length; j++) { // 변수 초기화
				r_input[0][j] = '0';
				r_input[1][j] = '0';
				up[j] = '0';
				re[j] = '0';
				Answer[j] = '0';
				realAnswer[j] = '0';
			}
			input[0] = sc.next().toCharArray();
			input[1] = sc.next().toCharArray();
			
			for (int j = input[0].length - 1, k = 0; j >= 0 ; j--,k++ ) {
				r_input[0][k] = input[0][j];
			}
			for (int j = input[1].length - 1, k = 0; j >= 0 ; j--,k++ ) {
				r_input[1][k] = input[1][j];
			}
			
			for (int k = 0; k < Math.max(input[0].length, input[1].length); k++ ) {
				// 더하면서 갑시다.
				Div temp;
				temp = plus(r_input[0][k], r_input[1][k]);
				//System.out.println(temp.re); 남은수
				//System.out.println(temp.up); 올림
				re[k] = temp.re;
				up[k+1] = temp.up;
			}
			
			for (int k = 0; k < up.length; k++) {
				Div temp;
				temp = plus(re[k],up[k]);
				if (temp.up != '0') {
					up[k + 1] = temp.up;
					Answer[k] = temp.re;
				} else
					Answer[k] = temp.re;
			}
			
			// 원래 숫자대로 다시 뒤집기
			for (int j = Answer.length - 1, k = 0; j >= 0 ; j--,k++ ) {
				realAnswer[k] = Answer[j];
			}
			s = new String(realAnswer, 0, realAnswer.length);	// 출력을 위해 char array를 string으로 변환합니다.
			if (!s.contains("+") && !s.contains("-")) // +나 -가 없으면 0으로 간주합니다.
				System.out.println("# " + (i+1) + "0");
			else
				System.out.println("# " + (i+1) + s.replaceFirst("^0{1,}", ""));	// 앞의 0을 제거합니다.		
		}
		sc.close();
	}
}
