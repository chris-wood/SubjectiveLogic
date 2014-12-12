public class Opinion {
	public double b;
	public double d;
	public double u;
	
	public Opinion(double belief, double disbelief, double unknown) {
		b = belief;
		d = disbelief;
		u = unknown;
	}
	
	public Opinion(String belief, String disbelief, String unknown) {
		b = Double.parseDouble(belief);
		d = Double.parseDouble(disbelief);
		u = Double.parseDouble(unknown);
	}
	
	public double project() {
		return ((b + u) / (b + d + 2*u));
	}
	
	@Override
	public String toString() {
		return "{" + b + "," + d + "," + u + "}"; 
	}
}
