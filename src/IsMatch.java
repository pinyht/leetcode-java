/**
 * 正则表达式匹配
 * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
 * 题目链接: https://leetcode.cn/problems/regular-expression-matching/
 */
public class IsMatch {
    // 动态规划方法,打败100%
    public boolean isMatch(String s, String p) {
        int m = s.length(); // s的长度
        int n = p.length(); // p的长度
        boolean[][] f = new boolean[m + 1][n + 1];  // 初始化记录表的长度为字符串长度+1,0下标存储两个空串的情况
        f[0][0] = true;     // 初始化两个空串匹配的结果,空串和空串匹配的结果为true
        for (int i = 0; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (p.charAt(j - 1) == '*') {
                    // 出现星号,先取左移两个下标的结果
                    f[i][j] = f[i][j - 2];
                    // 左移一位,取星号前面的字符进行对比
                    if (matches(s, p, i, j - 1)) {
                        f[i][j] = f[i][j] || f[i - 1][j];
                    }
                } else {
                    // 比对当前两个下标对应字符的结果
                    if (matches(s, p, i, j)) {
                        // 如果匹配,沿用上一个对角线对比结果
                        f[i][j] = f[i - 1][j - 1];
                    }
                }
            }
        }
        return f[m][n]; // 初始化的时候,做了长度+1,这里直接返回m和n
    }

    // 判断两个字符串对应下标的字符是否匹配,这里需要将下标-1来获取实际对应位置的值
    public boolean matches(String s, String p, int i, int j) {
        if (i == 0) {
            // 当i为0时,代表是空串,与任何表达式匹配都是false
            return false;
        }
        if (p.charAt(j - 1) == '.') {
            // .代表任意字符,和任何字符都可以匹配
            return true;
        }
        return s.charAt(i - 1) == p.charAt(j - 1);
    }

    public static void main(String[] args) {
        String s, p;
        s = "mississippi";
        p = "mis*is*p*.";
        System.out.println(new IsMatch().isMatch(s, p));
        s = "aab";
        p = "c*a*b";
        System.out.println(new IsMatch().isMatch(s, p));
        s = "aa";
        p = "a";
        System.out.println(new IsMatch().isMatch(s, p));
        s = "aa";
        p = "a*";
        System.out.println(new IsMatch().isMatch(s, p));
        s = "ab";
        p = ".*";
        System.out.println(new IsMatch().isMatch(s, p));
    }
}
