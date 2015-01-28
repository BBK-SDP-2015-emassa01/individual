package smlTests;

import static org.junit.Assert.*;

import org.junit.Test;

import sml.Registers;

public class RegistersTest {

	private Registers testRegisters = new Registers();
	private int[] registers = {1,2,3,4,5,6,7,8,9};
	
	@Test
	public void setRegistersTest(){
		for (int i = 0; i != registers.length; i++) {
			registers[i] = 0;
		}
		
		testRegisters.setRegister(4, 50);

		int observed = testRegisters.getRegister(4); //test the setters and getters together.
		int expected = 50;
		
		assertEquals(expected, observed);
	}

}
