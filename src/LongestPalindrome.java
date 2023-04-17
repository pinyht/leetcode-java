/**
 * 最长回文子串
 * 给你一个字符串 s，找到 s 中最长的回文子串。
 * 如果字符串的反序与原始字符串相同，则该字符串称为回文字符串。
 * 题目链接: https://leetcode.cn/problems/longest-palindromic-substring/
 */
public class LongestPalindrome {
    // 打败100%,优化版中心扩展算法
    // 算法的核心是将奇偶情况合成一次循环内计算,从中心点开始递归计算,跳过了左右两侧不可能出现更大子串的情况
    public String longestPalindrome(String s) {
        // 将字符串转换为数组,效率会高不少
        char[] arr = s.toCharArray();
        // 获取数组长度
        int arrLen = arr.length;
        // 获取最长回文子串信息,返回值包含两个数,起始位置和长度,如[0, 3]代表起始位置是0,长度是3
        // 从原始数组中点开始递归计算
        int[] max = expandFromCenter(arr, arrLen / 2, 0);
        // 截取最大长度子串,max[0]==起始下标,max[0]+max[1]==起始下标+长度
        return s.substring(max[0], max[0] + max[1]);
    }

    // 递归中心扩展获取最大回文字符串
    // arr - 原始数组, mid - 起始计算中点下标, direction - 递归方向,区分是向左递归还是向右递归,1向右递归,-1向左递归
    public int[] expandFromCenter(char[] arr, int mid, int direction) {
        // 获取数组长度
        int len = arr.length;

        // 因为调用递归传递的是index-1或者index+1,递归终止条件为<0或者>len-1
        if (mid < 0 || mid > len - 1) {
            // 下标已经无效,返回最大长度为0,起始下标为0,因为字符串至少第一个字符可以构成回文
            return new int[] { 0, 1 };
        }

        int maxBegin = 0; // 最大回文子串的起始下标
        int maxLen = 1; // 最大回文子串的初始长度,因为至少单个字符可以构成一个回文子串,直接初始为1,减少0->1的计算
        int left = mid - 1, right = mid + 1; // 左右扩散的下标初始为中点的左边和右边第一个元素

        // 先算相邻字符相同的情况,如果是偶数回文子串,也必然出现中心相邻两个字符相同,这样可以把偶数情况也处理掉
        while (left >= 0 && arr[left] == arr[mid]) {
            left--;
        }
        while (right < len && arr[right] == arr[mid]) {
            right++;
        }
        // 经过上面的计算,可以得到mid开始左右最大的相同字符子串
        // 暂存当前下标信息,用于递归条件的计算,因为到l和r为止,子串字符是相同的,下一次递归调用不需要再重复计算,可以直接跳过这个区间
        int l = left;
        int r = right;
        // 继续向两侧扩展,计算最大回文子串
        while (left >= 0 && right < len && arr[left] == arr[right]) {
            left--;
            right++;
        }

        maxBegin = left + 1; // 因为在上面循环中,达到中断条件的时候左下标会少1个数,右下标会多1个数,这里暂存最大子串开始下标需要加1
        maxLen = right - left - 1; // 计算最大回文子串,(right - 1) - (left + 1) + 1 = right - left - 1
        // 判断递归方向,用刚才暂存的左下标计算左边剩余长度,如果*2都没有现有最大子串长,就没有必要进行递归
        if (direction <= 0 && (l + 1) * 2 > maxLen) {
            int[] leftExpand = expandFromCenter(arr, l, -1); // 经过上面的循环,l已经是少了1个数的情况,这里直接传l
            // 对递归返回的数据进行对比,记录最长回文子串的信息
            if (maxLen < leftExpand[1]) {
                maxBegin = leftExpand[0];
                maxLen = leftExpand[1];
            }
        }
        // 判断递归方向,用刚才暂存的右下标计算右边剩余长度,如果*2都没有现有最大子串长,就没有必要进行递归
        if (direction >= 0 && (len - r) * 2 > maxLen) {
            int[] rightExpand = expandFromCenter(arr, r, 1);// 经过上面的循环,r已经是多了1个数的情况,这里直接传r
            // 对递归返回的数据进行对比,记录最长回文子串的信息
            if (maxLen < rightExpand[1]) {
                maxBegin = rightExpand[0];
                maxLen = rightExpand[1];
            }
        }
        return new int[] { maxBegin, maxLen };
    }

