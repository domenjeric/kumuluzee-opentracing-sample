package com.kumuluz.ee.samples.opentracing.customers;

import com.kumuluz.ee.opentracing.filters.OpenTracingClientRequestFilter;
import com.kumuluz.ee.opentracing.filters.OpenTracingClientResponseFilter;
import io.opentracing.Tracer;
import org.eclipse.microprofile.opentracing.Traced;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;


@RequestScoped
public class CustomersBean {

    private Client httpClient;

    @Inject
    Tracer configuredTracer;

    @PostConstruct
    private void init() {
        httpClient = ClientBuilder.newBuilder()
                .register(OpenTracingClientRequestFilter.class)
                .register(OpenTracingClientResponseFilter.class)
                .build();
    }

    @Traced(operationName = "Get customer orders")
    public Response getOrders() {
        try {
            configuredTracer.activeSpan().log("Fetching customer orders...");
            return httpClient.target("http://localhost:3001/v1/orders")
                    .request()
                    .get();
        } catch (WebApplicationException | ProcessingException e) {
            throw new WebApplicationException(e);
        }
    }
}
