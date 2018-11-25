package com.kumuluz.ee.samples.opentracing.customers;



import com.kumuluz.ee.opentracing.filters.OpenTracingClientRequestFilter;
import com.kumuluz.ee.opentracing.filters.OpenTracingClientResponseFilter;
import org.eclipse.microprofile.opentracing.Traced;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@ApplicationScoped
@Path("customers")
public class CustomerResource {

    @Inject
    private CustomersBean customersBean;

    @GET
    public Response getAllCustomers() {
        List<Customer> customers = Database.getCustomers();
        return Response.ok(customers).build();
    }

    @GET
    @Path("{customerId}")
    public Response getCustomer(@PathParam("customerId") String customerId) {
        Customer customer = Database.getCustomer(customerId);
        return customer != null
                ? Response.ok(customer).build()
                : Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    public Response addNewCustomer(Customer customer) {
        Database.addCustomer(customer);
        return Response.noContent().build();
    }

    @DELETE
    @Path("{customerId}")
    public Response deleteCustomer(@PathParam("customerId") String customerId) {
        Database.deleteCustomer(customerId);
        return Response.noContent().build();
    }

    @GET
    @Path("{customerId}/orders")
    public Response getCustomerOrders(@PathParam("customerId") String customerId) {
        return customersBean.getOrders();
    }

}

