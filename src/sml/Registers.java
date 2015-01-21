package sml;

import java.util.NoSuchElementException;

import lombok.Data;

/**
 * This class ....
 * 
 * An instance contains 32 registers and methods to access and change them
 * 
 * @author Esha Massand
 */

@Data
public class Registers {

	private final static int NUMBEROFREGISTERS = 32;
	private int registers[];

	// Constructor: an instance whose registers are set to 0

	{
		System.out.println("RegistersClass");
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
				System.out.println ("register OK:"+i);
				registers[i] = v;		
		}else {
			System.out.println("Warning! Your program attemped to save to a register that does not exist.\nCannot complete the operation. \nExiting program.");
			System.exit(0);
			}
	}

	public int getRegister(int i) {
		return registers[i];
	}
}