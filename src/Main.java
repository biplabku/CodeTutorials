import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    // -2, 1, -3, 4, -1, 2, 1, -5, 4

    public static int maxSubArraySum(int[] nums) {
        if(nums.length == 0) {
            return -1;
        }
        int maxSum = nums[0];
        int curSum = 0;
        for(int i = 0; i < nums.length; i++) {
            curSum += nums[0];
            maxSum = Math.max(maxSum, curSum);
            if(curSum < 0) {
                curSum = 0;
            }
        }
        return maxSum;
    }


    static class pair {
        char ch;
        int count;
        public pair(char ch, int count) {
            this.ch = ch;
            this.count = count;
        }
    }

    public static int minimumKeyPresses(String str) {
        if(str.isEmpty()) {
            return -1;
        }
        HashMap<Character, Integer> hmap = new HashMap<>();
        int numberOfWordPress = 0;
        for(int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            hmap.put(ch, hmap.getOrDefault(ch, 0) + 1);
        }
        PriorityQueue<pair> queue = new PriorityQueue<>(new Comparator<pair>() {
            @Override
            public int compare(pair o1, pair o2) {
                return o2.count - o1.count;
            }
        });
        Iterator iter = hmap.entrySet().iterator();
        while(iter.hasNext()) {
            Map.Entry pair1 = (Map.Entry)iter.next();
            char ch = (char)pair1.getKey();
            int count = (int)pair1.getValue();
            pair p = new pair(ch, count);
            queue.add(p);
        }
        int count = 0;
        while(!queue.isEmpty()) {
            pair p = queue.poll();
            count++;
            if(count <= 9) {
                numberOfWordPress += p.count;
            }else if(count > 9 && count <= 18) {
                numberOfWordPress += p.count * 2;
            }else {
                numberOfWordPress += p.count * 3;
            }
        }
        return numberOfWordPress;
    }

    // 3,4,5,5,3,1]

    public static int minimumSwaps(int[] nums) {
        // set maximum
        // set minimum

        int max = nums[nums.length - 1];
        int maxIndex = 0;
        for(int i = nums.length - 1; i >= 0; i--) {
            if(max < nums[i]) {
                max = nums[i];
                maxIndex = i;
            }
        }
        int result = swapMax(nums, maxIndex);

        int min = nums[0];
        int minIndex = 0;
        for(int i = 0; i < nums.length; i++) {
            if(min > nums[i]) {
                min = nums[i];
                minIndex = i;
            }
        }
        result += swapMin(nums, minIndex);
        return result;
    }

    // [3,4,5,3,1,5]
    public static int swapMin(int[] nums, int index){
        int count = 0;
        while(index - 1 >= 0) {
            int right = nums[index];
            int left = nums[index - 1];
            if(left > right) {
                nums[index] = left;
                nums[index - 1] = right;
                count++;
            }
            index--;
        }
        return count;
    }

    public static int swapMax(int[] nums, int index){
        int count = 0;
        while(index + 1 < nums.length) {
            int left = nums[index];
            int right = nums[index + 1];
            if(left > right) {
                nums[index] = right;
                nums[index + 1] = left;
                count++;
            }
            index++;
        }
        return count;
    }

    public static List<Integer> processServiceRequests(int[][] servers, int[][] requests) {
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < requests.length; i++) {
            int x = requests[i][0];
            int y = requests[i][1];
            int count = 0;
            for(int j = 0; j < servers.length; j++) {
                int x1 = servers[j][0];
                int y1 = servers[j][1];
                if(x1 >= x && y1 >=  y) {
                    count++;
                }
            }
            list.add(count);
        }
        return list;
    }

    // 5,10,17
    // 1,5,20,11,17
    public static List<Integer> storeHouse(int[] stores, int[] houses) {
        List<Integer> list = new ArrayList<>();

        for(int i = 0; i < houses.length; i++) {
            int val1 = houses[i];
            int diff = Integer.MAX_VALUE;
            int index = Integer.MAX_VALUE;
            for(int j = 0; j < stores.length; j++) {
                int val2 = stores[j];
                if(diff > Math.abs(val1 - val2)) {
                    diff = Math.abs(val1 - val2);
                    index = j;
                }
            }
            list.add(stores[index]);
        }

        return list;
    }

    public static List<Integer> storeHouseOptimize(int[] stores, int[] houses) {
        List<Integer> list = new ArrayList<>();
        Arrays.sort(stores);
        for(int i = 0; i < houses.length; i++) {
            int val = houses[i];
            int res = searchFast(stores, val);
            list.add(res);
        }
        return list;
    }

    public static int searchFast(int[] stores, int target){
        int left = 0;
        int right = stores.length - 1;
        while(left < right) {
            int middle = left + (right - left)/2;
            if(stores[middle] < target) {
                left = middle + 1;
            }else if(stores[middle] > target) {
                right = middle;
            }else{
                return target;
            }
        }
        if(left > 0 && Math.abs(stores[left - 1] - target) < Math.abs(stores[left] - target)){
            return stores[left - 1];
        }else{
            return stores[left];
        }
    }

    public static List<TreeNode> deleteNodes(TreeNode root, int[] delete) {
        HashMap<TreeNode, TreeNode> hmap = new HashMap<>();
        delete(root, hmap);
        Iterator iter = hmap.entrySet().iterator();
        List<TreeNode> toDelete = new ArrayList<>();
        while(iter.hasNext()) {
            Map.Entry pair = (Map.Entry)iter.next();
            TreeNode key = (TreeNode) pair.getKey();
            TreeNode value = (TreeNode) pair.getValue();
            // check if the key is equal to target
            for(int i = 0; i < delete.length; i++) {
                int val = delete[i];
                if(key != null && val == key.val) {
                    toDelete.add(key);
                }
            }
        }
        iter = hmap.entrySet().iterator();
        List<TreeNode> result = new ArrayList<>();
        while(iter.hasNext()) {
            Map.Entry pair = (Map.Entry)iter.next();
            TreeNode key = (TreeNode) pair.getKey();
            if(key != null && !toDelete.contains(key)) {
                if(key.left != null) {
                    result.add(key);
                }
                if(key.right != null) {
                    result.add(key);
                }
            }
        }
        return new ArrayList<>();
    }

    public static void delete(TreeNode node, HashMap<TreeNode, TreeNode> hmap){
        if(node != null) {
            if(!hmap.containsKey(node.left)) {
                hmap.put(node.left, node);
            }
            if(!hmap.containsKey(node.right)){
                hmap.put(node.right, node);
            }
            delete(node.left, hmap);
            delete(node.right, hmap);
        }
    }

    static HashMap<String, Integer> hmap = new HashMap<>();
    public Main(){
        hmap = new HashMap<>();
    }

    public static boolean shouldPrintMessage(int timestamp, String message) {
        if(!hmap.containsKey(message)) {
            hmap.put(message, timestamp);
            return true;
        }else{
            int time = hmap.get(message);
            if(time + 10  <= timestamp) {
                hmap.put(message, timestamp);
                return true;
            }else{
                return false;
            }
        }
    }

    public static List<Integer> slidingWindow(int[] array, int k) {
        HashMap<Integer, Integer> hmap = new HashMap<>();
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        int count = 0;
        for(int i = 0; i < array.length - k; i++) {
            int key = array[i];
            if(queue.size() != k) {
               queue.add(key);
               hmap.put(i, key);
               count++;
            }
            double median;

            if(k%2 == 0) {
            }
        }
        return new ArrayList<>();
    }


















    public static void main(String[] args) {
        int[][] servers = new int[][]{{1,3},{4,1},{6,6}};
        int[][] requests = new int[][]{{3,2},{1,1},{7,7}};
        int[] nums = new int[]{5,4,-1,7,8};
        int[] houses = new int[]{5,10,17};
        int[] stores = new int[]{1,5,20,11,16};
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        System.out.println(shouldPrintMessage(0,"A"));
        System.out.println(shouldPrintMessage(0,"B"));
        System.out.println(shouldPrintMessage(0,"C"));
        System.out.println(shouldPrintMessage(0,"A"));
        System.out.println(shouldPrintMessage(0,"B"));
        System.out.println(shouldPrintMessage(0,"C"));
        System.out.println(shouldPrintMessage(11,"A"));
        System.out.println(shouldPrintMessage(11,"B"));
        System.out.println(shouldPrintMessage(11,"C"));
        System.out.println(shouldPrintMessage(11,"A"));

        System.out.println(deleteNodes(root, new int[]{3,5}));
    }
}