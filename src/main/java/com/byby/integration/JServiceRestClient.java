package com.byby.integration;

import com.byby.integration.dto.QuestionExternalDto;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.List;

@Path("/random")
@RegisterRestClient(configKey="jservice.restclient.config" )
public interface JServiceRestClient {
    @GET
    List<QuestionExternalDto> getRandom();
}
