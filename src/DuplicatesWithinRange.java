import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeMap;

public class DuplicatesWithinRange {

    // 5. Find Duplicates Within a Range ‘K’ in an Array
    // [5,6,8,2,4,6,9], k = 2
    public boolean getDuplicates(int[] nums, int k){
        TreeMap<Integer, Integer> hmap = new TreeMap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });
        int left = 0;
        for(int i = 0; i < nums.length; i++) {
            int val = nums[i];
            hmap.put(val, hmap.getOrDefault(val, 0) + 1);
            int size = i - left + 1;
            if(size == k) {
                int key = hmap.firstKey();
                if(hmap.get(key) > 1) {
                    return true;
                }
                hmap.remove(nums[left]);
                left++;
            }
        }
        return false;
    }

    // Given an array of positive integers nums and a positive integer target, return the minimal length of a
    // subarray whose sum is greater than or equal to target. If there is no such subarray, return 0 instead.
    // [2,3,1,2,4,3]
    // target = 7
    public int minSubArraySum(int[] nums, int target) {
        int sum = 0;
        int left = 0;
        int len = Integer.MAX_VALUE;
        for(int i = 0; i < nums.length; i++) {
            int val = nums[i];
            sum += val;
            while(sum >= target) {
                len = Math.min(len, i - left + 1);
                sum = sum - nums[left];
                left++;
            }
        }
        return len == Integer.MAX_VALUE? 0:len;
    }

    public static void main(String[] args) {
        DuplicatesWithinRange ds = new DuplicatesWithinRange();
        int[] nums = new int[]{1,4,4};
        int k = 4;
        System.out.println(ds.minSubArraySum(nums, k));
    }
}
