package smlTests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import sml.AddInstruction;
import sml.DivInstruction;
import sml.Instruction;
import sml.Machine;
import sml.MulInstruction;
import sml.Registers;
import sml.SubInstruction;
import sml.Translator;

public class MachineTest {

	Machine m = new Machine();
	Registers r = new Registers();
	ArrayList<Instruction> prog = new ArrayList<>();
	
	@Test
	public void setRegistersTest(){
		Registers r1 = new Registers();
		Registers expected = r1;
		m.setRegisters(r1);
		Registers observed = this.r;
		assertEquals(expected, observed);
	}
	

	@Test
	public void toStringTest(){
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
	
	}
	
}
