/**
 * 寻找两个正序数组的中位数
 * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
 * 算法的时间复杂度应该为 O(log (m+n)) 。
 * 题目链接: https://leetcode.cn/problems/median-of-two-sorted-arrays/
 */
public class FindMedianSortedArrays {
    // 打败100%,采用二分查找法
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // 如果nums1长度大于nums2则交换,保证第一个数组长度小于第二个数组,这样第一个数组不管怎样切,第二个数组切割后右边都有数
        // 例如{1,3}和{2},如果不交换,第一步就会出错,找不到下标j的元素
        if (nums1.length > nums2.length) {
            int[] tmp = nums1;
            nums1 = nums2;
            nums2 = tmp;
        }

        int len1 = nums1.length;
        int len2 = nums2.length;
        // 计算是否奇数,采用不会溢出的计算方法
        boolean odd = ((len1 % 2) + (len2 % 2)) % 2 != 0 ? true : false;

        int i = 0, j = 0; // i和j代表两个数组切割位置,切割线左边元素下标为i-1,右边元素下标为i+1
        int halfLen = len1 + (len2 - len1 + 1) / 2; // 计算一半的长度,考虑到奇数,一律+1,这样奇数就落在左半边
        int iMin = 0, iMax = len1; // 需要二分查找的范围

        while (iMin <= iMax) {
            i = iMin + (iMax - iMin + 1) / 2; // 计算第一个数组的切割线位置,同样+1处理奇数情况
            j = halfLen - i; // 一半长度减去第一个数组的个数,就是第二个数组需要包含在左半边的数量
            if (i > 0 && nums1[i - 1] > nums2[j]) {
                iMax = i - 1; // 第一个数组的左半边最大值比第二个数组的右半边最小值还大,不符合有序,缩小搜索范围
            } else if (i < len1 && nums1[i] < nums2[j - 1]) {
                iMin = i + 1; // 第一个数组的右半边最小值比第二个数组的左半边最大值还小,不符合有序,扩大搜索范围
            } else {
                // 符合有序并且正确切割的情况
                // 处理溢出
                // 切割线在0位置,左边没有元素,给个最小值避免被选取
                int nums1LeftMax = i == 0 ? Integer.MIN_VALUE : nums1[i - 1]; 
                // 切割线在最大长度位置,右边没有元素,给个最大值避免被选取
                int nums1RightMin = i == len1 ? Integer.MAX_VALUE : nums1[i];
                // 切割线在0位置,左边没有元素,给个最小值避免被选取
                int nums2LeftMax = j == 0 ? Integer.MIN_VALUE : nums2[j - 1];
                // 切割线在最大长度位置,右边没有元素,给个最大值避免被选取
                int nums2RightMin = j == len2 ? Integer.MAX_VALUE : nums2[j];
                // 如果是奇数,就返回两个数组中左半边最大的值
                // 如果是偶数,就返回(两个数组中左半边最大的值+两个数组中右边最小的值)/2
                return odd ? Math.max(nums1LeftMax, nums2LeftMax)
                        : (Math.max(nums1LeftMax, nums2LeftMax) + Math.min(nums1RightMin, nums2RightMin)) / 2.0;
            }
        }
        return 0;
    }

    // 效率低,不过在合并数组方法中算效率高的
    public double findMedianSortedArrays1(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        int maxLen = len1 + len2;
        int[] arr = new int[maxLen];
        for (int i = 0, j = 0, k = 0; k < maxLen; k++) {
            if (i < len1 && j < len2) {
                if (nums1[i] <= nums2[j]) {
                    arr[k] = nums1[i++];
                } else {
                    arr[k] = nums2[j++];
                }
            } else if (i < len1) {
                arr[k] = nums1[i++];
            } else if (j < len2) {
                arr[k] = nums2[j++];
            }
        }

        if (maxLen % 2 == 0) {
            return ((arr[maxLen / 2 - 1]) + arr[maxLen / 2]) / 2.0;
        } else {
            return arr[maxLen / 2];
        }
    }

    public static void main(String[] args) {
        int[] nums1 = { 1, 3 }, nums2 = { 2 };
        System.out.println(new FindMedianSortedArrays().findMedianSortedArrays(nums1, nums2));
        int[] nums11 = { 1, 2 }, nums22 = { 3, 4 };
        System.out.println(new FindMedianSortedArrays().findMedianSortedArrays(nums11, nums22));
        int[] nums111 = { 0, 0 }, nums222 = { 0, 0 };
        System.out.println(new FindMedianSortedArrays().findMedianSortedArrays(nums111, nums222));
        int[] nums1111 = { 1 }, nums2222 = { 2, 3 };
        System.out.println(new FindMedianSortedArrays().findMedianSortedArrays(nums1111, nums2222));
        int[] nums11111 = { 1 }, nums22222 = { 1 };
        System.out.println(new FindMedianSortedArrays().findMedianSortedArrays(nums11111, nums22222));
        int[] nums111111 = { 1 }, nums222222 = { 2, 3, 4 };
        System.out.println(new FindMedianSortedArrays().findMedianSortedArrays(nums111111, nums222222));
        int[] nums1111111 = { 1, 2, 3, 6, 7 }, nums2222222 = { 4, 5, 8, 9, 10 };
        System.out.println(new FindMedianSortedArrays().findMedianSortedArrays(nums1111111, nums2222222));
        int[] nums11111111 = { 3 }, nums22222222 = { -2, -1 };
        System.out.println(new FindMedianSortedArrays().findMedianSortedArrays(nums11111111, nums22222222));
    }
}
