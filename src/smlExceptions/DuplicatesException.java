package smlExceptions;

public class DuplicatesException extends RuntimeException {
	
	/**
	 * A Class to throw duplicates if there are duplicates (for example, duplicate labels within the sml program).
	 */
	private static final long serialVersionUID = 1L; //for serialisation.

	public DuplicatesException(){
	}

	public DuplicatesException(String message){
	}
	
	public RuntimeException DuplicateLabelsException(RuntimeException r){
		return r;
	}
}
