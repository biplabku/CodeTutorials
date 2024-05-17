import javax.print.attribute.standard.Media;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MedianProblems {

    private static PriorityQueue<Integer> minQueue;
    private static PriorityQueue<Integer> maxQueue;

    public MedianProblems(){
        minQueue = new PriorityQueue<>();
        maxQueue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
    }

    // maxQueue --> minQueue
    public static void addNum(int num) {
        minQueue.add(num);
        maxQueue.add(minQueue.poll());
        while(minQueue.size() < maxQueue.size()) {
            minQueue.offer(maxQueue.poll());
        }
    }

    public static double findMedian() {
        if((maxQueue.size() + minQueue.size())%2 == 0){
            int left = maxQueue.peek();
            int right = minQueue.peek();
            double median  = (double) (left + right)/2;
            return median;
        }else{
            if(maxQueue.size() > minQueue.size()) {
                return (double) maxQueue.peek();
            }else{
                return (double) minQueue.peek();
            }
        }
    }

    public static void main(String[] args) {
        MedianProblems ms = new MedianProblems();
        addNum(1);
        addNum(2);
        addNum(3);
        addNum(4);
        System.out.println(findMedian());

    }
}
