package smlExceptions;


public class DuplicatesException extends RuntimeException {
	
	public DuplicatesException(){
	}

	public DuplicatesException(String message){
	}
	
	public RuntimeException DuplicateLabelsException(RuntimeException r){
		return r;
	}
}
