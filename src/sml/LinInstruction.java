package sml;

/**
 * This class ....
 * 
 * @author Esha Massand
 */

public class LinInstruction extends Instruction {
	private int register;
	private int value;

//	public LinInstruction(String label, String opcode) {
//		super(label, opcode);
//		System.out.println("LinInstructionClass");
//	}

	public LinInstruction(String label, int register, int value) {
		super(label, "lin");
		this.register = register;
		this.value = value;
		System.out.println("LinInstructionClass");

	}

	@Override
	public void execute(Machine m) {
		m.getRegisters().setRegister(register, value);
		System.out.println(m.getProg());
	}

	@Override
	public String toString() {
		return super.toString() + " register " + register + " value is " + value;
	}
}