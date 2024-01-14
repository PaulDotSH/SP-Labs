package paul.sp.labs.commands;

import paul.sp.labs.persistence.CrudRepository;
import paul.sp.labs.models.Pair;

public class UpdateOneCommand<T> implements Command<T, Pair<Long, T>> {
    private final CrudRepository<T, Long> repository;
    private Pair<Long, T> commandContext;

    public UpdateOneCommand(CrudRepository<T, Long> repository) {
        this.repository = repository;
    }

    private UpdateOneCommand(UpdateOneCommand<T> uoc) {
        this.repository = uoc.repository;
        this.commandContext = uoc.commandContext;
    }

    @Override
    public void setCommandContext(Pair<Long, T> o) {
        commandContext = o;
    }

    @Override
    public Command<T, Pair<Long, T>> getClone() {
        return new UpdateOneCommand<>(this);
    }

    @Override
    public T execute() {
        return repository.update(commandContext.first, commandContext.second);
    }
}
