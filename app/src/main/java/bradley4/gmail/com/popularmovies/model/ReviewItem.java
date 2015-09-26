package bradley4.gmail.com.popularmovies.model;

import java.io.Serializable;

/**
 * Created by Bradley on 7/28/15.
 */
public class ReviewItem implements Serializable {
    private String mID;
    private String mAuthor;
    private String mContent;
    private String mURL;

    public String getmID() {
        return mID;
    }

    public void setmID(String mID) {
        this.mID = mID;
    }

    public String getmAuthor() {
        return mAuthor;
    }

    public void setmAuthor(String mAuthor) {
        this.mAuthor = mAuthor;
    }

    public String getmContent() {
        return mContent;
    }

    public void setmContent(String mContent) {
        this.mContent = mContent;
    }

    public String getmURL() {
        return mURL;
    }

    public void setmURL(String mURL) {
        this.mURL = mURL;
    }
}
