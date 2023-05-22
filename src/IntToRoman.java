/**
 * 整数转罗马数字
 * 给你一个整数，将其转为罗马数字。
 * 题目链接: https://leetcode.cn/problems/integer-to-roman/
 */
public class IntToRoman {
    // 常规较快解法,偶尔打败100%,这个题目无法稳定打败100%,平台问题
    public String intToRoman(int num) {
        StringBuilder result = new StringBuilder();
        while (num > 0) {
            if (num >= 1000) {
                num -= 1000;
                result.append("M");
            } else if (num >= 900) {
                num -= 900;
                result.append("CM");
            } else if (num >= 500) {
                num -= 500;
                result.append("D");
            } else if (num >= 400) {
                num -= 400;
                result.append("CD");
            } else if (num >= 100) {
                num -= 100;
                result.append("C");
            } else if (num >= 90) {
                num -= 90;
                result.append("XC");
            } else if (num >= 50) {
                num -= 50;
                result.append("L");
            } else if (num >= 40) {
                num -= 40;
                result.append("XL");
            } else if (num >= 10) {
                num -= 10;
                result.append("X");
            } else if (num >= 9) {
                num -= 9;
                result.append("IX");
            } else if (num >= 5) {
                num -= 5;
                result.append("V");
            } else if (num == 4) {
                num -= 4;
                result.append("IV");
            } else {
                num -= 1;
                result.append("I");
            }
        }
        return result.toString();
    }

    public static void main(String[] args) {
        int num;
        num = 3;
        System.out.println(new IntToRoman().intToRoman(num));
        num = 4;
        System.out.println(new IntToRoman().intToRoman(num));
        num = 9;
        System.out.println(new IntToRoman().intToRoman(num));
        num = 58;
        System.out.println(new IntToRoman().intToRoman(num));
        num = 1994;
        System.out.println(new IntToRoman().intToRoman(num));
    }
}
