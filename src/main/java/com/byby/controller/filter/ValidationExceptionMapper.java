package com.byby.controller.filter;

import com.byby.controller.exception.ValidationException;
import com.byby.dto.response.ResponseDto;
import org.jboss.logmanager.Level;
import org.jboss.logmanager.Logger;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import static com.byby.dto.response.ResponseDto.ResponseCode.VALIDATION_ERROR;

@Provider
public class ValidationExceptionMapper implements ExceptionMapper<ValidationException> {
    private static Logger LOG = Logger.getLogger(ValidationExceptionMapper.class.getName());

    @Override
    public Response toResponse(ValidationException e) {
        LOG.log(Level.ERROR,String.format(">>> Valudation exception. %s", e.getMessage()), e);

        return Response.status(500)
                .entity(new ResponseDto(VALIDATION_ERROR, e.getMessage(), null))
                .build();

    }
}
