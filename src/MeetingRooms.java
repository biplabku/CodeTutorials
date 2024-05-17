import java.util.*;

public class MeetingRooms {


    // room 1 - 0, 10   ||
    // room 2 - 1, 5    ||  5, 10

    public static boolean canAddMeeting(int[][] intervals) {
        if(intervals.length == 0) {
            return true;
        }

        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        PriorityQueue<Integer> endMeetingTime = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        for(int i = 0; i < intervals.length; i++) {
            int[] meeting = intervals[i];
            if(endMeetingTime.isEmpty()) {
                endMeetingTime.add(meeting[1]);
            }else {
                int top = endMeetingTime.peek();
                if(top <= meeting[0]) {
                    endMeetingTime.add(meeting[1]);
                }else {
                    return false;
                }
            }
        }
        return true;
    }
    // 0,30 = 5,10 = 15-20
    // 2,4 == 7,10
    public static int minMeetingRooms(int[][] intervals) {
        if(intervals.length == 0) {
            return 0;
        }
        // start by start time
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        PriorityQueue<Integer> minQueue = new PriorityQueue<>();
        int numRooms = 0;
        // if there is an overlap, we create a new room
        for(int i = 0; i < intervals.length; i++) {
            int[] meeting = intervals[i];
            if(minQueue.isEmpty()) {
                minQueue.add(meeting[1]);
                numRooms++;
            }else{
                int start = meeting[0];
                int end = meeting[1];
                int currentEnd = minQueue.peek();
                if(start < currentEnd) {
                    numRooms++;
                }else{
                    minQueue.poll();
                }
                minQueue.add(end);
            }
        }
        return numRooms;
    }


    public static List<Integer> minAvailableDuration(int[][] slots1, int[][] slots2, int duration) {
        Arrays.sort(slots1, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        Arrays.sort(slots2, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        List<Integer> list = new ArrayList<>();
        int idx1 = 0;
        int idx2 = 0;
        while(idx1 < slots1.length && idx2 < slots2.length) {
            int start = Math.max(slots1[idx1][0], slots2[idx2][0]);
            int end = Math.min(slots1[idx1][1], slots2[idx2][1]);

            int endTimeNew = start + duration;
            if(end > endTimeNew) {
                return Arrays.asList(start, endTimeNew);
            }
            if(slots1[idx1][1] < slots2[idx2][1]) {
                idx1++;
            }else{
                idx2++;
            }
        }
        return new ArrayList<>();
    }

    // room 1 = 0, 10
    // room 2 = 1, 5   == 5, 10,    5 - 2, 10 - 1
    public static int mostBooked(int n, int[][] meetings) {
        PriorityQueue<int[]> usedRooms = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] == o2[0]? o1[1] - o2[1]: o1[0] - o2[0];
            }
        });
        PriorityQueue<Integer> unusedRooms = new PriorityQueue<>();
        for(int i = 0; i < n; i++) {
            unusedRooms.offer(i);
        }
        Arrays.sort(meetings, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        int[] meetingCount = new int[n];
        for(int[] meeting : meetings) {
            int start = meeting[0];
            int end = meeting[1];

            while(!usedRooms.isEmpty() && usedRooms.peek()[0] <= start){
                int room = (int) usedRooms.poll()[1];
                unusedRooms.offer(room);
            }
            if(!unusedRooms.isEmpty()){
                int room = unusedRooms.poll();
                usedRooms.offer(new int[]{end, room});
                meetingCount[room]++;
            }else{
                int availableTime = usedRooms.peek()[0];
            }
        }
        return 1; // have to do it later
    }

    /*
    Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
    Output: [1.00000,-1.00000,-1.00000,3.00000,5.00000,6.00000]
    Explanation:
    Window position                Median
    ---------------                -----
    [1  3  -1] -3  5  3  6  7        1
     1 [3  -1  -3] 5  3  6  7       -1
     1  3 [-1  -3  5] 3  6  7       -1
     1  3  -1 [-3  5  3] 6  7        3
     1  3  -1  -3 [5  3  6] 7        5
     1  3  -1  -3  5 [3  6  7]       6
    */
    public static double[] medianSlidingWindow(int[] nums, int k){
        if(nums.length == 0) {
            return new double[0];
        }
        Queue<Integer> queue = new LinkedList<>();
        HashMap<Integer, Integer> hmap = new HashMap<>();
        PriorityQueue<Integer> minQueue = new PriorityQueue<>();
        PriorityQueue<Integer> maxQueue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        int count = 0;
        double[] result = new double[nums.length - k];
        for(int i = 0; i < nums.length; i++){
            int value = nums[i];
            addNum(value, minQueue, maxQueue);
            int size = minQueue.size() + maxQueue.size();
            if(size == k){
                if(size % 2 == 0){
                    double median = (double)(minQueue.peek() + maxQueue.peek())/2;
                    result[count] = median;
                }else{
                    double median = (double) minQueue.size() > maxQueue.size()? minQueue.peek():maxQueue.peek();
                    result[count] = median;
                }
                if(minQueue.size() > maxQueue.size()) {
                    minQueue.poll();
                }else{
                    maxQueue.poll();
                }
                count++;
            }
        }
        return result;
    }

    public static void addNum(int num, PriorityQueue<Integer> minQueue, PriorityQueue<Integer> maxQueue) {
        minQueue.add(num);
        maxQueue.add(minQueue.poll());
        while(minQueue.size() < maxQueue.size()) {
            minQueue.offer(maxQueue.poll());
        }
    }


    public static void main(String[] args) {
        MeetingRooms mr = new MeetingRooms();
        int n = 3;
        int[][] meeting = new int[][]{{0,10},{1,5},{2,7},{3,4}};
        int[] nums = new int[]{1,3,-1,-3,5,3,6,7};
        System.out.println(medianSlidingWindow(nums, n));
    }
}
