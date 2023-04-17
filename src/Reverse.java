/**
 * 整数反转
 * 给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
 * 如果反转后整数超过 32 位的有符号整数的范围 [−2^31, 2^31 − 1] ，就返回 0。
 * 假设环境不允许存储 64 位整数（有符号或无符号）。
 * 题目链接: https://leetcode.cn/problems/reverse-integer/
 */
public class Reverse {
    // 打败100%,采用除法和模运算进行反转
    public int reverse(int x) {
        // x为0的时候不用反转直接返回
        if (x == 0) {
            return x;
        }
        int result = 0; // 初始化结果
        int oldResult = 0; // 初始化上一次计算的结果,用于比较是否溢出
        // 如果末尾有0,则去掉所有末尾的0
        while (x % 10 == 0) {
            x /= 10;
        }
        // 这里条件用x!=0,可以兼容负数的情况,负数与10取余数总为负数,计算过程相当于带符号位相加
        while (x != 0) {
            oldResult = result; // 先暂存未进行左移相加的结果
            result = result * 10 + x % 10; // result*10将上一次结果左移一位,再加上本次的个位数,实现逆序
            x /= 10; // 相当于x右移一位
            // 用当前的结果/10,如果发生溢出,和刚才暂存的数肯定就不同,直接返回0
            if (result / 10 != oldResult) {
                return 0;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MIN_VALUE);
        System.out.println(new Reverse().reverse(-2147483412));
        System.out.println(new Reverse().reverse(1534236469));
        System.out.println(new Reverse().reverse(901000));
        System.out.println(new Reverse().reverse(123));
        System.out.println(new Reverse().reverse(-123));
        System.out.println(new Reverse().reverse(120));
        System.out.println(new Reverse().reverse(0));
    }
}
