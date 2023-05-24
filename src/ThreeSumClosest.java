import java.util.Arrays;

/**
 * 最接近的三数之和
 * 给你一个长度为 n 的整数数组 nums 和 一个目标值 target。请你从 nums 中选出三个整数，使它们的和与 target 最接近。
 * 返回这三个数的和。
 * 假定每组输入只存在恰好一个解。
 * 题目链接: https://leetcode.cn/problems/3sum-closest/
 */
public class ThreeSumClosest {
    // 优化版本,打败100%,考虑各种边界条件
    public int threeSumClosest(int[] nums, int target) {
        int n = nums.length;
        // 对数组进行排序,方便计算
        Arrays.sort(nums);
        // 初始的接近数为前3个数
        int result = nums[0] + nums[1] + nums[2];
        // 如果初始值已经比目标值大,移动坐标只会越来越远离目标值,这里直接返回结果
        if (result > target) {
            return result;
        }
        // 用最后3个数计算总和
        int sum = nums[n - 3] + nums[n - 2] + nums[n - 1];
        // 如果最后3个数总和都小于等于目标值,移动坐标只会越来越小,越来越远离目标值,这里直接返回结果
        if (sum <= target) {
            return sum;
        }
        // 循环遍历
        for (int l = 0; l < n - 2; l++) {
            // 如果当前的左坐标值和上一次遍历的值一样,直接跳过处理
            if (l > 0 && nums[l] == nums[l - 1]) {
                continue;
            }
            // 中坐标取坐左坐标的下一个数,右坐标取最后一个数
            int m = l + 1, r = n - 1;
            // 如果左坐标的数和最后的两个数之和都小于目标值,移动中坐标和右坐标总和会越来越小,越来越远离目标,这次循环直接以这个总和为最优解
            sum = nums[l] + nums[n - 2] + nums[n - 1];
            if (sum < target) {
                result = sum;
                continue;
            }
            while (m < r) {
                // 如果上一个中坐标的值与当前中坐标值相等,则跳过
                if (m > l + 1 && nums[m] == nums[m + 1]) {
                    m++;
                    continue;
                }
                // 计算总和
                sum = nums[l] + nums[m] + nums[r];
                // 如果总和等于目标值,直接返回
                if (sum == target) {
                    return sum;
                }
                // 如果当前总和的绝对值已有最佳答案的绝对值,表示更接近目标,更新最佳答案
                if (Math.abs(sum - target) < Math.abs(result - target)) {
                    result = sum;
                }
                if (sum < target) {
                    // 如果当前总和小于目标值,则右移中坐标,看是是否会更靠近目标
                    m++;
                } else {
                    // 如果当前总和大于目标值,则左移右坐标,看是是否会更靠近目标
                    r--;
                }
            }
        }
        return result;
    }
    // 效率一般
    public int threeSumClosest2(int[] nums, int target) {
        int n = nums.length;
        Arrays.sort(nums);
        int result = nums[0] + nums[1] + nums[2];
        for (int l = 0; l < n - 2; l++) {
            if (l > 0 && nums[l] == nums[l - 1]) {
                continue;
            }
            int m = l + 1, r = n - 1;
            while (m < r) {
                if (m > l + 1 && nums[m] == nums[m + 1]) {
                    m++;
                    continue;
                }
                int sum = nums[l] + nums[m] + nums[r];
                if (sum - target == 0) {
                    return sum;
                }
                if (Math.abs(sum - target) < Math.abs(result - target)) {
                    result = sum;
                }
                if (sum < target) {
                    m++;
                } else {
                    r--;
                }
            }
        }
        return result;
    }
    // 效率太低
    public int threeSumClosest1(int[] nums, int target) {
        int n = nums.length;
        Arrays.sort(nums);
        int result = nums[0] + nums[1] + nums[2];
        for (int l = 0; l < n - 2; l++) {
            for (int m = l + 1; m < n - 1; m++) {
                for (int r = m + 1; r < n; r++) {
                    int sum = nums[l] + nums[m] + nums[r];
                    if (m >= r) {
                        break;
                    }
                    if (sum - target == 0) {
                        result = sum;
                        break;
                    }
                    if (Math.abs(sum - target) < Math.abs(result - target)) {
                        result = sum;
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums;
        int target;
        nums = new int[]{-1, 2, 1, -4};
        target = 1;
        System.out.println(new ThreeSumClosest().threeSumClosest(nums, target));
        nums = new int[]{4, 0, 5, -5, 3, 3, 0, -4, -5};
        target = -2;
        System.out.println(new ThreeSumClosest().threeSumClosest(nums, target));
        nums = new int[]{321, 413, 82, 812, -646, -858, 729, 609, -339, 483, -323, -399, -82, -455, 18, 661, 890, -328, -311, 520, -865, -174, 55, 685, -636, 462, -172, -696, -296, -832, 766, -808, -763, 853, 482, 411, 703, 655, -793, -121, -726, 105, -966, -471, 612, 551, -257, 836, -94, -213, 511, 317, -293, 279, -571, 242, -519, 386, -670, -806, -612, -433, -481, 794, 712, 378, -325, -564, 477, 169, 601, 971, -300, -431, -152, 285, -899, 978, -419, 708, 536, -816, -335, 284, 384, -922, -941, 633, 934, 497, -351, 62, 392, -493, -44, -400, 646, -912, -864, 835, 713, -12, 322, -228, 340, -42, -307, -580, -802, -914, -142, 575, -684, -415, 718, -579, 759, 579, 732, -645, 525, 114, -880, -603, -699, -101, -738, -887, 327, 192, 747, -614, 393, 97, -569, 160, 782, -69, 235, -598, -116, 928, -805, -76, -521, 671, 417, 600, -442, 236, 831, 637, -562, 613, -705, -158, -237, -299, 808, -734, 364, 919, 251, -163, -343, 899};
        target = 2218;
        System.out.println(new ThreeSumClosest().threeSumClosest(nums, target));
        nums = new int[]{0, 0, 0};
        target = 0;
        System.out.println(new ThreeSumClosest().threeSumClosest(nums, target));
    }
}
