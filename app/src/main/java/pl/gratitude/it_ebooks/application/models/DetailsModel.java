package pl.gratitude.it_ebooks.application.models;

/**
 * Created 23.08.2015.
 *
 * @author Sławomir
 */
public class DetailsModel {

    private String Description;

    private String ISBN;

    private String Author;

    private String Error;

    private String Title;

    private String Publisher;

    private String Year;

    private String Time;

    private String Page;

    private String Image;

    private String ID;

    private String SubTitle;

    private String Download;

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String Author) {
        this.Author = Author;
    }

    public String getError() {
        return Error;
    }

    public void setError(String Error) {
        this.Error = Error;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getPublisher() {
        return Publisher;
    }

    public void setPublisher(String Publisher) {
        this.Publisher = Publisher;
    }

    public String getYear() {
        return Year;
    }

    public void setYear(String Year) {
        this.Year = Year;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String Time) {
        this.Time = Time;
    }

    public String getPage() {
        return Page;
    }

    public void setPage(String Page) {
        this.Page = Page;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getSubTitle() {
        return SubTitle;
    }

    public void setSubTitle(String SubTitle) {
        this.SubTitle = SubTitle;
    }

    public String getDownload() {
        return Download;
    }

    public void setDownload(String Download) {
        this.Download = Download;
    }

    @Override
    public String toString() {
        return "ClassPojo [Description = " + Description + ", ISBN = " + ISBN + ", Author = " + Author + ", Error = " + Error + ", Title = " + Title + ", Publisher = " + Publisher + ", Year = " + Year + ", Time = " + Time + ", Page = " + Page + ", Image = " + Image + ", ID = " + ID + ", SubTitle = " + SubTitle + ", Download = " + Download + "]";
    }
}
