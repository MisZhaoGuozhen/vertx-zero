package io.vertx.up.rs.hunt;

import io.vertx.up.annotations.EndPoint;

import javax.ws.rs.*;

@EndPoint
public class GetParam {

    public GetParam() {
    }

    private void ensure(final Object input) {
        System.out.println(input);
        if (null == input) {
            throw new RuntimeException("name could not be get:" + input);
        }
    }

    @Path("/query")
    @GET
    public void getQuery(@QueryParam("name") final String name) {
        ensure(name);
    }

    @Path("/query/{name}")
    @GET
    public void getPath(@PathParam("name") final String name) {
        ensure(name);
    }

    @Path("/query/header")
    @GET
    public void getHeader(@HeaderParam("Content-Type") final String content) {
        ensure(content);
    }

    @Path("/query/cookie")
    @GET
    public void getCookie(@CookieParam("cookie") final String content) {
        System.out.println(content);
    }
}