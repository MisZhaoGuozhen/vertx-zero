package io.vertx.up.rs.hunt;

import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;
import io.vertx.up.atom.Envelop;
import io.vertx.up.atom.agent.Event;
import io.vertx.up.func.Fn;
import io.vertx.up.rs.Aim;
import io.vertx.up.web.ZeroResponser;

/**
 * BlockAim: Non-Event Bus: One-Way
 */
public class BlockAim extends BaseAim implements Aim<RoutingContext> {

    @Override
    public Handler<RoutingContext> attack(final Event event) {
        return Fn.get(() -> (context) -> ZeroResponser.exec(() -> {
            // 1. Build Arguments
            final Object[] arguments = buildArgs(context, event);

            // 2. Method call
            invoke(event, arguments);

            // 3. Resource model building
            final Envelop data = Envelop.ok();

            // 4. Process modal
            Answer.reply(context, data, event);
        }, context, event), event);
    }
}
