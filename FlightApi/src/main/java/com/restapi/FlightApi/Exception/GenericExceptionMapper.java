package com.restapi.FlightApi.Exception;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable>{

	@Override
	public Response toResponse(Throwable ex) {
		ErrorMessage errorMessage = new ErrorMessage("The url is incorrect or the inputs are not in proper format please check the documentation",500,"https://docs.google.com/document/d/13HX9Dx-0UGcPmuVZ5vgiM9T93ubrQazdMFbKoxa3skM/edit");
		return Response.status(Status.BAD_REQUEST)
				.entity(errorMessage)
				.build();
	}

}
