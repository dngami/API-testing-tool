package com.restapi.FlightApi.Exception;


import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException>{

	@Override
	public Response toResponse(DataNotFoundException ex) {
		ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(),404,"https://docs.google.com/document/d/13HX9Dx-0UGcPmuVZ5vgiM9T93ubrQazdMFbKoxa3skM/edit");
		return Response.status(Status.NOT_FOUND)
				.entity(errorMessage)
				.build();
	}

}
