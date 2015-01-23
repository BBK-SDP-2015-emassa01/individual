package sml;

/**
 * This class stores the result x in
 * register r, given the Instruction format: Label1 lin r x
 * 
 * @author Esha Massand
 */

public class LinInstruction extends Instruction {
	private int register;
	private int value;

	public LinInstruction(String label, int register, int value) {
		super(label, "lin");
		this.register = register;
		this.value = value;
	}

	@Override
	public void execute(Machine m) {
		m.getRegisters().setRegister(register, value);
	}

	@Override
	public String toString() {
		return super.toString() + " register " + register + " value is " + value;
	}
}