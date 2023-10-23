package org.Learning;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RomanToInteger {
	
	public static int romanToIntegerConverter(String romanString) {
		int integerValue = 0;
		
		boolean identifyISubstraction = false,identifyXSubstraction = false,identifyCSubstraction = false;
		
		int romanStringLength = romanString.length();
		for(int i = 0; i<romanStringLength; i++) {
			char getCharInRoman = romanString.charAt(i);
			
			if(getCharInRoman == 'I') {
				integerValue = integerValue + 1;
				identifyISubstraction = true;
			} else if (getCharInRoman == 'V' && identifyISubstraction) {
				integerValue = integerValue + 3;
			}else if (getCharInRoman == 'V' && !identifyISubstraction) {
				integerValue = integerValue + 5;
			}else if (getCharInRoman == 'X' && identifyISubstraction) {
				integerValue = integerValue + 8;
				identifyXSubstraction = true;
			}
			else if (getCharInRoman == 'X' && !identifyISubstraction) {
				integerValue = integerValue + 10;
				identifyXSubstraction = true;
			}else if (getCharInRoman == 'L' && identifyXSubstraction) {
				integerValue = integerValue + 30;
			}else if (getCharInRoman == 'L' && !identifyXSubstraction) {
				integerValue = integerValue + 50;
			}else if (getCharInRoman == 'C' && identifyXSubstraction) {
				integerValue = integerValue + 80;
				identifyCSubstraction = true;
			}else if (getCharInRoman == 'C' && !identifyXSubstraction) {
				integerValue = integerValue + 100;
				identifyCSubstraction = true;
			}else if (getCharInRoman == 'D' && identifyCSubstraction) {
				integerValue = integerValue + 300;
			}else if (getCharInRoman == 'D' && !identifyCSubstraction) {
				integerValue = integerValue + 500;
			}else if (getCharInRoman == 'M' && identifyCSubstraction) {
				integerValue = integerValue + 800;
			}else if (getCharInRoman == 'M' && !identifyCSubstraction) {
				integerValue = integerValue + 1000;
			}
			//System.out.println(integerValue);
		}
		
		return integerValue;
	}
	
	//Alternative
	/*
	 public static int romanToInt(String s) {
         int ans = 0, num = 0;
        for (int i = s.length()-1; i >= 0; i--) {
            switch(s.charAt(i)) {
                case 'I': num = 1; break;
                case 'V': num = 5; break;
                case 'X': num = 10; break;
                case 'L': num = 50; break;
                case 'C': num = 100; break;
                case 'D': num = 500; break;
                case 'M': num = 1000; break;
            }
            if (4 * num < ans) ans -= num;
            else ans += num;
        }
        return ans;
    }
    
    public int romanToInt(String s) {
    
    int answer = 0, number = 0, prev = 0;

    for (int j = s.length() - 1; j >= 0; j--) {
        switch (s.charAt(j)) {
            case 'M' -> number = 1000;
            case 'D' -> number = 500;
            case 'C' -> number = 100;
            case 'L' -> number = 50;
            case 'X' -> number = 10;
            case 'V' -> number = 5;
            case 'I' -> number = 1;
        }
        if (number < prev) {
            answer -= number;
        }
        else {
            answer += number;
        }
        prev = number;
    }
    return answer;
}
    */
	
	
	
		
		
		

}
