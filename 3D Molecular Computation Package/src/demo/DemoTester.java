package demo;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import algorithms.Physics;
import molecular.Bond;
import molecular.MolecularUnit;
import motion.Vector3d;
import simulation.Atom;
import simulation.Element;
import simulation.MolecularSimulation;

public class DemoTester {
	
	public static void main(String[] args) {
		System.out.println(new BigDecimal(5e-9));
		System.out.println(Physics.AVOGADROS_CONSTANT.doubleValue());
		System.out.println(Physics.calculateCoulombicAttractionNewtons(Physics.ELEMENTARY_CHARGE, Physics.ELEMENTARY_CHARGE, new BigDecimal(5e-9)));
		System.out.println("Testing1");
		MolecularSimulation.entityList = new ArrayList<>();
		MolecularSimulation.entityList.add(new Atom(BigDecimal.ONE.multiply(Physics.ELEMENTARY_CHARGE), BigDecimal.ONE.negate().divide(Physics.AVOGADROS_CONSTANT, 1000 ,RoundingMode.HALF_UP), BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO));
		MolecularSimulation.entityList.add(new Atom(BigDecimal.ONE.negate().multiply(Physics.ELEMENTARY_CHARGE), BigDecimal.ONE.negate().divide(Physics.AVOGADROS_CONSTANT, 1000 ,RoundingMode.HALF_UP), new BigDecimal(5e-9), new BigDecimal(5e-9), BigDecimal.ZERO));
		((Atom) MolecularSimulation.entityList.get(0)).applyForce(new Vector3d(new BigDecimal(1), new BigDecimal(1), new BigDecimal(1)));
		
		Element carbon = new Element(6, 12, 2, "C");
		Element oxygen = new Element(8, 16, 2, "O");
		
		MolecularUnit tester = new MolecularUnit("tester molecule");
		MolecularSimulation.entityList.add(tester);
		
		Bond testBond1 = new Bond(carbon, oxygen);
		
		System.out.println(tester.append(testBond1));
		System.out.println(tester);
		System.out.println(tester.append(testBond1));
		System.out.println(tester);
		
//		Thread updateLoop = new Thread(new UpdateLoop(), "Update");
//		updateLoop.start();
		System.out.println("Testing2");
	}
}
