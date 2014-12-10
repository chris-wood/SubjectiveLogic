public class Recommendation implements LogicOperation {

	@Override
	public Opinion apply(Opinion l, Opinion r) {
		double b = l.b * r.b;
		double d = l.b * r.d;
		double u = l.d + l.u + (l.b * r.u);
		return new Opinion(b, d, u);
	}
	
}
