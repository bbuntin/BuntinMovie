package bradley4.gmail.com.popularmovies.business;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import bradley4.gmail.com.popularmovies.model.TrailerItem;

/**
 * Created by Bradley on 7/23/15.
 */



public class MovieTrailerDBJsonParser {

    private static final String TAG_RESULTS = "results";
    private static final String TAG_ID = "id";
    private static final String TAG_ISO_639_1 = "iso_639_1";
    private static final String TAG_KEY = "key";
    private static final String TAG_NAME = "name";
    private static final String TAG_SITE = "site";
    private static final String TAG_SIZE = "size";
    private static final String TAG_TYPE = "type";



    public static TrailerItem[] getParsedMovies(String jsonStr) throws JSONException {
            JSONObject jsonObj = new JSONObject(jsonStr);
            JSONArray vidoes = jsonObj.getJSONArray(TAG_RESULTS);
            TrailerItem[] trailerItems;
            trailerItems = new TrailerItem[vidoes.length()];
            for (int i = 0; i < vidoes.length(); i++){
                JSONObject m = vidoes.getJSONObject(i);
                TrailerItem newItem = new TrailerItem();
                newItem.setmID(m.getString(TAG_ID));
                newItem.setmIso_639_1(m.getString(TAG_ISO_639_1));
                newItem.setmKey(m.getString(TAG_KEY));
                newItem.setmName(m.getString(TAG_NAME));
                newItem.setmSite(m.getString(TAG_SITE));
                newItem.setmSize(m.getString(TAG_SIZE));
                newItem.setmType(m.getString(TAG_TYPE));
                trailerItems[i] = newItem;
            }
            return trailerItems;
    }
}
