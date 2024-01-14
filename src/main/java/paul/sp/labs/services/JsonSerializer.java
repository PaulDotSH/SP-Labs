package paul.sp.labs.services;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import paul.sp.labs.models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class JsonSerializer {
    private final BookSaveVisitor saveVisitor;

    public JsonSerializer() {
        saveVisitor = new BookSaveVisitor();
    }

    public String serialize(Visitee book) {
        saveVisitor.clearBuffer();
        book.accept(saveVisitor);
        return saveVisitor.getJson();
    }

    // Insane functional programming
    public String serialize(List<Visitee> books) {
        return "[" +
                books.stream()
                        .map(book -> {
                            saveVisitor.clearBuffer();
                            book.accept(saveVisitor);
                            return saveVisitor.getJson();
                        })
                        .collect(Collectors.joining(",")) +
                "]";
    }

    public BaseElement DeserializeBookRecursive(JsonNode node) {
        return internDeserializeBook(node, new Book());
    }

    private BaseElement internDeserializeBook(JsonNode node, Section section) {
        JsonNode elementListNode = node.get("elementList");
        if (elementListNode == null) {
            return deserializeBaseType(node);
        }

        List<BaseElement> tmpElementList = new ArrayList<>();

        elementListNode.elements().forEachRemaining(element -> {
            BaseElement elt = internDeserializeBook(element, new Section());
            tmpElementList.add(elt);
        });

        section.setTitle(node.get("title").asText());
        section.setElementList(tmpElementList);
        return section;
    }


    private BaseElement deserializeBaseType(JsonNode node) {
        String className = node.get("class").asText();

        return switch (className) {
            case "Image" -> createImage(node);
            case "Paragraph" -> createParagraph(node);
            case "Table" -> createTable(node);
            default -> null;
        };
    }

    private Image createImage(JsonNode node) {
        String imageName = node.get("imageName").asText();
        return new Image(imageName);
    }

    private Paragraph createParagraph(JsonNode node) {
        String text = node.get("text").asText();
        return new Paragraph(text);
    }

    private Table createTable(JsonNode node) {
        String title = node.get("title").asText();
        return new Table(title);
    }

}
