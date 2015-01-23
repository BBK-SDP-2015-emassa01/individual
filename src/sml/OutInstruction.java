package sml;

/**
 * This class prints on the console the value in
 * register s1, given the Instruction format: Label1 out s1
 * 
 * @author Esha Massand
 */

public class OutInstruction extends Instruction {
	private int register;
	private int result;

	public OutInstruction(String label, int register) {
		super(label, "out");
		this.register = register;
	}

	@Override
	public void execute(Machine m) {
		this.result = m.getRegisters().getRegister(register);
		System.out.println(super.toString() + " register " + register + " value is " + result);
	}
	
	/* No overridden to String() method -- System.out.println().
	*  No other functionality is required. 
	*/
}