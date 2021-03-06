package io.vertx.up.rs;

import io.vertx.ext.unit.TestContext;
import io.vertx.quiz.ZeroBase;
import io.vertx.up.eon.em.ServerType;
import io.vertx.up.example.AnnoAgent;
import io.vertx.up.example.AnnoExceAgent;
import io.vertx.up.micro.ZeroHttpAgent;
import io.vertx.up.web.ZeroAnno;
import io.vertx.up.web.ZeroHelper;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.ConcurrentMap;

public class VertxAnnoTc extends ZeroBase {

    private static final ConcurrentMap<ServerType, List<Class<?>>>
            AGENTS = ZeroAnno.getAgents();

    private boolean isDefine(final Class<?>... args) {
        synchronized (AGENTS) {
            final ConcurrentMap<ServerType, Boolean>
                    defined = ZeroHelper.isAgentDefined(AGENTS, args);
            return defined.get(ServerType.HTTP);
        }
    }

    @Test
    public void testNo() {
        
    }

    public void testAnno(final TestContext context) {
        context.assertNotNull(AGENTS);
    }

    public void testDefined(final TestContext context) {
        final boolean isDefined = isDefine(AnnoExceAgent.class,
                ZeroHttpAgent.class);
        context.assertTrue(isDefined);
    }

    public void testUndefined(final TestContext context) {
        final boolean isDefined = isDefine(AnnoExceAgent.class,
                ZeroHttpAgent.class, AnnoAgent.class);
        context.assertFalse(isDefined);
    }

    public void testExcpetion() {
        isDefine(ZeroHttpAgent.class);
    }
}
