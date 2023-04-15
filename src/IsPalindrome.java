import java.util.ArrayList;
import java.util.List;

/**
 * 回文数
 * 给你一个整数 x ，如果 x 是一个回文整数，返回 true ；否则，返回 false 。
 * 回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 * 例如，121 是回文，而 123 不是。
 * 题目链接: https://leetcode.cn/problems/palindrome-number/
 */
public class IsPalindrome {
    // 效率高,打败97.98%,循环只处理一半位数的数字
    public boolean isPalindrome(int x) {
        // 负数不可能是回文数,末位为0也不可能是回文数,因为整形不可能是0,计算倒序数也会出错
        // x不为0时,如果x%10=0,代表个位为0
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }
        int revNum = 0; // 记录倒序数
        while (x > revNum) {
            // 第一次revNum为0,0*10还是0,就可以成功记录个位数字,以后每次乘以10,就可以实现已有数字左移一位的功能
            revNum = revNum * 10 + (x % 10);
            if (x == revNum) {
                // 当x为奇数时候,算到x==revNum的时候,revNum已经处理了一半加1个位置,
                // 这时候如果是回文数,x与revNum刚好相等,终止循环,可以保证x不会比revNum多一位,并且两数相等
                break;
            }
            x = x / 10;     // 相当于右移一位,舍弃已经处理过的数字
        }
        // x舍弃右边一半,revNum处理完倒序右边一半,如果为回文数应该是一样的值
        return x == revNum;
    }

    // 效率不高
    public boolean isPalindrome4(int x) {
        if (x < 0) {
            return false;
        }
        if (x == 0) {
            return true;
        }
        String seq = String.valueOf(x);
        String rev = "";
        int value = 0;
        while (x > 0) {
            value = x % 10;
            rev += value;
            x /= 10;
        }
        if (!rev.equals(seq)) {
            return false;
        }
        return true;
    }

    // 效率太低
    public boolean isPalindrome3(int x) {
        if (x < 0) {
            return false;
        }
        if (x == 0) {
            return true;
        }
        int value = 0;
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        while (x > 0) {
            value = x % 10;
            list1.add(value);
            list2.add(0, value);
            x /= 10;
        }
        if (!list1.equals(list2)) {
            return false;
        }
        return true;
    }

    // 效率不高
    public boolean isPalindrome2(int x) {
        if (x < 0) {
            return false;
        }
        int value = 0;
        List<Integer> list = new ArrayList<>();
        while (x > 0) {
            value = x % 10;
            list.add(0, value);
            x /= 10;
        }

        int i = 0, j = list.size() - 1;
        while (i < j) {
            if (list.get(i++) != list.get(j--)) {
                return false;
            }
        }
        return true;
    }

    // 效率不高
    public boolean isPalindrome1(int x) {
        if (x < 0) {
            return false;
        }
        String str = String.valueOf(x);
        int i = 0, j = str.length() - 1;
        while (i < j) {
            if (str.charAt(i++) != str.charAt(j--)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new IsPalindrome().isPalindrome(201));
    }
}
