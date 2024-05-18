import java.util.*;

public class SlidingWindow {

    private PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    private PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

    public double[] medianSlidingWindow(int[] nums, int k) {
        int n = nums.length - k + 1;
        if (n <= 0) {
            return new double[0];
        }
        double[] result = new double[n];
        for (int i = 0; i <= nums.length; i++) {

        }
        return result;
    }

    public void add(int num){
        double median = getMedian();
        if(num > median) {
            minHeap.add(num);
        }else{
            maxHeap.add(num);
        }
        rebalance();
    }

    public void remove(int num){
        double median = getMedian();
        if(num > median) {
            minHeap.remove(num);
        }else {
            maxHeap.remove(num);
        }
        rebalance();
    }

    public void rebalance(){
        if(minHeap.size() > maxHeap.size()) {
            maxHeap.add(minHeap.poll());
        }else if(maxHeap.size() > minHeap.size() + 1){
            minHeap.add(maxHeap.poll());
        }
    }

    public double getMedian() {
        if(maxHeap.isEmpty() && minHeap.isEmpty()) {
            return 0;
        }
        if(maxHeap.size() == minHeap.size()) {
            return (double) (maxHeap.peek() + minHeap.peek())/2;
        }else {
            return (double) maxHeap.peek();
        }
    }


    Queue<Integer> minHeap1 = new PriorityQueue<>();
    Queue<Integer> maxHeap1 = new PriorityQueue<>(Collections.reverseOrder());

    public double[] medianSlidingWindowFast(int[] nums, int k) {
        double[] medians = new double[nums.length - k + 1];
        for (int i = 0; i < nums.length; i++) {
            if (maxHeap1.isEmpty() || nums[i] <= maxHeap1.peek()) {
                maxHeap1.offer(nums[i]);
            } else {
                minHeap1.offer(nums[i]);
            }

            rebalanceHeaps();

            if (i - k + 1 >= 0) {
                if (maxHeap.size() == minHeap1.size()) {
                    medians[i - k + 1] = ((double) maxHeap1.peek() + minHeap1.peek()) / 2.0;
                } else {
                    medians[i - k + 1] = (double) maxHeap1.peek();
                }

                int toBeRemoved = nums[i - k + 1];
                if (toBeRemoved <= maxHeap1.peek()) {
                    maxHeap1.remove(toBeRemoved);
                } else {
                    minHeap1.remove(toBeRemoved);
                }

                rebalanceHeaps();
            }
        }
        return medians;
    }

    private void rebalanceHeaps() {
        while (maxHeap1.size() > minHeap1.size() + 1) {
            minHeap1.offer(maxHeap1.poll());
        }
        while (minHeap1.size() > maxHeap1.size()) {
            maxHeap1.offer(minHeap1.poll());
        }
    }

    public static boolean canCross(int[] stones) {
        HashMap<Integer , HashSet<Integer>> map = new HashMap<>();
        int n = stones.length;
        for(int ele : stones)
        {
            map.put(ele,new HashSet<>());
        }

        map.get(stones[0]).add(1);

        for(int i = 0;i < n - 1; i++) {
            int num = stones[i];
            HashSet<Integer> jumps = map.get(num);

            for(int prevJumps :jumps)
            {
                int possibleJump = num + prevJumps;
                if(possibleJump == stones[n-1]){
                    return true;
                }
                if(map.containsKey(possibleJump))
                {
                    if(prevJumps -1>0) {
                        map.get(possibleJump).add(prevJumps - 1);
                    }
                    map.get(possibleJump).add(prevJumps);
                    map.get(possibleJump).add(prevJumps +1);
                }
            }
        }
        return false;
    }

