package com.byby.integration;

import com.byby.integration.dto.QuestionExternalDto;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.List;

@RegisterRestClient(configKey = "jservice.restclient.config")
public interface JServiceRestClient {
    @GET
    @Path("/random")
    List<QuestionExternalDto> getRandom();

    @GET
    @Path("/clues")
    List<QuestionExternalDto> getAll();
}
