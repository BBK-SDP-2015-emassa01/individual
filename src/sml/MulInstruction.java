package sml;

import lombok.Data;

import lombok.EqualsAndHashCode;

/**
 * This class multiplies the contents of registers op1 and op2 and stores the result in
 * register r, given the Instruction format: Label mul r op1 op2
 * 
 * @author Esha Massand
 */
@EqualsAndHashCode(callSuper=false)
@Data
public class MulInstruction extends Instruction {
	
	private int result;
	private int op1;
	private int op2;
	
	public MulInstruction(String label, int result, int op1, int op2) {
		super(label, "mul");
		this.result = result;
		this.op1 = op1;
		this.op2 = op2;
	}
	
	@Override
	public void execute(Machine m) {
		int value1 = m.getRegisters().getRegister(op1);
		int value2 = m.getRegisters().getRegister(op2);
		m.getRegisters().setRegister(result, value1 * value2);
	}
	
	@Override
	public String toString() {
		return super.toString() + " " + op1 + " * " + op2 + " to " + result;
	}

}
