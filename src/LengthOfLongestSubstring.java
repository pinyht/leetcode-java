import java.util.HashSet;
import java.util.Set;

/**
 * 无重复字符的最长子串
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
 * 题目链接:
 * https://leetcode.cn/problems/longest-substring-without-repeating-characters/
 */
public class LengthOfLongestSubstring {
    // 打败100%,用数组作为字符标记,获取速度最快,双指针一次遍历求出结果
    public int lengthOfLongestSubstring(String s) {
        int len = s.length(); // 字符串长度
        int max = 0; // 最长子串长度
        int[] arr = new int[128]; // 字符串由英文字母、数字、符号和空格组成,出现的字符在ascii码范围内,128位大小可以存下所有字符
        int left = 0; // 左指针,子字符串的左边界

        // 循环检索字符,一个字符一个字符比对,不断移动右指针范围,根据是否出现重复字符,跨域移动左指针
        for (int right = 0; right < len; right++) {
            char c = s.charAt(right);
            // 这里如果遇到重复字符,并且还未更新记录对应下标时,左指针直接右移到上一次出现的下标+1位置
            left = Math.max(arr[c], left);
            // 每一次都比对更新最大子串长度,如果当前计算的子串长度大于已经记录的最大长度就更新替代
            max = Math.max(max, right - left + 1);
            // 数组默认值为0,为了区分没有记录过的值和下标为0的值,将下标直接+1存储,加上left需要移动到从上一次出现重复字符的位置+1,
            // 这样存储刚好拿到下标直接可以用
            arr[c] = right + 1;
        }
        return max;
    }

    // 效率不高,采用双指针滑动窗口方案,右指针只要没发现重复字符就一直移动
    public int lengthOfLongestSubstring1(String s) {
        int len = s.length(); // 字符串长度
        int max = 0; // 最长子串长度
        int right = 0; // 右指针
        Set<Character> record = new HashSet<>(len);
        for (int i = 0; i < len; i++) {
            if (i > 0) {
                // 当i>0的时候,删除上一个字符,左指针右移
                record.remove(s.charAt(i - 1));
            }
            // 右指针只要没发现重复字符就一直右移扩大
            while (right < len && !record.contains(s.charAt(right))) {
                record.add(s.charAt(right));
                max = Math.max(max, right - i + 1); // 当前子串如果比max更大就更新max
                right++;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        String s = "abcabcbb";
        System.out.println(new LengthOfLongestSubstring().lengthOfLongestSubstring(s));
        s = "dvdf";
        System.out.println(new LengthOfLongestSubstring().lengthOfLongestSubstring(s));
        s = "pawwkw";
        System.out.println(new LengthOfLongestSubstring().lengthOfLongestSubstring(s));
        s = "abba";
        System.out.println(new LengthOfLongestSubstring().lengthOfLongestSubstring(s));
    }
}
