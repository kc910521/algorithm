package ind.ck.construct;

import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @Author caikun
 * @Description //TODO $END
 * @Date 上午10:27 21-6-18
 **/
public class TreeMapTest {


    static TreeMap<Integer, Object> tm = new TreeMap<>();

    public static void main(String[] args) {
        tm.put(0, "奖品1");
        tm.put(20, "奖品2");
        tm.put(21, "奖品3");
        tm.put(80, "奖品4");
        tm.put(81, "奖品5");
        tm.put(100, "奖品6");
        SortedMap<Integer, Object> integerObjectSortedMap = tm.tailMap(0);
        System.out.println(tm.get(integerObjectSortedMap.firstKey()));

    }
}
