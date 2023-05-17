/**
 * 盛最多水的容器
 * 给定一个长度为 n 的整数数组height。有n条垂线，第 i 条线的两个端点是(i, 0)和(i, height[i])。
 * 找出其中的两条线，使得它们与x轴共同构成的容器可以容纳最多的水。
 * 返回容器可以储存的最大水量。
 * 说明：你不能倾斜容器。
 * 题目链接: https://leetcode.cn/problems/container-with-most-water/
 */
public class MaxArea {
    // 效率高,打败100%
    public int maxArea(int[] height) {
        // 容量实际上就是x和y轴区域的面积
        // 初始化变量,max记录当前最大的容量,l和r代表左边和右边的下标,采用双指针向中间收拢计算
        int max = 0, l = 0, r = height.length - 1;
        while (l < r) {
            // 获取两头较小的高度,容量需要以较小的高度为参考,比如左边是5右边是3,容量也只能按照两个3来算
            int minHeight = Math.min(height[l], height[r]);
            // 计算临时的容量
            int tmpMax = minHeight * (r - l);
            // 如果超过当前最大容量则更新最大容量值
            max = Math.max(tmpMax, max);
            // 当左边的边长小于或等于小的那边高度,由于r-l的值越来越小,面积只会越来越小,直接跳过,然后用下一个更高的边与当前高的那一边去计算容量
            while (height[l] <= minHeight && l < r) {
                l++;
            }
            // 当右边的边长小于或等于小的那边高度,由于r-l的值越来越小,面积只会越来越小,直接跳过,然后用下一个更高的边与当前高的那一边去计算容量
            while (height[r] <= minHeight && l < r) {
                r--;
            }
        }
        return max;
    }

    // 效率太低,执行超时
    public int maxArea1(int[] height) {
        int max = 0;
        int curHeight = 0;
        for (int i = 0; i < height.length; i++) {
            curHeight = height[i];
            for (int j = i; j < height.length; j++) {
                int tmpMax = curHeight <= height[j] ? curHeight * (j - i) : height[j] * (j - i);
                max = Math.max(tmpMax, max);
            }
        }

        return max;
    }

    public static void main(String[] args) {
        int[] height;
        height = new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println(new MaxArea().maxArea(height));
        height = new int[]{1, 1};
        System.out.println(new MaxArea().maxArea(height));
    }
}
