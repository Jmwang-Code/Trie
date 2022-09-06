import java.util.*;

/**
 * @author jmw
 * @Description TODO
 * @date 2022年08月25日 12:43
 * @Version 1.0
 */
public class Te {

    public static void main(String[] args) {
        System.out.println(findClosestElements(new int[]{1,2,3,4,5},4,3));
    }

    public static List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> list = new ArrayList<Integer>();
        for (int num : arr) {list.add(num);}
        Collections.sort(list, (a, b) -> {
            if (Math.abs(a - x) != Math.abs(b - x)) return Math.abs(a - x) - Math.abs(b - x);
            else return a - b;});
        List<Integer> ans = list.subList(0, k);
        Collections.sort(ans);
        return ans;
    }

}
