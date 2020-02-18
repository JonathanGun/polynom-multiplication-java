package polynommult;

public abstract class MultBehav {
	public int ops;
	protected abstract Polynom multiply(Polynom p1, Polynom p2);
}