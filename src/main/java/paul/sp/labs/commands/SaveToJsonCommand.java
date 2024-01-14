package paul.sp.labs.commands;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import paul.sp.labs.services.JsonSerializer;
import paul.sp.labs.models.Book;
import paul.sp.labs.models.Visitee;

import java.util.List;


@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SaveToJsonCommand implements Command<String, Object> {
    private final JsonSerializer serializer;
    @Setter
    private Object commandContext;

    @Autowired
    public SaveToJsonCommand(JsonSerializer serializer) {
        this.serializer = serializer;
    }

    private SaveToJsonCommand(SaveToJsonCommand stjc) {
        this.serializer = stjc.serializer;
        this.commandContext = stjc.commandContext;
    }

    @Override
    public String execute() {
        if (commandContext instanceof List)
            return serializer.serialize((List<Visitee>) commandContext);
        return serializer.serialize((Book) commandContext);
    }

    @Override
    public Command<String, Object> getClone() {
        return new SaveToJsonCommand(this);
    }
}
