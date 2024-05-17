import java.util.*;

public class MkAverage {
    PriorityQueue<Integer> min;
    List<Integer> list;
    int m;
    int k;
    public MkAverage(int m, int k){
        min = new PriorityQueue<>();
        this.m = m;
        this.k = k;
        list = new ArrayList<>();
    }

    public void addElements(int num){
        list.add(num);
    }

    public void removeElements() {

    }

    public int calculateMkAverage(){
        if(list.size() < m) {
            return -1;
        }
        return 1;
    }

    public static void learnTreeMap() {
        TreeMap<Integer, String> tmap = new TreeMap<>();
        tmap.put(3, "apple3");
        tmap.put(2, "apple2");
        tmap.put(1, "apple1");
        tmap.put(5, "apple5");
        tmap.put(6, "apple6");

        int lowestKey = tmap.firstKey();
        int highestKey = tmap.lastKey();
        System.out.println(lowestKey + " " + highestKey);
        System.out.println(tmap.headMap(4).keySet());
        System.out.println(tmap.tailMap(3).keySet());
    }

    static class Pair{
        double ratio;
        int quality;

        public Pair(double ratio, int quality){
            this.ratio = ratio;
            this.quality = quality;
        }
    }

    // quality = [10, 20, 5]
    // wage =    [70, 50, 30]
    // k =
    // 5  -- 30/5  = $6
    // 10 -- 70/10 = $7
    // 20 -- 50/20 = $2.5



    public static void main(String[] args){
        MkAverage ms = new MkAverage(3,1);
        learnTreeMap();
        /*
        ms.addElements(3);
        ms.addElements(1);
        System.out.println(ms.calculateMkAverage());
        ms.addElements(10);
        System.out.println(ms.calculateMkAverage());
        ms.addElements(5);
        ms.addElements(5);
        ms.addElements(5);
        System.out.println(ms.calculateMkAverage());
         */
    }
}
