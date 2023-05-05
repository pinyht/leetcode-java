/**
 * 字符串转换整数 (atoi)
 * 请你来实现一个 myAtoi(string s) 函数，使其能将字符串转换成一个 32 位有符号整数（类似 C/C++ 中的 atoi 函数）。
 * 题目链接: https://leetcode.cn/problems/string-to-integer-atoi/
 */
public class MyAtoi {
    // 优化版本,打败100%
    public int myAtoi(String s) {
        char[] arr = s.toCharArray(); // 字符串转为数组
        int len = arr.length; // 字符串长度
        int i = 0; // 遍历下标
        int sign = 1; // 正负数标记
        int result = 0; // 结果

        // 去除前面的空格
        while (i < len && arr[i] == ' ') {
            i++;
        }

        // 判断最先出现的符号字符串
        if (i < len && (arr[i] == '-' || arr[i] == '+')) {
            sign = arr[i] == '-' ? -1 : 1; // 当出现-时,标记为-1用作结果计算
            i++;
        }

        // 累加数字
        while (i < len) {
            char c = arr[i];
            if (c < '0' || c > '9') {
                // 出现非数字时终止
                break;
            }
            int digit = c - '0'; // 计算字符对应的int值
            // 判断是否溢出
            if (Integer.MAX_VALUE / 10 < result
                    || (Integer.MAX_VALUE / 10 == result && Integer.MAX_VALUE % 10 < digit)) {
                // 根据符号返回对应的整型最大值或最小值
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            // 进位累加
            result = result * 10 + digit;
            i++;
        }
        // 组合数字与符号
        return result * sign;
    }

    // 效率高
    public int myAtoi1(String s) {
        char[] arr = s.toCharArray();
        int result = 0;
        boolean hasValid = false;
        int neg = 1;
        int last = 0;
        for (char c : arr) {
            if (c == ' ' && !hasValid) {
                continue;
            }
            if ((c == '-' || c == '+') && !hasValid) {
                hasValid = true;
                neg = c == '-' ? -1 : 1;
                continue;
            }
            if (c >= '0' && c <= '9') {
                hasValid = true;
                last = result;
                result = result * 10 + Integer.valueOf(String.valueOf(c));
                if (neg * result / 10 != neg * last) {
                    if (neg < 0) {
                        return Integer.MIN_VALUE;
                    } else {
                        return Integer.MAX_VALUE;
                    }
                }
            } else {
                break;
            }
        }
        return result * neg;
    }

    public static void main(String[] args) {
        String s;
        s = "10000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000522545459";
        System.out.println(new MyAtoi().myAtoi(s));
        s = "   +0 123";
        System.out.println(new MyAtoi().myAtoi(s));
        s = "00000-42a1234";
        System.out.println(new MyAtoi().myAtoi(s));
        s = "+-12";
        System.out.println(new MyAtoi().myAtoi(s));
        s = "+1";
        System.out.println(new MyAtoi().myAtoi(s));
        s = "42";
        System.out.println(new MyAtoi().myAtoi(s));
        s = "  -42";
        System.out.println(new MyAtoi().myAtoi(s));
        s = "4193 with words";
        System.out.println(new MyAtoi().myAtoi(s));
    }
}
