package smlTests;

import static org.junit.Assert.*;

import org.junit.Test;

import sml.Machine;
import sml.Registers;
import sml.SubInstruction;

public class SubInstructionTest {
	
	SubInstruction si = new SubInstruction("sub", 2, 9, 7);
	
	@Test
	public void executeTest(){
		Machine m = new Machine();
		Registers r = new Registers();
		m.setRegisters(r);
		
		m.getRegisters().setRegister(9, 9);// check getRegisters() and setRegister() methods.
		m.getRegisters().setRegister(7, 7);
		si.execute(m);
		
		assertTrue(m.getRegisters().getRegister(2) ==2); //checked the result was in the register.
	}
	
	@Test
	public void toStringTest() {
		String observed = si.toString();
		String expected =  "sub: sub 9 - 7 to 2";
		assertEquals(expected, observed);
	}

}
