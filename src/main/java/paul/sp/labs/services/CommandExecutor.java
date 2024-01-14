package paul.sp.labs.services;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import paul.sp.labs.commands.Command;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class CommandExecutor {
    private final ExecutorService executor;
    private final Map<String, Object> objectResults;
    private final Map<String, Object> iterableResults;

    public CommandExecutor() {
        int poolSize = 2; // Not gonna risk it
        objectResults = new HashMap<>();
        iterableResults = new HashMap<>();
        this.executor = Executors.newFixedThreadPool(poolSize);
    }

    public <T1, T2> T1 execute(Command<T1, T2> cmd) {
        return cmd.execute();
    }

    public <T1, T2> String executeAsync(Command<T1, T2> cmd) {
        String opId = UUID.randomUUID().toString();
        Command<T1, T2> deepClonedCmd = cmd.getClone();

        Runnable runnableTask = () -> {
            try {
                T1 result = deepClonedCmd.execute();

                if (result instanceof Iterable<?>) {
                    iterableResults.put(opId, result);
                } else {
                    objectResults.put(opId, result);
                }
            } catch (Exception ex) {
                ex.printStackTrace(); // Aici am putea da handle mai specific daca e cazul
            }
        };

        executor.submit(runnableTask);
        return opId;
    }


    public Object getAsyncResult(String opId) {
        if (objectResults.containsKey((opId))) {
            return objectResults.get(opId);
        } else {
            return iterableResults.get(opId);
        }
    }

}
