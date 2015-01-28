package smlTests;

import static org.junit.Assert.*;

import org.junit.Test;

import sml.Machine;
import sml.OutInstruction;
import sml.Registers;

public class OutInstructionTest {

	OutInstruction oi = new OutInstruction("out", 5);
	
	@Test
	public void executeTest(){
		Machine m = new Machine();
		Registers r = new Registers();
		m.setRegisters(r);
		
		// check getRegisters() and setRegister() methods.
		m.getRegisters().setRegister(5, 5);
		
		oi.execute(m);
		
		int res = m.getRegisters().getRegister(5);
		System.out.println(res); //printed result to screen
		assertTrue(res == 5); //checked the result
		assertTrue(m.getRegisters().getRegister(5) ==5); //checked the result was in the register.
	}
	
	//no toString() as uses the super.


}
