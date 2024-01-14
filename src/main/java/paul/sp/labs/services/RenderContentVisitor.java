package paul.sp.labs.services;

import paul.sp.labs.models.*;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class RenderContentVisitor implements Visitor<Void> {

    @Override
    public Void visitBook(Book book) {
        System.out.println("Book: " + book.getTitle());
        System.out.println();

        System.out.println("Authors: ");
        for (Author author :
                book.getAuthorList()) {
            author.accept(this);
        }
        System.out.println();

        for (Visitee element :
                book.getElementList()) {
            element.accept(this);
        }
        return null;
    }

    @Override
    public Void visitSection(Section section) {
        System.out.println("Section: " + section.getTitle());
        section.getElementList().forEach(element -> element.accept(this));

        return null;
    }


    @Override
    public Void visitTableOfContents(TableOfContents toc) {
        System.out.println("TableOfContent");

        AtomicInteger pageCount = new AtomicInteger(1); // Ne trebuie pt lambda expression (stream)

        toc.getEntries().stream()
                .filter(Objects::nonNull)
                .forEach(entry -> {
                    System.out.println(entry + "__________" + pageCount.get());
                    pageCount.incrementAndGet();
                });

        return null;
    }


    @Override
    public Void visitParagraph(Paragraph paragraph) {
        System.out.println("Paragraph: " + paragraph.getText());
        return null;
    }

    @Override
    public Void visitImageProxy(ImageProxy imageProxy) {
        imageProxy.LoadImage().accept(this);
        return null;
    }

    @Override
    public Void visitImage(Image image) {
        System.out.println("Image with name: " + image.getName());
        return null;
    }

    @Override
    public Void visitTable(Table table) {
        System.out.println("Table with Title: " + table.getTitle());
        return null;
    }

    public Void visitAuthor(Author author) {
        System.out.println("Author: " + author.getName());
        return null;
    }
}
