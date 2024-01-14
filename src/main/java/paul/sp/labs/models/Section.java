package paul.sp.labs.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Section extends BaseElement implements Visitee {

    @OneToMany(targetEntity = BaseElement.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    protected List<BaseElement> elementList = new ArrayList<>();

    protected String title;

    public Section(Section other) {
        this.elementList = new ArrayList<>(other.elementList);
        this.title = other.title;
    }

    public Section() {
        title = "";
    }

    public Section(String title) {
        this.title = title;
    }

    @Override
    public void add(BaseElement e) {
        elementList.add(e);
    }

    @Override
    public void remove(BaseElement e) {
        elementList.remove(e);
    }

    @Override
    public BaseElement get(int index) {
        return elementList.get(index);
    }

    @Override
    public BaseElement clone() {
        return new Section(this);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitSection(this);
    }
}

