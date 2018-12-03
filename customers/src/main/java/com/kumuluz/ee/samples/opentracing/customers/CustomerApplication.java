package com.kumuluz.ee.samples.opentracing.customers;

import com.kumuluz.ee.opentracing.filters.OpenTracingServerRequestFilter;
import com.kumuluz.ee.opentracing.filters.OpenTracingServerResponseFilter;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("v1")
public class CustomerApplication extends Application {
}
