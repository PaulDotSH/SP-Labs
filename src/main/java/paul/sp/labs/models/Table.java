package paul.sp.labs.models;


import jakarta.persistence.Entity;
import lombok.Getter;

@Entity
@jakarta.persistence.Table(name = "ContentTable")
public class Table extends BaseElement implements Visitee {

    @Getter
    private String title;

    public Table(String title) {
        this.title = title;
    }

    public Table() {
        title = "";
    }

    public Table(Table other) {
        this.title = other.title;
    }


    @Override
    public BaseElement clone() {
        return new Table(this);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitTable(this);
    }
}
