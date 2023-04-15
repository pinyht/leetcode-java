import java.util.HashMap;
import java.util.Map;

/**
 * 罗马数字转整数
 * 罗马数字包含以下七种字符: I， V， X， L，C，D 和 M。
 * 字符 数值
 * I 1
 * V 5
 * X 10
 * L 50
 * C 100
 * D 500
 * M 1000
 * 例如， 罗马数字 2 写做 II ，即为两个并列的 1 。12 写做 XII ，即为 X + II 。 27 写做  XXVII,
 * 即为 XX + V + II 。
 * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。
 * 数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。
 * 这个特殊的规则只适用于以下六种情况：
 * I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
 * X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。 
 * C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
 * 给定一个罗马数字，将其转换成整数。
 * 题目链接: https://leetcode.cn/problems/roman-to-integer/
 */
public class RomanToInt {
    // 打败100%
    public int romanToInt(String s) {
        int result = 0;
        int pre = getValue(s.charAt(0));   // 将第一个位置的值记作上一个值

        // 循环从1开始,每一次都加上一个字符的值,每次循环可以少查一次字符对应值
        for (int i = 1; i < s.length(); i++) {
            int cur = getValue(s.charAt(i)); // 获取当前字符值
            if (cur > pre) {
                // 如果当前字符的值比上一个字符大,表示是两个字符的组合,值减去上一个字符值,
                // 这里直接先减去上一个值,下一个值正常加,就能凑出cur - pre的形式
                result -= pre;
            } else {
                result += pre;
            }
            pre = cur;
        }
        // 最后加上一次pre值,完成所有字符值相加
        return result + pre;
    }

    public int getValue(char ch) {
        switch (ch) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
            default:
                return 0;
        }
    }

    // 效率低
    public int romanToInt2(String s) {
        Map<Character, Integer> table = new HashMap<>(7);
        table.put('I', 1);
        table.put('V', 5);
        table.put('X', 10);
        table.put('L', 50);
        table.put('C', 100);
        table.put('D', 500);
        table.put('M', 1000);

        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);
            int value = table.get(cur);
            if (i < s.length() - 1 && value < table.get(s.charAt(i + 1))) {
                result -= value;
            } else {
                result += value;
            }
        }
        return result;
    }

    // 效率太低
    public int romanToInt1(String s) {
        Map<String, Integer> table = new HashMap<>(7);
        table.put("I", 1);
        table.put("V", 5);
        table.put("X", 10);
        table.put("L", 50);
        table.put("C", 100);
        table.put("D", 500);
        table.put("M", 1000);

        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            String curStr = String.valueOf(s.charAt(i));
            if (i == 0) {
                result = table.get(curStr);
            } else {
                String lastStr = String.valueOf(s.charAt(i - 1));
                if ((curStr.equals("V") || curStr.equals("X")) && lastStr.equals("I")) {
                    result += (table.get(curStr) - table.get(lastStr) - table.get(lastStr));
                } else if ((curStr.equals("L") || curStr.equals("C")) && lastStr.equals("X")) {
                    result += (table.get(curStr) - table.get(lastStr) - table.get(lastStr));
                } else if ((curStr.equals("D") || curStr.equals("M")) && lastStr.equals("C")) {
                    result += (table.get(curStr) - table.get(lastStr) - table.get(lastStr));
                } else {
                    result += table.get(curStr);
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new RomanToInt().romanToInt("III"));
        System.out.println(new RomanToInt().romanToInt("IV"));
        System.out.println(new RomanToInt().romanToInt("IX"));
        System.out.println(new RomanToInt().romanToInt("LVIII"));
        System.out.println(new RomanToInt().romanToInt("MCMXCIV"));
    }
}
