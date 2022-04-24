package com.byby.mock.resources;

import com.github.tomakehurst.wiremock.WireMockServer;
import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;

import java.io.InputStream;
import java.util.Collections;
import java.util.Map;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

/*
 * Mock external calls
 *
 * todo
 */
public class WireMockExtensions implements QuarkusTestResourceLifecycleManager {
    private static final String JSON_FILE = "question.json";
    private WireMockServer wireMockServer;

    @Override
    public Map<String, String> start() {
        wireMockServer = new WireMockServer();
        wireMockServer.start();


        wireMockServer.stubFor(
                get(urlEqualTo("/random"))
                        .willReturn(aResponse()
                                .withHeader("Content-Type", "application/json")
                                .withBody(mockQuestion())));

//        wireMockServer.stubFor(get(urlMatching(".*"))
//                .atPriority(10)
//                .willReturn(aResponse().proxiedFrom("https://jservice.io/api")));

        return Collections.singletonMap("quarkus.rest-client.\"jservice.restclient.config\".url", wireMockServer.baseUrl());
    }

    private String mockQuestion(){
        try (InputStream is = WireMockExtensions.class.getResourceAsStream(JSON_FILE)) {
            String content = new String(is.readAllBytes());
            return content;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "[]";
        }
    }
    @Override
    public void stop() {
        if (null != wireMockServer) {
            wireMockServer.stop();
        }
    }
}
