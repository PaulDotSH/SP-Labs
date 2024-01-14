package paul.sp.labs.services;

import lombok.Getter;
import paul.sp.labs.models.*;

public class TableOfContentUpdate implements Visitor<Void> {
    @Getter
    private final TableOfContents ToC;

    public TableOfContentUpdate() {
        ToC = new TableOfContents();
    }

    @Override
    public Void visitBook(Book book) {
        for (Visitee element :
                book.getElementList()) {
            element.accept(this);
        }
        return null;
    }

    @Override
    public Void visitSection(Section section) {
        ToC.addEntry(section.getTitle());
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
        ToC.addEntry(null);
        return null;
    }

    @Override
    public Void visitImageProxy(ImageProxy imageProxy) {
        imageProxy.LoadImage().accept(this);
        return null;
    }

    @Override
    public Void visitImage(Image image) {
        ToC.addEntry(null);
        return null;
    }

    @Override
    public Void visitTable(Table table) {
        ToC.addEntry(null);
        return null;
    }

    @Override
    public Void visitAuthor(Author author) {
        return null;
    }

}
