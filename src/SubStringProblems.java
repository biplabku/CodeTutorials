import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

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

    public static void main(String[] args) {
        SubStringProblems ss = new SubStringProblems();
        String str = "leetcode";
        List<String> list = new ArrayList<>();
        list.add("de");
        list.add("le");
        list.add("e");
        int[] nums = new int[]{0,1,2,4,5,6,7};
        System.out.println(ss.findMin(nums));
    }
}
