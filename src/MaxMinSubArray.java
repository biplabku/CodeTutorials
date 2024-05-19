import java.util.HashMap;
import java.util.HashSet;
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

    public String longestSubstring(String str, int k){
        if(str.isEmpty()) {
            return "";
        }
        String res = "";
        int max = Integer.MIN_VALUE;
        int left = 0;
        HashMap<Character, Integer> hmap = new HashMap<>();
        for(int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            hmap.put(ch, hmap.getOrDefault(ch, 0) + 1);
            while(hmap.size() > k) {
                hmap.put(str.charAt(left), hmap.get(str.charAt(left)) -1 );
                if(hmap.get(str.charAt(left)) == 0) {
                    hmap.remove(str.charAt(left));
                }
                left++;
            }
            if(i - left + 1 > max) {
                res = str.substring(left, i + 1);
                max = i - left + 1;
            }
        }
        return res;
    }


    public static void main(String[] args) {
        MaxMinSubArray ms = new MaxMinSubArray();
        int[] nums = new int[]{17,6,19, 3};
        String str = "abcbdbdbbdcdabd";
        System.out.println(ms.longestSubstring(str, 4));
    }

}
