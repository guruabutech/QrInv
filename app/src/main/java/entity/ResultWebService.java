package entity;

import java.util.List;

public class ResultWebService {
	private Boolean newDevice;
	private Integer numDevices;
	private int error;
	private String message;
	

	public String getMessage(){
		return message;
	}
	
	public int getError(){
		return error;
	}
	
	public void setError(int error){
		this.error=error;
	}

	public void setMessage(String message) {
		// TODO Auto-generated method stub
		this.message = message;
	}

}
