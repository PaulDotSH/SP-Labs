package paul.sp.labs.commands;

public interface Command<Ret, Context> {
    Ret execute();

    default void setCommandContext(Context commandContext) {
    }

    Command<Ret, Context> getClone();
}
