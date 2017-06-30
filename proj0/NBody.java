public class NBody {

	/** Return the radius of the universe. */
	public static double readRadius(String file) {
		In in = new In(file);
		int numOfPlanets = in.readInt();
		double radius = in.readDouble();
		return radius;
	}

	/** Returns an array of Planets. */
	public static Planet[] readPlanets(String file) {
		In in = new In(file);
		int numOfPlanets = in.readInt();
		double radius = in.readDouble();
		Planet[] allPlanets = new Planet[numOfPlanets];
		int i = 0;
		while (!in.isEmpty() && i < numOfPlanets) {
			allPlanets[i] = new Planet(0,0,0,0,0,"null");
			allPlanets[i].xxPos = in.readDouble();
			allPlanets[i].yyPos = in.readDouble();
			allPlanets[i].xxVel = in.readDouble();
			allPlanets[i].yyVel = in.readDouble();
			allPlanets[i].mass = in.readDouble();
			allPlanets[i].imgFileName = in.readString();
			i++;
		}
		return allPlanets;
	}

	public static void main(String[] args) {
		/** Read global variables. */
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];

		/** Set up an array of planets. */
		double radius = readRadius(filename);
		Planet[] planets = readPlanets(filename);
		int N = planets.length;

		/** Set up the drawing field and background. */
		StdDraw.setScale(-radius, radius);
		StdDraw.clear();
		StdDraw.picture(0, 0, "images/starfield.jpg");
		StdDraw.show();

		double[] xForces = new double[N];
		double[] yForces = new double[N];

		int time = 0;
		for (time = 0; time <= T; time += dt) {

			/** Update forces and planet variables. */
			for (int i = 0; i < N; i++) {
				xForces[i] = planets[i].calcNetForceExertedByX(planets);
				yForces[i] = planets[i].calcNetForceExertedByY(planets);
			}

			for (int i = 0; i < N; i++) {
				planets[i].update(dt, xForces[i], yForces[i]);
			}

			/** Drawing background and planets. */
			StdDraw.clear();
			StdDraw.picture(0, 0, "images/starfield.jpg");
			int j = 0;
			for (j = 0; j < N; j++) {
				planets[j].draw();
			}
			StdDraw.show(10);
		}
	}
}
