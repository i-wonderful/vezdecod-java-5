package com.byby.controller.filter;

import com.byby.dto.response.ResponseDto;
import org.jboss.logmanager.Level;
import org.jboss.logmanager.Logger;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import static com.byby.dto.response.ResponseDto.ResponseCode.*;

@Provider
public class RuntimeExceptionMapper implements ExceptionMapper<RuntimeException> {
private static Logger LOG = Logger.getLogger(RuntimeExceptionMapper.class.getName());
    @Override
    public Response toResponse(RuntimeException e) {
        LOG.log(Level.ERROR,String.format(">>> Runtime exception. %s", e.getMessage()), e);

        return Response.status(500)
                .entity(new ResponseDto(INTERNAL_ERROR, e.getMessage(), null))
                .build();
    }
}
