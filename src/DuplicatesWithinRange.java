import java.util.*;

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

    // Given an array of positive integers nums and a positive integer target,
    // return the minimal length of a
    // subarray whose sum is greater than or equal to target. If there is no such
    // subarray, return 0 instead.
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

    public int lengthOfLongestSubstring(String str) {
        if(str.length() == 0) {
            return 0;
        }
        HashMap<Character, Integer> hmap = new HashMap<>();
        int result = 0;
        int left = 0;
        int right;
        for(right = 0; right < str.length(); right++) {
            char ch = str.charAt(right);
            hmap.put(ch, hmap.getOrDefault(ch, 0) + 1);
            if(hmap.get(ch) > 1) {
                result = Math.max(result, right - left + 1);
                int value = hmap.get(ch);
                value--;
                if(value == 0) {
                    hmap.remove(ch);
                }else{
                    hmap.put(ch, value);
                }
                left++;
            }
        }
        return result;
    }

    // Input: s = "ADOBECODEBANC", t = "ABC"
    // BANC
    // Input: s = "a", t = "a"
    public String minWindowSubstring(String str, String pattern){
        if(pattern.length() > str.length()) {
            return "";
        }
        HashMap<Character, Integer> mapString = new HashMap<>();
        HashMap<Character, Integer> mapPattern = new HashMap<>();
        for(int i = 0; i < pattern.length(); i++) {
            char ch = pattern.charAt(i);
            mapPattern.put(ch, mapPattern.getOrDefault(ch, 0) + 1);
        }
        int requiredSize = mapPattern.size();

        int left = 0;
        int right = 0;
        int formed = 0;
        int[] answ = new int[]{-1, 0, 0};
        while(right < str.length()) {
            char ch = str.charAt(right);
            int count = mapString.getOrDefault(ch, 0);
            mapString.put(ch, count + 1);

            if(mapPattern.containsKey(ch) && mapString.get(ch).intValue() == mapPattern.get(ch).intValue()) {
                formed++;
            }

            while(left <= right && formed == requiredSize) {
                ch = str.charAt(left);
                if(answ[0] == -1 || right - left + 1 < answ[0]) {
                    answ[0] = right - left + 1;
                    answ[1] = left;
                    answ[2] = right;
                }

                mapString.put(ch, mapString.get(ch) - 1);
                if(mapPattern.containsKey(ch) && mapString.get(ch).intValue() < mapPattern.get(ch).intValue()) {
                    formed--;
                }
                left++;
            }
            right++;
        }
        return answ[0] == -1? "": str.substring(answ[1], answ[2] + 1);
    }

    public boolean checkIfExists(HashMap<Character, Integer> str, HashMap<Character, Integer> pattern) {
        Iterator iter = pattern.entrySet().iterator();
        while(iter.hasNext()) {
            Map.Entry pair = (Map.Entry) iter.next();
            char key = (char)pair.getKey();
            int value = (int)pair.getValue();
            if(str.containsKey(key) && str.get(key) >= value) {
                continue;
            }else{
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        DuplicatesWithinRange ds = new DuplicatesWithinRange();
        int[] nums = new int[]{1,4,4};
        int k = 4;
        String str = "ADOBECODEBANC";
        String pattern = "ABC";

    }
}
