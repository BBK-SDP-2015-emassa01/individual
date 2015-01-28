package smlTests;

import static org.junit.Assert.*;

import org.junit.Test;

import sml.Machine;
import sml.Registers;
import sml.SubInstruction;

public class SubInstructionTest {

	private int result = 2;
	private int s1 = 9;
	private int s2 = 7;	
	
	@Test
	public void executeTest(){
		Machine m = new Machine();
		Registers r = new Registers();
		m.setRegisters(r);
		
		m.getRegisters().setRegister(9, 9);// check getRegisters() and setRegister() methods.
		m.getRegisters().setRegister(7, 7);
		
		SubInstruction si = new SubInstruction("sub", 2, 9, 7);
		si.execute(m);
		
		int res = m.getRegisters().getRegister(2);
		System.out.println(res); //printed result to screen
		assertTrue(res == 2); //checked the result
		assertTrue(m.getRegisters().getRegister(2) ==2); //checked the result was in the register.
	}
	
	@Test
	public void toStringTest() {
		SubInstruction addInstructionTest = new SubInstruction("sub", 2, 9, 7);
		String observed = addInstructionTest.toString();
		String expected =  "sub: sub 9 - 7 to 2";
		assertEquals(expected, observed);
	}

}
