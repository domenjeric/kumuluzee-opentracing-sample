package com.kumuluz.ee.samples.opentracing.orders;


import io.opentracing.Tracer;
import org.eclipse.microprofile.opentracing.Traced;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("orders")
@ApplicationScoped
@Traced
public class OrderResource {

    @Inject
    Tracer configuredTracer;

    @GET
    public Response getAllOrders() {
        configuredTracer.activeSpan().log("Getting all orders...");
        List<Order> orders = Database.getOrders();
        configuredTracer.activeSpan().log("Got "+ orders.size() + " orders.");
        return Response.ok(orders).build();
    }

    @GET
    @Path("{orderId}")
    public Response getOrder(@PathParam("orderId") String orderId) {
        Order order = Database.getOrder(orderId);
        return order != null
                ? Response.ok(order).build()
                : Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    public Response addNewOrder(Order order) {
        Database.addOrder(order);
        return Response.noContent().build();
    }

    @DELETE
    @Path("{orderId}")
    public Response deleteOrder(@PathParam("orderId") String orderId) {
        Database.deleteOrder(orderId);
        return Response.noContent().build();
    }
}

