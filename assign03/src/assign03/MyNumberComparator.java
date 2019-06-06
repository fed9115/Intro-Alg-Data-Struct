package assign03;

import java.util.Comparator;

public class MyNumberComparator implements Comparator<MyNumber> {

	@Override
	public int compare(MyNumber o1, MyNumber o2) {
		return o1.myNumberInt() - o2.myNumberInt();
	}

}
