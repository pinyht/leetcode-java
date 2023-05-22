/**
 * 最长公共前缀
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * 如果不存在公共前缀，返回空字符串 ""。
 * 题目链接: https://leetcode.cn/problems/longest-common-prefix/
 */
public class LongestCommonPrefix {
    // 效率最高,打败100%
    public String longestCommonPrefix(String[] strs) {
        StringBuilder maxPrefix = new StringBuilder();
        // 因为1<=strs.length<=200,所以字符串数组一定至少有一个字符串,先以第一个字符串的长度作为最小长度
        int minLen = strs[0].length();
        // 获取最小的字符串长度,超过这个长度不存在公共前缀的情况
        for (String s : strs) {
            minLen = Math.min(s.length(), minLen);
        }
        // 因为0<=strs[i].length<=200,有可能出现空字符串
        if (minLen == 0) {
            return "";
        }
        for (int j = 0; j < minLen; j++) {
            // 已第一个字符串的当前下标字符作为比较字符
            char cur = strs[0].charAt(j);
            for (String str : strs) {
                if (str.charAt(j) != cur) {
                    // 比对不成立直接返回
                    return maxPrefix.toString();
                }
            }
            // 比对成立,追加字符
            maxPrefix.append(cur);
        }
        return maxPrefix.toString();
    }
    // 效率不高
    public String longestCommonPrefix1(String[] strs) {
        int len = strs.length;
        StringBuilder maxPrefix = new StringBuilder();
        int maxLen = 0;
        int[] strLen = new int[len];
        for (int i = 0; i < len; i++) {
            strLen[i] = strs[i].length();
            maxLen = Math.max(strLen[i], maxLen);
        }
        for (int i = 0; i < maxLen; i++) {
            char cur = '\0';
            for (int j = 0; j < len; j++) {
                if (i > strLen[j] - 1) {
                    return maxPrefix.toString();
                }
                if (j == 0)  {
                    cur = strs[j].charAt(i);
                    continue;
                }
                if (strs[j].charAt(i) != cur) {
                    return maxPrefix.toString();
                }
            }
            maxPrefix.append(cur);
        }
        return maxPrefix.toString();
    }

    public static void main(String[] args) {
        String[] strs;
        strs = new String[] {"flower","flow","flight"};
        System.out.println(new LongestCommonPrefix().longestCommonPrefix(strs));
        strs = new String[] {"dog","racecar","car"};
        System.out.println(new LongestCommonPrefix().longestCommonPrefix(strs));
    }
}
