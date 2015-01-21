package sml;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * This class ....
 * 
 * @author Esha Massand
 */
@EqualsAndHashCode(callSuper=false)
@Data
public class AddInstruction extends Instruction {

	private int result;
	private int op1;
	private int op2;

//	public AddInstruction(String label, String op) {
//		super(label, op);
//		System.out.println("AddInstructionClass");
//	}

	public AddInstruction(String label, int result, int op1, int op2) {
		super(label, "add");
		this.result = result;
		this.op1 = op1;
		this.op2 = op2;
	}

	@Override
	public void execute(Machine m) {
		int value1 = m.getRegisters().getRegister(op1);
		int value2 = m.getRegisters().getRegister(op2);
		m.getRegisters().setRegister(result, value1 + value2);
		System.out.println(m.getProg());
	}

	@Override
	public String toString() {
		return super.toString() + " " + op1 + " + " + op2 + " to " + result;
	}
}