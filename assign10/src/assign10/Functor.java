package assign10;

import java.util.Comparator;

/**
 * This is a functor class for us to design our own sorting method. Here, we
 * treat the integer with relative smaller value as the "bigger" one, so in the
 * max heap, the top node should have the smallest value actually by using this
 * functor
 * 
 * @author Kyle Perry, Erdi Fan
 *
 */
public class Functor implements Comparator<Integer> {
	@Override
	public int compare(Integer o1, Integer o2) {

		return o2 - o1;

	}

}
