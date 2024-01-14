package paul.sp.labs.models;

public interface Visitor<T> {
    T visitBook(Book book);

    T visitSection(Section section);

    T visitTable(Table table);

    T visitTableOfContents(TableOfContents toc);

    T visitParagraph(Paragraph paragraph);

    T visitImage(Image image);

    T visitImageProxy(ImageProxy imageProxy);

    T visitAuthor(Author author);
}