    public static List<List<Integer>> fourSumProblem(int[] nums, int target){
        if(nums.length == 0) {
            return new ArrayList<>();
        }
        Arrays.sort(nums);
        List<List<Integer>> list = new ArrayList<>();
        for(int i = 0; i < nums.length; i++) {
            int val1 = nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                int val2 = nums[j];

                List<Integer> temp = new ArrayList<>();
                temp.add(val1);
                temp.add(val2);
                if (!list.contains(temp)) {
                    list.add(temp);
                }
            }
        }
        List<List<Integer>> result = new ArrayList<>();
        for(int i = 0; i < list.size(); i++) {
            for(int j = i + 1; j < list.size(); j++) {
                List<Integer> t2 = list.get(j);
                List<Integer> t1 = list.get(i);
                int sum = t1.get(0) + t1.get(1) + t2.get(0) + t2.get(1);
                if(sum == 0) {
                    List<Integer> t = new ArrayList<>();
                    t.add(t1.get(0));
                    t.add(t1.get(1));
                    t.add(t2.get(0));
                    t.add(t2.get(1));
                    Collections.sort(t);
                    if(!result.contains(t)) {
                        result.add(new ArrayList<>(t));
                    }
                }
            }
        }
        return new ArrayList<>();
    }

    public static void addAllList(List<List<Integer>> l1, List<List<Integer>> l2, List<List<Integer>> res){
        for(List<Integer> t1 : l1) {
            for(List<Integer> t2 : l2) {
                List<Integer> t = new ArrayList<>();
                t.addAll(t1);
                t.addAll(t2);
                Collections.sort(t);
                if(!res.contains(t)) {
                    res.add(new ArrayList<>(t));
                }
            }
        }
    }

    class Student{
        int studentId;
        String name;

        public Student(int studentId, String name){
            this.studentId = studentId;
            this.name = name;
        }
    }

    public void sortStudentById(List<Student> listofStudents){
        // natural ordering of Priority queue is in ascending order.
        PriorityQueue<Student> minHeap = new PriorityQueue<>(new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.studentId - o2.studentId;
            }
        });

        // reverse the order
        PriorityQueue<Student> maxHeap = new PriorityQueue<>(new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o2.studentId - o1.studentId;
            }
        });
        /// max heap can be further simplified
        PriorityQueue<Student> maxHeapSimplified = new PriorityQueue<>(Collections.reverseOrder());

    }

    // 3,2,1,5,6,4
    public int findKthLargestElement(int[] nums, int k){
        if(nums.length == 0) {
            return -1;
        }
        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<>(){
            @Override
            public int compare(Integer a, Integer b){
                return b.compareTo(a);
            }
        });
        for(int i = 0; i < nums.length; i++) {
            queue.offer(nums[i]);
        }
        int val = queue.peek();
        while(!queue.isEmpty() && k > 0) {
            val = queue.poll();
            k--;
        }
        return k > 0? -1:val;
    }

    // 3,2,1,5,6,4
    public int findKthLargest(int[] nums, int k){
        if(nums.length == 0) {
            return -1;
        }
        int max = 0;
        for (int num : nums) {
            max = Math.max(num, max);
        }
        int[] arr = new int[40];
        for(int i = 0; i < nums.length; i++) {
            arr[nums[i]]++;
        }
        boolean first =false;
        if(k == 0) {
            first = true;
        }
        for(int i = 8; i >= 0; i--) {
            if(first && arr[i] == 1) {
                return i;
            }
            while(arr[i] >= 1 && k > 0) {
                arr[i]--;
                k--;
            }
            if(k == 0) {
                return i;
            }

        }
        return -1;
    }

    // quick select algorithm
    // to find the kth smallest element in an unordered list
    // On average O(n) run time.
    public int findKthLargestQuickSelect(int[] nums, int k) {
        List<Integer> list = new ArrayList<>();
        for(int num : nums) {
            list.add(num);
        }
        return quickSelect(list, k);
    }

    public int quickSelect(List<Integer> list, int k){
        int pivotIndex = new Random().nextInt(list.size());
        int pivot = list.get(pivotIndex);
        List<Integer> left = new ArrayList<>();
        List<Integer> mid = new ArrayList<>();
        List<Integer> right = new ArrayList<>();

        for(int num: list){
            if(num > pivot) {
                left.add(num);
            }else if(num == pivot) {
                mid.add(num);
            }else{
                right.add(num);
            }
        }
        if(k <= left.size()) {
            return quickSelect(left, k);
        }

        if(left.size() + mid.size() < k) {
            return quickSelect(right, k - left.size() - mid.size());
        }
        return pivot;
    }

    class profitPoint{
        int profit;
        int buyTime;
        int sellTime;

        public profitPoint(int profit, int buyTime, int sellTime) {
            this.profit = profit;
            this.buyTime = buyTime;
            this.sellTime = sellTime;
        }
    }

    public int maxProfit(int[] prices) {
        PriorityQueue<profitPoint> queue = new PriorityQueue<>(new Comparator<profitPoint>() {
            @Override
            public int compare(profitPoint o1, profitPoint o2) {
                return o2.profit - o1.profit;
            }
        });
        for(int i = 0; i < prices.length; i++) {
            for(int j = i + 1; j < prices.length; j++) {
                int profit = prices[j] - prices[i];
                if(profit > 0) {
                    profitPoint  p = new profitPoint(profit, i, j);
                    queue.add(p);
                }
            }
        }
        profitPoint p1 = queue.poll();
        int result = p1.profit;
        while(!queue.isEmpty()) {
            profitPoint temp = queue.poll();
            if(p1.buyTime > temp.sellTime) {
                result = p1.profit + temp.profit;
            }
            p1 = temp;
        }
        return result;
    }

    public int stockProfitOne(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int result = 0;
        for(int i = 0; i < prices.length; i++) {
            int currentprice = prices[i];
            if(minPrice > currentprice) {
                minPrice = currentprice;
            }else if(currentprice - minPrice > result) {
                result = Math.max(result, currentprice - minPrice);
            }
        }
        return result;
    }

    public int stockProfitTwo(int[] prices){
        int globalMinPrice = Integer.MAX_VALUE;
        int result = 0;
        for(int i = 0; i < prices.length; i++) {
            int currentMinPrice = prices[i];
            if(globalMinPrice > currentMinPrice) {
                globalMinPrice = currentMinPrice;
            }
            if(currentMinPrice - globalMinPrice > 0){
                result = result + (currentMinPrice - globalMinPrice);
                globalMinPrice = currentMinPrice;
            }
        }
        return result;
    }

    public int maxProfitThree(int[] array) {
        if(array.length == 0 || array.length == 1) {
            return 0;
        }
        int minPrice1 = Integer.MAX_VALUE;
        int minPrice2 = Integer.MAX_VALUE;
        int profit1 = 0;
        for(int i = 0; i < array.length; i++) {
            int price = array[i];
            if(minPrice1 > price) {
                minPrice1 = price;
            }else if(price > minPrice1) {
                profit1 = Math.max(profit1, price - minPrice1);
            }
        }
        return 1;
    }

    public int getMaxSpecialCount(int[] array1, int[] array2) {
        PriorityQueue<Integer> max1 = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });
        PriorityQueue<Integer> max2 = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });
        for(int i = 0; i < array1.length; i++) {
            max1.add(array1[i]);
        }
        for(int i = 0; i < array2.length; i++) {
            max2.add(array2[i]);
        }
        int sum = 0;
        while(!max1.isEmpty()) {
            int val1 = max1.peek();
            int val2 = max2.peek();
            if(val1 > val2) {
                sum += max2.size();
                max1.poll();
            }else if (val1 == val2) {
                max2.poll();
            }else if(val2  > val1) {
                max2.poll();
            }
        }
        return sum;
    }
    // contain decimal points
    // 567123.459
    //    123.4
    public String add_numbers(String number1, String number2){
        StringBuilder sb = new StringBuilder();
        int idx1 = 0;
        int idx2 = 0;
        while(idx1 < number1.length() && number1.charAt(idx1) != '.') {
            idx1++;
        }
        while(idx2 < number2.length() && number2.charAt(idx2) != '.') {
            idx2++;
        }
        StringBuilder num1 = new StringBuilder(number1.substring(idx1));
        StringBuilder num2 = new StringBuilder(number2.substring(idx2));
        StringBuilder num11 = new StringBuilder(number1.substring(0, idx1));
        StringBuilder num22 = new StringBuilder(number2.substring(0, idx2));
        if(num11.length() <= num22.length()) {
            int count = num22.length() - num11.length();
            while(count != 0) {
                num11.insert(0, '0');
                count--;
            }
        }else {
            int count = - num22.length() + num11.length();
            while(count != 0) {
                num22.insert(0, '0');
                count--;
            }
        }
        if(idx1 < number1.length() && idx2 < number2.length()) { // if decimal
            int count1 = num1.length();
            int count2 = num2.length();
            while(count2 < count1) {
                num2.append('0');
                count2++;
            }
            while(count2 > count1) {
                num1.append('0');
                count1++;
            }
        }else if(idx1 < number1.length() && idx2 == number2.length()) {
            int len = num1.length();
            int c = num2.length();
            num2.insert(0, '.');
            while(c < len - 1) {
                num2.append('0');
                c++;
            }
        }else if(idx1 == number1.length() && idx2 < number2.length()) {
            int len = num2.length();
            int c = num1.length();
            num1.insert(0, '.');
            while(c < len - 1) {
                num1.append('0');
                c++;
            }
        }
        num11.append(num1);
        num22.append(num2);
        idx1 = num11.length() - 1;
        idx2 = num22.length() - 1;
        int carry = 0;
        while(idx1 >= 0 && idx2 >= 0) {
            if(num11.charAt(idx1) == '.') {
                sb.append('.');
                idx1--;
                idx2--;
                continue;
            }
            int val1 = Character.getNumericValue(num11.charAt(idx1));
            int val2 = Character.getNumericValue(num22.charAt(idx2));
            idx1--;
            idx2--;
            int sum = val1 + val2 + carry;
            sb.append(sum % 10);
            carry = sum/10;
        }
        if(carry > 0) {
            sb.append(carry);
        }
        return sb.reverse().toString();
    }

    public List<Integer> runningSumDivide(int[] nums, int k) {
        if(nums.length  == 0) {
            return new ArrayList<>();
        }
        List<Integer> list = new ArrayList<>();
        int index = 0;
        int originalIndex = 0;
        int sum = 0;
        while(index < nums.length) {
            sum += nums[index];
            index++;
            if((index - originalIndex)%k == 0) {
                list.add(sum/k);
                if(originalIndex >= nums.length) {
                    continue;
                }
                sum = sum - nums[originalIndex];
                originalIndex++;
            }
        }
        return list;
    }

    // message, timestamp
    HashMap<String, Integer> hmap;
    public void loggerInitialize() {
        hmap = new HashMap<>();
    }

    public boolean shouldPrintMessage(int timestamp, String message) {
        if(!hmap.containsKey(message)) {
            hmap.put(message, timestamp);
            return true;
        }else{
            int time = hmap.get(message);
            if(time + 10 <= timestamp) {
                hmap.put(message, timestamp);
                return true;
            }
            return false;
        }
    }

    public long countSubarrays(int[] nums, int minK, int maxK) {
        int result = 0;
        List<List<Integer>> list = new ArrayList<>();
        for(int i = 0; i < nums.length; i++) {
            List<Integer> l = new ArrayList<>();
            for(int j = i; j < nums.length; j++) {
                int val = nums[j];
                l.add(val);
                if(checkMax(l, minK, maxK)) {
                    result++;
                }
            }
        }
        return result;
    }

    public boolean checkMax(List<Integer> temp, int minK, int maxK) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < temp.size(); i++) {
            max = Math.max(temp.get(i), max);
            min = Math.min(temp.get(i), min);
        }
        if(min == minK && max == maxK) {
            return true;
        }
        return false;
    }

    // 1,3,5,2,7,5
    public long countSubarraysFast(int[] nums, int minK, int maxK) {
        long result = 0;
        int left = -1;
        int minPosition = -1;
        int maxPosition = -1;
        for(int i = 0; i < nums.length; i++) {
            int val = nums[i];
            if(val < minK || val > maxK) {
                left = i;
            }
            if(val == minK) {
                minPosition = i;
            }
            if(val == maxK) {
                maxPosition = i;
            }
            result += Math.max(0, Math.min(maxPosition, minPosition) - left);
        }
        return result;
    }

    public long countSubArrays(int[] nums, int k){
        List<List<Integer>> result = new ArrayList<>();
        generateContiguousSubarrays(nums, result, k);
        long out = 0;
        for(List<Integer> list: result) {
            HashMap<Integer, Integer> hmap = new HashMap<>();
            int max = Integer.MIN_VALUE;
            for(int i = 0; i < list.size(); i++) {
                int key = list.get(i);
                if(!hmap.containsKey(key)) {
                    hmap.put(key, 1);
                }else {
                    hmap.put(key, hmap.get(key) + 1);
                }
                max = Math.max(max, hmap.get(key));
            }
            if(max >= k) {
                System.out.println(list);
                out++;
            }
        }
        return out;
    }

    public static List<List<Integer>> generateContiguousSubarrays(int[] arr, List<List<Integer>> result, int k) {
        int n = arr.length;

        for (int i = 0; i < n; i++) {
            List<Integer> subarray = new ArrayList<>();
            for (int j = i; j < n; j++) {
                subarray.add(arr[j]);
                if(!result.contains(subarray) && subarray.size() >= k) {
                    result.add(new ArrayList<>(subarray));
                }
            }
        }

        return result;
    }

    public int maxSumSubArray(int[] nums, int k) {
        int sum = 0;
        int max = Integer.MIN_VALUE;
        int index = 0;
        for(int i = 0; i < nums.length; i++) {
            int val = nums[i];
            sum += val;
            if(i - index + 1 == k) {
                max = Math.max(max, sum);
                sum -= nums[index];
                index = i;
            }
        }
        return max;
    }

    public int countAnagrams(String str, String pattern) {
        StringBuilder sb = new StringBuilder();
        int index = 0;
        HashMap<Character, Integer> hmapWord = new HashMap<>();
        for(int i = 0; i < pattern.length(); i++) {
            char ch = pattern.charAt(i);
            hmapWord.put(ch, hmapWord.getOrDefault(ch, 0) + 1);
        }
        int result = 0;
        HashMap<Character, Integer> hmap = new HashMap<>();
        for(int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            sb.append(ch);
            hmap.put(ch, hmap.getOrDefault(ch, 0) + 1);
            if(i - index  + 1 == pattern.length()) {
                if(checkAna(hmap, pattern)) {
                    result++;
                }
                char chold = str.charAt(index);
                index++;
                int value = hmap.get(chold);
                value--;
                if(value == 0) {
                    hmap.remove(chold);
                }else{
                    hmap.put(chold, value);
                }
            }
        }
        return result;
    }

    public boolean checkAna(HashMap<Character, Integer> currentMap, String word) {
        HashMap<Character, Integer> tmap = new HashMap<>(currentMap);
        for(int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if(tmap.containsKey(ch)) {
                int val = tmap.get(ch);
                val--;
                if(val == 0) {
                    tmap.remove(ch);
                }else{
                    tmap.put(ch, val);
                }
            }else{
                return false;
            }
        }
        return tmap.size() == 0? true:false;
    }




    public static void main(String[] args) {
        int n = 3;
        SlidingWindow ss = new SlidingWindow();
        int[] nums = new int[]{3, 5, 10, 1, 9};
        String str = "forxxorfxdofr";
        String word = "for";
        System.out.println(ss.countAnagrams(str, word));

    }
}