    // 中心扩散法,效率差一点点
    public String longestPalindrome3(String s) {
        char[] arr = s.toCharArray();
        int arrLen = arr.length;
        if (arrLen == 1) {
            return s;
        }
        int begin = 0;
        int maxLen = 1;
        for (int i = 0; i < arrLen - 1; i++) {
            int[] sub1 = expandAroundCenter(arr, i, i + 1);
            int[] sub2 = expandAroundCenter(arr, i - 1, i + 1);
            int[] subMax = sub1[1] < sub2[1] ? sub2 : sub1;
            if (maxLen < subMax[1]) {
                begin = subMax[0];
                maxLen = subMax[1];
            }
        }
        return s.substring(begin, begin + maxLen);
    }

    // 计算中心扩散最大子串起始下标和最大长度
    public int[] expandAroundCenter(char[] arr, int left, int right) {
        int len = arr.length;
        int subLen = 0;
        while (left >= 0 && right < len) {
            if (arr[left] == arr[right]) {
                subLen = right - left + 1;
                left--;
                right++;
            } else {
                break;
            }
        }
        return subLen > 0 ? new int[] { left + 1, subLen } : new int[] { 0, 1 };
    }

    // 中心扩散法,效率较低
    public String longestPalindrome2(String s) {
        if (s.length() == 1) {
            return s;
        }
        if (s.length() == 2) {
            return s.charAt(0) == s.charAt(1) ? s : String.valueOf(s.charAt(0));
        }
        String max = String.valueOf(s.charAt(0));
        for (int i = 0; i < s.length() - 1; i++) {
            int left = i - 1, right = i + 1;
            while (left >= 0 && right < s.length()) {
                if (s.charAt(left) == s.charAt(right)) {
                    String sub = s.substring(left, right + 1);
                    max = max.length() < sub.length() ? sub : max;
                    left--;
                    right++;
                } else {
                    break;
                }
            }
            left = i;
            right = i + 1;
            while (left >= 0 && right < s.length()) {
                if (s.charAt(left) == s.charAt(right)) {
                    String sub = s.substring(left, right + 1);
                    max = max.length() < sub.length() ? sub : max;
                    left--;
                    right++;
                } else {
                    break;
                }
            }
        }
        return max;
    }

    // 效率极差
    public String longestPalindrome1(String s) {
        int start = 0;
        int[] record = new int[128];
        if (s.length() == 1) {
            return s;
        }
        String max = s.substring(0, 1);
        for (int i = 0; i < s.length(); i++) {
            while (record[s.charAt(i)] != 0 && start < i) {
                String sub = s.substring(start, i + 1);
                if (isPalindrome(sub)) {
                    max = max.length() < sub.length() ? sub : max;
                    break;
                } else {
                    start++;
                }
            }
            start = 0;
            record[s.charAt(i)] = i + 1;
        }
        return max;
    }

    public boolean isPalindrome(String s) {
        int i = 0, j = s.length() - 1;
        while (i < j) {
            if (s.charAt(i++) != s.charAt(j--)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "";
        s = "adam";
        System.out.println(new LongestPalindrome().longestPalindrome(s));
        s = "babad";
        System.out.println(new LongestPalindrome().longestPalindrome(s));
        s = "cbbd";
        System.out.println(new LongestPalindrome().longestPalindrome(s));
        s = "a";
        System.out.println(new LongestPalindrome().longestPalindrome(s));
        s = "ab";
        System.out.println(new LongestPalindrome().longestPalindrome(s));
        s = "aa";
        System.out.println(new LongestPalindrome().longestPalindrome(s));
        s = "ccc";
        System.out.println(new LongestPalindrome().longestPalindrome(s));
        s = "abcba";
        System.out.println(new LongestPalindrome().longestPalindrome(s));
        s = "tattarrattat";
        System.out.println(new LongestPalindrome().longestPalindrome(s));
    }
}
