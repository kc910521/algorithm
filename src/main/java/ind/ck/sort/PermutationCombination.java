package ind.ck.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author caikun
 * @Description //TODO $END
 * @Date 下午5:17 20-11-10
 **/
public class PermutationCombination {

    public static void combination() {

    }

    public static void permutation(ArrayList<String> origins, ArrayList<String> results) {
        if (origins.size() == 0) {
            System.out.println(results);
            return;
        }
        for (int idx = 0; idx < origins.size(); idx ++) {
            ArrayList<String> nowOrigins = (ArrayList<String>) origins.clone();
            ArrayList<String> nowResults = (ArrayList<String>) results.clone();
            nowResults.add(nowOrigins.get(idx));
            nowOrigins.remove(idx);
            permutation(nowOrigins, nowResults);
        }

    }

    public static void main(String[] args) {
        ArrayList<String> stringList = new ArrayList<>(Arrays.asList("A","B","C"));
        permutation(stringList, new ArrayList<>());
    }

}
