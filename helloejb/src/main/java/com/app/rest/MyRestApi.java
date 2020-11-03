package com.app.rest;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

//@Path("/api/v1")
@ApplicationScoped
@Path("/api")
@Produces("text/plain")
public class MyRestApi {

    @GET
    public String find(){
        System.out.println("find()");
        return "SUCCESS";
    }
}
