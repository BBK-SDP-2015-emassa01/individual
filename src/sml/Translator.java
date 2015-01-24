package sml;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

/*
 * The translator of a <b>S</b><b>M</b>al<b>L</b> program.
 */
public class Translator {

	// word + line is the part of the current line that's not yet processed
	// word has no whitespace
	// If word and line are not empty, line begins with whitespace
	private String line = "";
	private Labels labels; // The labels of the program being translated
	private ArrayList<Instruction> program; // The program to be created
	private String fileName; // source file of SML code

	private static final String SRC = "src";

	public Translator(String fileName) {
		this.fileName = SRC + "/" + fileName;
	}

	// translate the small program in the file into lab (the labels) and
	// prog (the program)
	// return "no errors were detected"
	public boolean readAndTranslate(Labels lab, ArrayList<Instruction> prog) {

		try (Scanner sc = new Scanner(new File(fileName))) {
			// Scanner attached to the file chosen by the user
			labels = lab;
			labels.reset();
			program = prog;
			program.clear();

			try {
				line = sc.nextLine();
			} catch (NoSuchElementException ioE) {
				return false;
			}

			// Each iteration processes line and reads the next line into line
			while (line != null) {
				// Store the label in label
				String label = scan();

				if (label.length() > 0) {
					Instruction ins = getInstruction(label);
					if (ins != null) {
						labels.addLabel(label);
						program.add(ins);
					}
				}

				try {
					line = sc.nextLine();
				} catch (NoSuchElementException ioE) {
					return false;
				}
			}
		} catch (IOException ioE) {
			System.out.println("File: IO error " + ioE.getMessage());
			return false;
		}
		return true;
	}

	// line should consist of an MML instruction, with its label already
	// removed. Translate line into an instruction with label label
	// and return the instruction
	public Instruction getInstruction(String label) {

		if (line.equals("")) {
			return null;
		}

		// get the filename and convert it to classname
		String ClassInstruction = scan() + "Instruction";
		ClassInstruction = ClassInstruction.substring(0, 1).toUpperCase()
				+ ClassInstruction.substring(1, ClassInstruction.length());

		/*
		 * This method uses the fileName, to scan the instructions in the lines
		 * of the program. for each Instruction Class, will find the right
		 * number of parameters (required for the constructor of the class), and
		 * will return the Class constructor with the correct number of
		 * parameters to declare it. Now I will use reflection to do this.
		 */

		try {
			// Using example here:
			// http://docs.oracle.com/javase/tutorial/reflect/member/ctorLocation.html
			// https://www.youtube.com/watch?v=agnblS47F18 Derek Banas Youtube
			Class<?> aClass = Class.forName("sml." + ClassInstruction);
			Constructor<?>[] allInstructionConstructors = aClass
					.getDeclaredConstructors();
			Constructor<?> theConstructor = allInstructionConstructors[0];

			// now get the parameters needed for the constructor
			Object[] parametersForConstructor = new Object[theConstructor
					.getParameters().length];

			parametersForConstructor[0] = label;

			for (int i = 1; i < theConstructor.getParameters().length; i++) {
				if (theConstructor.getParameters()[i].getType().equals(
						java.lang.String.class)) {
					String parameterString = scan();
					parametersForConstructor[i] = parameterString;

				} else if (theConstructor.getParameters()[i].getType().equals(
						int.class)) {
					int parameterInt = scanInt();
					parametersForConstructor[i] = parameterInt;
				} else {
					System.out.println("Didn't find it.");
				}
			}

			try {
				return (Instruction) theConstructor
						.newInstance(parametersForConstructor);

			} catch (InstantiationException | IllegalAccessException
					| IllegalArgumentException | InvocationTargetException e) {
				System.out.println("I can't do/create/access that!");
				e.printStackTrace();
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.err.println("It's all gone wrong.");
		}
		return null;
	}

	/*
	 * Return the first word of line and remove it from line. If there is no
	 * word, return ""
	 */
	private String scan() {
		line = line.trim();
		if (line.length() == 0)
			return "";

		int i = 0;
		while (i < line.length() && line.charAt(i) != ' '
				&& line.charAt(i) != '\t') {
			i = i + 1;
		}
		String word = line.substring(0, i);
		line = line.substring(i);
		return word;
	}

	// Return the first word of line as an integer. If there is
	// any error, return the maximum int
	private int scanInt() {
		String word = scan();
		if (word.length() == 0) {
			return Integer.MAX_VALUE;
		}

		try {
			return Integer.parseInt(word);
		} catch (NumberFormatException e) {
			return Integer.MAX_VALUE;
		}
	}
}