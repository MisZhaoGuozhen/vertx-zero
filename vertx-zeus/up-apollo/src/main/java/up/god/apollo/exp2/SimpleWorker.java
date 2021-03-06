package up.god.apollo.exp2;

import io.vertx.core.eventbus.Message;
import io.vertx.quiz.Params;
import io.vertx.up.annotations.Address;
import io.vertx.up.annotations.Queue;
import io.vertx.up.atom.Envelop;
import io.vertx.up.kidd.Rapider;

@Queue
public class SimpleWorker {

    @Address("EXP2://QUEUE/SAY-HELLO")
    public void sayHello(final Message<Envelop> message) {
        final String user = Rapider.getString(message, 0);
        final Integer limit = Rapider.getInt(message, 1);
        Params.start(getClass()).monitor(user).monitor(limit).end();
        message.reply(Envelop.success("Response Successfully"));
    }
}
