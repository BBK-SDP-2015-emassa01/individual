package smlTests;

import static org.junit.Assert.*;

import org.junit.Test;

import sml.AddInstruction;
import sml.BnzInstruction;
import sml.Machine;
import sml.Registers;

public class BnzInstructionTest {

	private int result = 9;
	private String register = "f0";
	private Machine m = new Machine();
	private Machine m2 = new Machine();
	Registers r2 = new Registers();
	Registers r = new Registers();
	
	
	@Test
	public void executeTest() {
		
		m.setRegisters(r);
		m2.setRegisters(r2);
		
		BnzInstruction bnzInstructionTest2 = new BnzInstruction("bnz", result, register);
	
		
		m.getRegisters().setRegister(3,30); // check getRegisters() and setRegister() methods.
		m.getLabels().addLabel("f1");
		m.getLabels().addLabel("f2");
		m.getLabels().addLabel("f0"); //add some labels to check the addLabel(), getLabels() and indexOf() methods
		int expectedInt = m.getRegisters().getRegister(3); // check getRegister() and getRegisters() methods.
		System.out.println("The number is not zero: "+expectedInt);
		System.out.println("Skipping to instruction: "+m.getLabels().indexOf(register));
		
		bnzInstructionTest2.execute(m2);
		
		//void method so testing each component separately with println statements.
		assertEquals(2, m.getLabels().indexOf(register)); 
	}
}