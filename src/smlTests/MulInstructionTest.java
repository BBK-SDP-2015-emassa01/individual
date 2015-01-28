package smlTests;

import static org.junit.Assert.*;

import org.junit.Test;

import sml.Machine;
import sml.MulInstruction;
import sml.Registers;

public class MulInstructionTest {
	
	MulInstruction mi = new MulInstruction("mul", 10, 5, 10);
	
	@Test
	public void executeTest(){
		Machine m = new Machine();
		Registers r = new Registers();
		m.setRegisters(r);
		
		m.getRegisters().setRegister(5, 5);// check getRegisters() and setRegister() methods.
		m.getRegisters().setRegister(10, 10);

		mi.execute(m);
		
		int res = m.getRegisters().getRegister(10);
		System.out.println(res); //printed result to screen
		assertTrue(res == 50); //checked the result
		assertTrue(m.getRegisters().getRegister(10) == 50); //checked the result was in the register.
	}
	
	@Test
	public void toStringTest() {
		String observed = mi.toString();
		String expected =  "mul: mul 5 * 10 to 10";
		assertEquals(observed, expected);
	}

}
