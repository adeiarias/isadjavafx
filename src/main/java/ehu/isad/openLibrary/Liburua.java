package ehu.isad.openLibrary;

import java.util.Arrays;

public class Liburua {
    private String title;
    private String number_of_pages;
    private String[] publishers;

    @Override
    public String toString() {
        return "Liburua{" +
                "title='" + title + '\'' +
                ", number_of_pages='" + number_of_pages + '\'' +
                ", publishers=" + Arrays.toString(publishers) +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public String getNumber_of_pages() {
        return number_of_pages;
    }

    public String[] getPublishers() {
        return publishers;
    }
}
