import java.util.*;

public class SubStringProblems {

    public int longestValidSubstring(String word, List<String> forbidden) {
        int len = 0;
        for(int i = 0; i < forbidden.size(); i++) {
            len = Math.max(len, forbidden.get(i).length());
        }
        int left = 0;
        int right;
        int max = 0;
        for(right = 0; right < word.length(); right++) {
            for(int k = right; k >= Math.max(right - len, left); k--) {
                if(forbidden.contains(word.substring(k, right + 1))) {
                    left = k + 1;
                    break;
                }
            }
            max = Math.max(right - left + 1, max);
        }

        return max;
    }

    public void getAllSubstringsEff(String word, List<String> result) {
        for(int i = 0; i < word.length(); i++) {
            for(int j = i; j < word.length(); j++) {
                StringBuilder sb = new StringBuilder();
                for(int k = i; k <= j; k++) {
                    sb.append(word.charAt(k));
                }
                if(!result.contains(sb.toString())){
                    result.add(sb.toString());
                }
            }
        }
    }

    public int[] platestBetweebCandles(String str, int[][] queries) {
        for(int[] arr : queries) {

        }
        return new int[0];
    }



    public int checkPlates(int x, int y, String str) {
        int left = x;
        int right = y;
        while(left < right) {
            char chLeft = str.charAt(left);
            while(chLeft != '|') {
                
            }


        }
        return 1;
    }

    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        if(nums[0] < nums[right]) {
            return nums[0];
        }
        while(left < right) {
            int midIndex = left + (right - left)/2;
            int midValue = nums[midIndex];
            if(midValue > nums[midIndex + 1]) {
                return nums[midIndex + 1];
            }
            if(midValue < nums[midIndex - 1]) {
                return midValue;
            }
            if(midValue > nums[left]) {
                left = midIndex + 1;
            }else {
                right = midIndex - 1;
            }
        }
        return nums[left];
    }

    class intCount{
        int value;
        int count;
        public intCount(int value, int count) {
            this.value = value;
            this.count = count;
        }
    }
    public int minOperations(int[] nums) {
        if(nums.length == 0) {
            return 0;
        }
        HashMap<Integer, Integer> hmap = new HashMap<>();
        for(int i: nums) {
            hmap.put(i, hmap.getOrDefault(i, 0) + 1);
        }
        PriorityQueue<intCount> queue = new PriorityQueue<>(new Comparator<intCount>() {
            @Override
            public int compare(intCount o1, intCount o2) {
                return o2.count - o1.count;
            }
        });
        Iterator iter = hmap.entrySet().iterator();
        while(iter.hasNext()) {
            Map.Entry pair = (Map.Entry) iter.next();
            int value = (int)pair.getKey();
            int count = (int)pair.getValue();
            queue.add(new intCount(value, count));
        }
        int result = 0;
        while(!queue.isEmpty()) {
            intCount c = queue.poll();
            if(c.count == 1) {
                return -1;
            }
            result += c.count/3;
            if(c.count%3 != 0) {
                result++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        SubStringProblems ss = new SubStringProblems();
        String str = "leetcode";
        List<String> list = new ArrayList<>();
        list.add("de");
        list.add("le");
        list.add("e");
        int[] nums = new int[]{19,19,19,19,19,19,19,19,19,19,19,19,19};
        System.out.println(ss.minOperations(nums));
    }
}
