package paul.sp.labs.models;

public class ImageProxy extends BaseElement implements Picture, Visitee {
    private final String url;
    private Image realImage;

    public ImageProxy(String url) {
        this.url = url;
    }

    @Override
    public String getUrl() {
        return url;
    }

    public Image LoadImage() {
        if (realImage == null)
            realImage = new Image(url);
        return realImage;
    }


    @Override
    public BaseElement clone() {
        return null;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitImageProxy(this);
    }
}
