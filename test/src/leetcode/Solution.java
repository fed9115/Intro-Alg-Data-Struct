package leetcode;

import java.util.ArrayList;

public class Solution {
	    public int[] sortArrayByParity(int[] A) {
	        ArrayList<Integer> odd = new ArrayList<>();
	        ArrayList<Integer> even = new ArrayList<>();
	        for (int i : A){
	            if (i % 2 == 0) even.add(i);
	            if (i % 2 != 0) odd.add(i);
	        }
	        even.addAll(odd);
	        
	        int[] res = (int[])new Integer[even.size()];
	        return even.toArray(res);
	    }
}
