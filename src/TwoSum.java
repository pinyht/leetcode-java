import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 两数之和
 * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target 的那 两个 整数，并返回它们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 * 你可以按任意顺序返回答案。
 * 题目链接: https://leetcode.cn/problems/two-sum/
 */
public class TwoSum {
    // 打败100%
    public int[] twoSum(int[] nums, int target) {
        // 用一个map记录遍历过的数,用于反查,key为数字,value为下标
        Map<Integer, Integer> record = new HashMap<>(nums.length);
        // 进行双向循环,i从左往右,j从又往左,因为如果有答案,且两个数不重复出现,那么当两个方向循环到相会时,map中一定有对应的另外一个值
        // 这样可以省下一半的循环量
        int i = 0;
        int j = nums.length - 1;
        while (i <= j) {
            // 从左往右的情况
            if (record.containsKey(target - nums[i])) {
                return new int[] { i, record.get(target - nums[i]) };
            }
            record.put(nums[i], i);

            // 从右往左的情况,当i==j的时候,上面的从左往右循环已经遍历完所有元素,下面部分没必要再执行
            if (i != j) {
                if (record.containsKey(target - nums[j])) {
                    return new int[] { j, record.get(target - nums[j]) };
                }
                record.put(nums[j], j);
                i++;
                j--;
            }
        }
        return new int[] { -1, -1 };
    }

    /*
    // 效率偏低版本
    public int[] twoSum(int[] nums, int target) {
        // 用一个map记录遍历过的数,用于反查,key为数字,value为下标
        Map<Integer, Integer> record = new HashMap<>(nums.length);
        int len = nums.length;
        record.put(nums[0], 0);
        for (int i = 1; i < len; i++) {
            if (record.containsKey(target - nums[i])) {
                return new int[] { i, record.get(target - nums[i]) };
            }
            record.put(nums[i], i);
        }
        return new int[] { -1, -1 };
    }
    */

    public static void main(String[] args) {
        int[] nums = { 2, 7, 11, 15 };
        int target = 9;
        System.out.println(Arrays.toString(new TwoSum().twoSum(nums, target)));
    }
}
