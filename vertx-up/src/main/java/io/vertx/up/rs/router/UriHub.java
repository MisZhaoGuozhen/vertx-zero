package io.vertx.up.rs.router;

import io.vertx.ext.web.Route;
import io.vertx.up.atom.agent.Event;

/**
 * Hub for Uri basic
 * path, method, order
 * register to route to generate media support
 */
public class UriHub implements Hub<Route> {

    @Override
    public void mount(final Route route,
                      final Event event) {
        route.path(event.getPath())
                .method(event.getMethod())
                .order(event.getOrder());
    }
}
