package bradley4.gmail.com.popularmovies.model;

import java.io.Serializable;

/**
 * Created by Bradley on 7/28/15.
 */
public class TrailerItem implements Serializable {
    private String mID;
    private String mIso_639_1;
    private String mKey;
    private String mName;
    private String mSite;
    private String mSize;
    private String mType;

    public String getmID() {
        return mID;
    }

    public void setmID(String mID) {
        this.mID = mID;
    }

    public String getmIso_639_1() {
        return mIso_639_1;
    }

    public void setmIso_639_1(String mIso_639_1) {
        this.mIso_639_1 = mIso_639_1;
    }

    public String getmKey() {
        return mKey;
    }

    public void setmKey(String mKey) {
        this.mKey = mKey;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmSite() {
        return mSite;
    }

    public void setmSite(String mSite) {
        this.mSite = mSite;
    }

    public String getmSize() {
        return mSize;
    }

    public void setmSize(String mSize) {
        this.mSize = mSize;
    }

    public String getmType() {
        return mType;
    }

    public void setmType(String mType) {
        this.mType = mType;
    }
}
