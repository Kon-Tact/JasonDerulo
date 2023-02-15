package parse.JSon;

public class ModelItem {

    private String imgUrl;
    private String author;
    private int likes;

    public ModelItem(String imgUrl, String author, int likes) {
        this.imgUrl = imgUrl;
        this.author = author;
        this.likes = likes;
    }

    public ModelItem() {
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getAuthor() {
        return author;
    }

    public int getLikes() {
        return likes;
    }

}
