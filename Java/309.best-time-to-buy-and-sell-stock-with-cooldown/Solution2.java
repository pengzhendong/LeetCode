/*
 * @lc app=leetcode.cn id=309 lang=java
 *
 * [309] 最佳买卖股票时机含冷冻期
 *
 * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/description/
 *
 * algorithms
 * Medium (45.56%)
 * Total Accepted:    2.8K
 * Total Submissions: 6.1K
 * Testcase Example:  '[1,2,3,0,2]'
 *
 * 给定一个整数数组，其中第 i 个元素代表了第 i 天的股票价格 。​
 * 
 * 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
 * 
 * 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
 * 
 * 示例:
 * 
 * 输入: [1,2,3,0,2]
 * 输出: 3 
 * 解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]
 */
class Solution {
    public int maxProfit(int[] prices) {
        int rest = 0;
        int hold = Integer.MIN_VALUE;
        int sold = 0;

        for (int price : prices) {
            int pre_sold = sold;
            rest = Math.max(rest, pre_sold);
            hold = Math.max(hold, rest - price);
            sold = hold + price;
        }
        return Math.max(sold, rest);
    }
}
