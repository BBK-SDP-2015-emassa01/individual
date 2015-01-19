package sml;

/**
 * This class ....
 * 
 * @author Esha Massand
 */

public class Out extends Instruction {
	private int register;
	private int result;

	public Out(String label, String opcode) {
		super(label, opcode);
		System.out.println("OutClass");
	}

	public Out(String label, int register) {
		super(label, "out");
		this.register = register;
	}

	@Override
	public void execute(Machine m) {
		this.result = m.getRegisters().getRegister(register);
		System.out.println(super.toString() + " register " + register + " value is " + result);
	}
	
	/* No overridden to String() method, as the 'execute' will print (using System.out.println()) the register values, and 
	*  no other functionality is required beyond he toString() method in the Parent Class, as specified by the course-work description. 
	*/
}