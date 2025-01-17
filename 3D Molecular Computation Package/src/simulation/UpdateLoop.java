package simulation;

import java.math.BigDecimal;


public class UpdateLoop implements Runnable {

	public static boolean running = true;
	public static boolean program = true;

	public void update() {
		System.out.println("updating without time");
		for (Updatable a : MolecularSimulation.entityList) {
			if (a instanceof Atom) {
				((Atom) a).appliedForces.clear();
				for (Updatable b : MolecularSimulation.entityList) {
					if (b instanceof Atom && !b.equals(a)) {
						((Atom) a).applyForce(((Atom) a)
								.getCoulombicForcesWith((Atom) b));
					}
				}
			}
		}
	}

	public void update(double milliseconds) {
		update();
		System.out.println("updating with time: "
				+ MolecularSimulation.entityList);
		for (Updatable u : MolecularSimulation.entityList) {
			if (u instanceof TimeUpdatable) {
				((TimeUpdatable) u).update(new BigDecimal(milliseconds));
				System.out.println(u);
				System.out.println("Magnitude: "
						+ ((Atom) u).getResultantForce().getMagnitude()
								.toEngineeringString());
			}
		}
	}

	public static void restartSimulation() {
		running = true;
	}

	public static void pauseSimulation() {
		running = false;
	}

	public static void destroySimulation() {
		// clean up resources and reset var
	}

	public void run() {

		long lastTime = System.nanoTime();
		double delta = 0.0;
		double ns = 1000000000.0 / 1.0;
		long timer = System.currentTimeMillis();
		int updates = 0;
			while (running) {
				long now = System.nanoTime();
				delta += (now - lastTime) / ns;
				lastTime = now;
				if (delta >= 1.0) {
					try {
						update(System.currentTimeMillis() - timer);
					} catch (Exception e) {
						e.printStackTrace();
						System.err.println("Update " + updates + " failed at "
								+ System.currentTimeMillis());
					}
					updates++;
					delta--;
				}

				if (System.currentTimeMillis() - timer > 1000) {
					timer += 1000;
					System.out.println(updates + " ups");
					updates = 0;
				}
			}
		}
	}
