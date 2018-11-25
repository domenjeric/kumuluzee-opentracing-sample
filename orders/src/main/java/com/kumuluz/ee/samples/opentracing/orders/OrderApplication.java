package com.kumuluz.ee.samples.opentracing.orders;

import com.kumuluz.ee.opentracing.filters.OpenTracingServerRequestFilter;
import com.kumuluz.ee.opentracing.filters.OpenTracingServerResponseFilter;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("v1")
public class OrderApplication extends Application {

    public OrderApplication() {
    }

    @Override
    public Set<Class<?>> getClasses() {

        Set<Class<?>> classes = new HashSet<>();

        //OpenTracing JAX-RS filters
        classes.add(OpenTracingServerRequestFilter.class);
        classes.add(OpenTracingServerResponseFilter.class);

        //resources
        classes.add(OrderResource.class);

        return classes;
    }
}
