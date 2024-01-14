package paul.sp.labs.commands;

import paul.sp.labs.persistence.CrudRepository;

public class DeleteOneCommand<T> implements Command<Void, Long> {
    private final CrudRepository<T, Long> repository;
    private Long commandContext;

    public DeleteOneCommand(CrudRepository<T, Long> repository) {
        this.repository = repository;
    }

    private DeleteOneCommand(DeleteOneCommand<T> doc) {
        repository = doc.repository;
        commandContext = doc.commandContext;
    }

    @Override
    public void setCommandContext(Long o) {
        commandContext = o;
    }

    @Override
    public Command<Void, Long> getClone() {
        return new DeleteOneCommand<>(this);
    }

    @Override
    public Void execute() {
        repository.deleteById(commandContext);
        return null;
    }
}
