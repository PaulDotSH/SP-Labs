package paul.sp.labs.services;

import paul.sp.labs.models.*;

import java.util.List;
import java.util.stream.Collectors;


public class BookSaveVisitor implements Visitor<Void> {
    // We do not need other instances
    private final StringBuilder buildingJson = new StringBuilder();

    // According to the internet this is the way of clearing a StringBuffer in Java
    public void clearBuffer() {
        buildingJson.setLength(0);
    }

    @Override
    public Void visitBook(Book book) {
        String bookPropertiesTemplateJson = """
                    {
                        "title": "%s",
                        "class": "%s",
                        "authorList": [%s],
                        "elementList": [%s]
                    }
                """;

        // Process authorList
        String authorListJson = book.getAuthorList().stream()
                .map(author -> {
                    author.accept(this);
                    return "";
                })
                .collect(Collectors.joining(",\n"));

        // Process elementList
        String elementListJson = book.getElementList().stream()
                .map(element -> {
                    element.accept(this);
                    return "";
                })
                .collect(Collectors.joining(",\n"));

        // Build the final JSON
        buildingJson.append(String.format(bookPropertiesTemplateJson,
                book.getTitle(),
                Book.class,
                authorListJson,
                elementListJson));

        return null;
    }


    private void printChildren(List<BaseElement> elements) {
        for (BaseElement element : elements) {
            element.accept(this);
            if (elements.indexOf(element) < elements.size() - 1) {
                buildingJson.append(",");
            }
        }
    }

    @Override
    public Void visitSection(Section section) {
        String sectionJsonTemplate = """
                {
                    "title": "%s",
                    "class": "%s"%s
                """;

        String elementListJson = section.getElementList().stream()
                .map(element -> {
                    element.accept(this);
                    return "";
                })
                .collect(Collectors.joining(",\n"));

        String json = String.format(sectionJsonTemplate,
                section.getTitle(), Section.class,
                section.getElementList().isEmpty() ? "" : ",\n \"elementList\": [" + elementListJson + "]");

        buildingJson.append(json);
        buildingJson.append("}");
        return null;
    }


    @Override
    public Void visitTableOfContents(TableOfContents toc) {
        String entryTemplate = "\"%s\":\"%s\"";

        buildingJson.append("{");

        int pageCount = 1;
        boolean isFirstEntry = true;

        for (String entry : toc.getEntries()) {
            if (entry != null) {
                if (!isFirstEntry) {
                    buildingJson.append(",");
                } else {
                    isFirstEntry = false;
                }
                buildingJson.append(String.format(entryTemplate, entry, pageCount));
            } else {
                pageCount++;
            }
        }

        buildingJson.append("}");
        return null;
    }

    public String getJson() {
        return buildingJson.toString();
    }

    @Override
    public Void visitTable(Table table) {
        String tableJsonTemplate = """
                {
                    "title": "%s",
                    "class": "%s"
                }
                """;
        buildingJson.append(String.format(tableJsonTemplate, table.getTitle(), Table.class));
        return null;
    }

    @Override
    public Void visitImageProxy(ImageProxy imageProxy) {
        imageProxy.LoadImage().accept(this);
        return null;
    }

    @Override
    public Void visitImage(Image image) {
        String imageJsonTemplate = """
                {
                    "imageName": "%s",
                    "class": "%s"
                }
                """;
        buildingJson.append(String.format(imageJsonTemplate, image.getName(), Image.class));
        return null;
    }

    @Override
    public Void visitParagraph(Paragraph paragraph) {
        String paragraphJsonTemplate = """
                {
                    "text": "%s",
                    "class": "%s"
                }
                """;

        buildingJson.append(String.format(paragraphJsonTemplate, paragraph.getText(), Paragraph.class));
        return null;
    }

    public Void visitAuthor(Author author) {
        String authorJsonTemplate = """
                {
                    "authorList": "%s",
                    "class": "%s"
                }
                """;
        String json = String.format(authorJsonTemplate, author.getName(), Author.class);
        buildingJson.append(json);
        return null;
    }

}
