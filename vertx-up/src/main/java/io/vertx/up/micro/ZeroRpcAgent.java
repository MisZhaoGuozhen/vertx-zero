package io.vertx.up.micro;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.AsyncResult;
import io.vertx.core.ServidorOptions;
import io.vertx.core.json.JsonObject;
import io.vertx.core.net.JksOptions;
import io.vertx.grpc.VertxServer;
import io.vertx.grpc.VertxServerBuilder;
import io.vertx.up.annotations.Agent;
import io.vertx.up.eon.em.ServerType;
import io.vertx.up.exception.RpcSslAlpnException;
import io.vertx.up.func.Fn;
import io.vertx.up.log.Annal;
import io.vertx.up.web.ZeroGrid;
import io.vertx.zero.eon.Values;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Internal Rpc Server, called IPC
 * Once you have defined another Agent, the default will be replaced.
 */
@Agent(type = ServerType.IPC)
public class ZeroRpcAgent extends AbstractVerticle {

    private static final Annal LOGGER = Annal.get(ZeroRpcAgent.class);
    private static final String KEY_JKS = "jks";
    private static final String KEY_PWD = "password";
    /** **/
    private static final ConcurrentMap<Integer, ServidorOptions> SERVERS =
            ZeroGrid.getRpcOptions();
    private static final ConcurrentMap<Integer, AtomicInteger>
            LOGS = new ConcurrentHashMap<Integer, AtomicInteger>() {
        {
            SERVERS.forEach((port, option) -> {
                put(port, new AtomicInteger(0));
            });
        }
    };

    @Override
    public void start() {
        /** 1. Iterate all the configuration **/
        Fn.itMap(SERVERS, (port, config) -> {
            /** 2.Rcp server builder initialized **/
            final VertxServerBuilder builder = VertxServerBuilder
                    .forAddress(this.vertx, config.getHost(), config.getPort());
            /**
             * 3.Must contains following config item:
             * ssl, alpn, jks, password
             * **/
            final JsonObject options = config.getOptions();

            Fn.flingUp(!options.containsKey(KEY_JKS) || !options.containsKey(KEY_PWD), LOGGER,
                    RpcSslAlpnException.class, getClass(), port, options);
            /**
             * 4.Must enabled ssl in agent to enable rpc.
             */
            final String jks = options.getString(KEY_JKS);
            final String password = options.getString(KEY_PWD);
            builder.useSsl(option -> option
                    .setSsl(true)
                    .setUseAlpn(true)
                    .setKeyStoreOptions(new JksOptions()
                            .setPath(jks)
                            .setPassword(password)));
            /**
             * 5.Service added.
             */
            {

            }
            /**
             * 6.Server added.
             */
            final VertxServer server = builder.build();
            server.start(handler -> registryServer(handler, config));
        });
    }

    /**
     * Registry the data into etcd
     *
     * @param handler
     * @param options
     */
    private void registryServer(final AsyncResult<Void> handler,
                                final ServidorOptions options) {
        final Integer port = options.getPort();
        final AtomicInteger out = LOGS.get(port);
        if (Values.ZERO == out.getAndIncrement()) {
            if (handler.succeeded()) {
                LOGGER.info(Info.RPC_LISTEN, options.getHost(), String.valueOf(options.getPort()));
            } else {
                LOGGER.info(Info.RPC_FAILURE, null == handler.cause() ? "None" : handler.cause().getMessage());
            }
        }
    }
}
