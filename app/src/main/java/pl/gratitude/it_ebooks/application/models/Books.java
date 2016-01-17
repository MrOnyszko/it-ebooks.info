package pl.gratitude.it_ebooks.application.models;

import java.io.Serializable;

/**
 * Created 22.08.2015.
 *
 * @author Sławomir
 */
public class Books implements Serializable {

    private String Description;

    private String isbn;

    private String Image;

    private String SubTitle;

    private String ID;

    private String Title;

    public Books() {}
    public Books(String description, String isbn, String image, String subTitle, String id, String title) {
        this.Description = description;
        this.isbn = isbn;
        this.Image = image;
        this.SubTitle = subTitle;
        this.ID = id;
        this.Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }

    public String getSubTitle() {
        return SubTitle;
    }

    public void setSubTitle(String SubTitle) {
        this.SubTitle = SubTitle;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    @Override
    public String toString() {
        return "ClassPojo [Description = " + Description + ", isbn = " + isbn + ", Image = " + Image + ", SubTitle = " + SubTitle + ", ID = " + ID + ", Title = " + Title + "]";
    }
}