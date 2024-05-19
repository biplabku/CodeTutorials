import java.util.LinkedList;
import java.util.Queue;

public class MaxMinSubArray {


    public double maxMinSubarrayDiff(int[] nums, int k){
        int leftBound = 0;
        double minSum = Integer.MAX_VALUE;
        double maxSum = Integer.MIN_VALUE;
        int currentSum = 0;
        for(int i = 0; i < nums.length; i++) {
            currentSum += nums[i];
            if(i - leftBound + 1 == k) {
                minSum = Math.min(minSum, (double)currentSum/k);
                maxSum = Math.max(maxSum, (double)currentSum/k);
                currentSum -= nums[leftBound];
                leftBound++;
            }
        }
        return (double) maxSum - minSum;
    }
    
    public static void main(String[] args) {
        MaxMinSubArray ms = new MaxMinSubArray();
        int[] nums = new int[]{17,6,19, 3};
        System.out.println(ms.maxMinSubarrayDiff(nums, 3));
    }

}
