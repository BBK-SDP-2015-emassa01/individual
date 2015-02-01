package sml;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * An instance of this class contains 32 registers and methods to access and change them.
 * 
 * @author Esha Massand
 */
@EqualsAndHashCode(callSuper=false)
/*
* FindBugs debugging revealed these as errors, but they are fine: 
* call to setRegisters() is fine.
* call to getRegisters() is fine.
*/
@Data
public class Registers {

	private final static int NUMBEROFREGISTERS = 32;
	private int registers[];

	// Constructor: an instance whose registers are set to 0

	{
		registers = new int[NUMBEROFREGISTERS];
	}

	public Registers() {
		for (int i = 0; i != registers.length; i++) {
			registers[i] = 0;
		}
	}

	// Set register i to v.
	// Precondition: 0 <= i <= NUMBEROFREGISTERS

	public void setRegister(int i, int v) {
		if ((i > 0) && (i < 33)) {
				registers[i] = v;		
		}else {
			System.out.println("Warning! Your program attemped to save to a register that does not exist.\nCannot complete the operation. \nExiting program.");
			}
	}

	public int getRegister(int i) {
		return registers[i];
	}
}