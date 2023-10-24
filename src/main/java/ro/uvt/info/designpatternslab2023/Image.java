package ro.uvt.info.designpatternslab2023;


import java.util.concurrent.TimeUnit;

public class Image extends Element implements Picture {
    private String url;
    private String imageContent;

    public Image(String url) {
        this.url = url;
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    public Image(Image other){
        url = other.url;
    }

    public void print(){
        System.out.println("Image with name: " + url);
    }

    @Override
    public Element clone() {
        return new Image(this);
    }

    @Override
    public String getUrl() {
        return url;
    }
}
