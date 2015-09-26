package bradley4.gmail.com.popularmovies.business;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import bradley4.gmail.com.popularmovies.model.ReviewItem;

/**
 * Created by Bradley on 7/23/15.
 */



public class MovieReviewDBJsonParser {

    private static final String TAG_RESULTS = "results";
    private static final String TAG_ID = "id";
    private static final String TAG_AUTHOR = "author";
    private static final String TAG_CONTENT = "content";
    private static final String TAG_URL = "url";

    public static ReviewItem[] getParsedMovieReviews(String jsonStr) throws JSONException {
            JSONObject jsonObj = new JSONObject(jsonStr);
            JSONArray reviews = jsonObj.getJSONArray(TAG_RESULTS);
            ReviewItem[] reviewItems;
            reviewItems = new ReviewItem[reviews.length()];
            for (int i = 0; i < reviews.length(); i++){
                JSONObject m = reviews.getJSONObject(i);
                ReviewItem newItem = new ReviewItem();
                newItem.setmID(m.getString(TAG_ID));
                newItem.setmAuthor(m.getString(TAG_AUTHOR));
                newItem.setmContent(m.getString(TAG_CONTENT));
                newItem.setmURL(m.getString(TAG_URL));
                reviewItems[i] = newItem;
            }
            return reviewItems;
    }
}
