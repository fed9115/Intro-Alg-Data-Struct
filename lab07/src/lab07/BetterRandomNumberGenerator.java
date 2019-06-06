package lab07;

public class BetterRandomNumberGenerator implements RandomNumberGenerator {

	private long seed = 1;
	private long const1 = 16769023L;
	private long const2 = 1000;

	@Override
	public int nextInt(int max) {
		long x = this.seed;

		x = (a * x + c) % m;
		setSeed(x);

		return (int) Math.abs(seed % max);
	}

	@Override
	public void setSeed(long seed) {
		this.seed = seed;

	}

	@Override
	public void setConstants(long const1, long const2) {
		this.const1 = const1;
		this.const2 = const2;

	}

}
