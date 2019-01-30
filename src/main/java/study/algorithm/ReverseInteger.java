package study.algorithm;

// https://leetcode.com/problems/reverse-integer/
public class ReverseInteger {

	public static int reverse(int x) {
		
		int copyX = x;
		int result = 0;
		
		while(copyX!=0) {
			int tmp = copyX % 10;
			result = result*10 + tmp;
			copyX = copyX /10;
		}
		
        return result;
    }
	
}
