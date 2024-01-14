package paul.sp.labs.models;


import jakarta.persistence.Entity;
import lombok.Getter;

@Entity
public class Image extends BaseElement implements Visitee {

    @Getter
    private String name;

    public Image() {
        name = "";
    }

    public Image(String name) {
        this.name = name;
    }

    public Image(Image other) {
        name = other.name;
    }


    @Override
    public void add(BaseElement e) {
        throw new IllegalStateException("Cannot add an element");
    }

    @Override
    public void remove(BaseElement e) {
        throw new IllegalStateException("Cannot remove an element");
    }

    @Override
    public BaseElement get(int index) {
        throw new IllegalStateException("Cannot get an element");
    }

    @Override
    public BaseElement clone() {
        return new Image(this);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitImage(this);
    }
}
