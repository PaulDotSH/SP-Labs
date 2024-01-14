package paul.sp.labs.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Transient;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class TableOfContents extends BaseElement implements Visitee {

    @Transient
    private final List<String> entries;

    public TableOfContents() {
        entries = new ArrayList<>();
    }

    public TableOfContents(TableOfContents other) {
        entries = new ArrayList<>(other.entries);
    }

    @Override
    public BaseElement clone() {
        return new TableOfContents(this);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitTableOfContents(this);
    }


    // Add name if chapter, null otherwise
    public void addEntry(String entry) {
        entries.add(entry);
    }
}
