public class Planet {
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;

	public Planet(double xP, double yP, 
				double xV, double yV, 
				double m, String img) {
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	public Planet(Planet p) {
		xxPos = p.xxPos;
		yyPos = p.yyPos;
		xxVel = p.xxVel;
		yyVel = p.yyVel;
		mass = p.mass;
		imgFileName = p.imgFileName;
	}

	/** This calculates the distance between two Planets. */
	public double calcDistance(Planet p) {
		return Math.sqrt(Math.pow(p.xxPos-xxPos, 2) 
			+ Math.pow(p.yyPos-yyPos, 2));
	}

	/** This returns the force that the taken-in Planet exerted on the "this" Planet. */
	public double calcForceExertedBy(Planet p) {
		return 6.67*Math.pow(10, -11)*mass*p.mass
		/(Math.pow(calcDistance(p), 2));
	}

	/** Force on the X axis */
	public double calcForceExertedByX(Planet p) {
		return calcForceExertedBy(p)*(p.xxPos-xxPos)/calcDistance(p);
	}

	/** Force on the Y axis */
	public double calcForceExertedByY(Planet p) {
		return calcForceExertedBy(p)*(p.yyPos-yyPos)/calcDistance(p);
	}

	/** Net force on the X axis */
	public double calcNetForceExertedByX(Planet[] all) {
		int i = 0;
		double netForce = 0;
		for (i = 0; i < all.length; i++) {
			if (!this.equals(all[i])) {
				netForce += calcForceExertedByX(all[i]);
			}
		}
		return netForce;
	}

	/** Net force on the Y axis */
	public double calcNetForceExertedByY(Planet[] all) {
		int i = 0;
		double netForce = 0;
		for (i = 0; i < all.length; i++) {
			if (!this.equals(all[i])) {
				netForce += calcForceExertedByY(all[i]);
			}
		}
		return netForce;
	}		

	/** Update the variables of the Planet. */
	public void update(double dt, double fX, double fY) {
		double aX = fX/mass;
		double aY = fY/mass;
		xxVel += dt*aX;
		yyVel += dt*aY;
		xxPos += dt*xxVel;
		yyPos += dt*yyVel;
	}

	/** Draws a planet. */
	public void draw() {
		StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
	}
}
