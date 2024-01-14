package paul.sp.labs.commands;


import paul.sp.labs.persistence.CrudRepository;

public class AddOneCommand<T> implements Command<T, T> {
    private final CrudRepository<T, Long> repository;
    private T commandContext;

    public AddOneCommand(CrudRepository<T, Long> repository) {
        this.repository = repository;
    }

    private AddOneCommand(AddOneCommand<T> aoc) {
        this.repository = aoc.repository;
        this.commandContext = aoc.commandContext;
    }

    @Override
    public void setCommandContext(T o) {
        commandContext = o;
    }

    @Override
    public Command<T, T> getClone() {
        return new AddOneCommand<>(this);
    }

    @Override
    public T execute() {
        return repository.save(commandContext);
    }


}
