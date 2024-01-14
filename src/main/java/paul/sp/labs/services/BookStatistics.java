package paul.sp.labs.services;

import lombok.Getter;
import paul.sp.labs.models.*;

@Getter
public class BookStatistics implements Visitor<Void> {
    private int ImagesCount;
    private int TablesCount;
    private int ParagraphsCount;

    @Override
    public Void visitBook(Book book) {
        for (Visitee element : book.getElementList()) {
            element.accept(this);
        }
        return null;
    }

    @Override
    public Void visitSection(Section section) {
        for (BaseElement element :
                section.getElementList()) {
            element.accept(this);
        }
        return null;
    }

    @Override
    public Void visitTableOfContents(TableOfContents toc) {
        return null;
    }

    @Override
    public Void visitParagraph(Paragraph paragraph) {
        ParagraphsCount++;
        return null;
    }

    @Override
    public Void visitImageProxy(ImageProxy imageProxy) {
        imageProxy.LoadImage().accept(this);
        return null;
    }

    @Override
    public Void visitImage(Image image) {
        ImagesCount++;
        return null;
    }

    @Override
    public Void visitTable(Table table) {
        TablesCount++;
        return null;
    }

    @Override
    public Void visitAuthor(Author author) {
        return null;
    }

    public String getStatistics() {
        String statisticsTemplate = """
                Book Statistics:
                - Images: %d
                - Paragraphs: %d
                - Tables: %d
                """;
        return String.format(statisticsTemplate, ImagesCount, ParagraphsCount, TablesCount);

    }
}
