package com.fy.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@Path("/hello")
public interface HelloService {

    @GET
    @Path("/say")
    String say(@QueryParam("name") String name);
}
