public class Conjunction implements LogicOperation {

	@Override
	public Opinion apply(Opinion l, Opinion r) {
		double b = l.b * r.b;
		double d = l.d + r.d - (l.d * r.d);
		double u = (l.b * r.u) + (l.u * r.b) + (l.u * r.u);
		return new Opinion(b, d, u);
	}

}
