package com.example.android.booklisting;


public class BookInfo {

    /**
     * Title info
     */
    private String mTitle;

    /**
     * Author info
     */
    private String mAuthor;

    /**
     * Book description
     */
    private String mDescription;

    public BookInfo(String Title, String Author, String Description) {

        mTitle = Title;
        mAuthor = Author;
        mDescription = Description;
    }

    /**
     * get Title
     */
    public String getTitle() {
        return mTitle;
    }

    /**
     * get Author
     */
    public String getAuthor() {
        return mAuthor;
    }

    /**
     * get description
     */
    public String getDescription() {
        return mDescription;
    }

}
