package paul.sp.labs.models;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import lombok.Getter;
import paul.sp.labs.services.ElementDeserializer;

@Getter
@JsonDeserialize(using = ElementDeserializer.class)
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class BaseElement implements Visitee {
    @Id
    @GeneratedValue
    protected Long id;

    public BaseElement() {
    }

    // O sa fie overridden, nu avem implementare de baza.
    public void add(BaseElement e) {
    }

    public void remove(BaseElement e) {
    }

    public BaseElement get(int index) {
        throw new UnsupportedOperationException("Not Implemented");
    }

    public BaseElement clone() {
        throw new UnsupportedOperationException("Not Implemented");
    }
}
