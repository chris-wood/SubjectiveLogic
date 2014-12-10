public class Consensus implements LogicOperation {

	@Override
	public Opinion apply(Opinion l, Opinion r) {
		double b = ((l.b * r.u) + (r.b * l.u)) / (l.u + r.u - (l.u * r.u));
		double d = ((l.d * r.u) + (r.d * l.u)) / (l.u + r.u - (l.u * r.u));
		double u = (l.u * r.u) / (l.u + r.u - (l.u * r.u));
		return new Opinion(b, d, u);
	}

}
