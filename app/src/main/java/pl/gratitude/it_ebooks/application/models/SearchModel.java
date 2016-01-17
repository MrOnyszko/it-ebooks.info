package pl.gratitude.it_ebooks.application.models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created 15.08.2015.
 *
 * @author Sławomir
 */
public class SearchModel implements Serializable {

    private String Time;

    private ArrayList<Books> Books;

    private String Page;

    private String Total;

    private String Error;

    public String getTime() {
        return Time;
    }

    public void setTime(String Time) {
        this.Time = Time;
    }

    public ArrayList<Books> getBooks() {
        return Books;
    }

    public void setBooks(ArrayList<Books> Books) {
        this.Books = Books;
    }

    public String getPage() {
        return Page;
    }

    public void setPage(String Page) {
        this.Page = Page;
    }

    public String getTotal() {
        return Total;
    }

    public void setTotal(String Total) {
        this.Total = Total;
    }

    public String getError() {
        return Error;
    }

    public void setError(String Error) {
        this.Error = Error;
    }

    @Override
    public String toString() {
        return "ClassPojo [\nTime = " + Time + ",\nBooks = " + Books + ",\nPage = " + Page + ",\nTotal = " + Total + ",\nError = " + Error + "\n]";
    }

}
