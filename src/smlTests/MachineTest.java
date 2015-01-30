package smlTests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import sml.*;


public class MachineTest {
	
	Machine m = new Machine();
	Registers r = new Registers();
	ArrayList<Instruction> prog = new ArrayList<>();
	
	@Before
	public void initialise(){
		m.setRegisters(r);
		m.getRegisters().setRegister(3, 3);// check getRegisters() and setRegister() methods.
		m.getRegisters().setRegister(4, 4);
		m.getRegisters().setRegister(5, 5);
		
		AddInstruction ai = new AddInstruction("add", 5, 3, 4); //create some instructions
		SubInstruction ai2 = new SubInstruction("sub", 6, 8, 11);
		DivInstruction ai3 = new DivInstruction("div",7, 12, 13);
		MulInstruction ai4 = new MulInstruction("mul",9, 10, 14);
		
		prog.add(ai); //add the instructions to the program
		prog.add(ai2);
		prog.add(ai3);
		prog.add(ai4);	
		
		m.setProg(prog);
	}
	
	
	@Test 
	public void executeTest(){
		assertEquals ("add: add 3 + 4 to 5", m.getProg().get(m.getPc()).toString());
		m.execute();
		assertEquals(4, prog.size());
		assertEquals(4, m.getPc());		
	}

	@Test
	public void toStringTest(){
		
	m.setProg(prog);
	
	assertEquals("add: add 3 + 4 to 5\nsub: sub 8 - 11 to 6\ndiv: div 12 / 13 to 7\nmul: mul 10 * 14 to 9\n", m.toString());	
	}
	
}
