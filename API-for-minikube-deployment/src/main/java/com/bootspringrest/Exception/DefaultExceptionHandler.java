package com.bootspringrest.Exception;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class DefaultExceptionHandler extends Throwable {
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorMessage> somethingWentWrong(Exception ex){
		ErrorMessage exceptionResponse =
				new ErrorMessage("Wrong url! refer the documentation",405,"https://docs.google.com/document/d/13HX9Dx-0UGcPmuVZ5vgiM9T93ubrQazdMFbKoxa3skM/edit");
		return new ResponseEntity<ErrorMessage>(exceptionResponse,new HttpHeaders(),HttpStatus.BAD_REQUEST);
	}

}

class ErrorMessage {
	private String message;
	private int errorID;
	private String documentation;
	public ErrorMessage(String message,int errorID,String documentation) {
		super();
		this.message=message;
		this.errorID=errorID;
		this.documentation=documentation;
		// TODO Auto-generated constructor stub
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getErrorID() {
		return errorID;
	}
	public void setErrorID(int errorID) {
		this.errorID = errorID;
	}
	public String getDocumentation() {
		return documentation;
	}
	public void setDocumentation(String documentation) {
		this.documentation = documentation;
	}
	
}
