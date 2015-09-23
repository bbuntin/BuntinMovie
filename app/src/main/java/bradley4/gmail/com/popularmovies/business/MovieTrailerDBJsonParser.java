package bradley4.gmail.com.popularmovies.business;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import bradley4.gmail.com.popularmovies.model.MovieItem;

/**
 * Created by Bradley on 7/23/15.
 */



public class MovieTrailerDBJsonParser {

    private static final String TAG_RESULTS = "results";
    private static final String TAG_ADULT = "adult";
    private static final String TAG_BACKDROP_PATH = "backdrop_path";
    private static final String TAG_GENRE_IDS = "genre_ids";
    private static final String TAG_ID = "id";
    private static final String TAG_ORIGINAL_LANGUAGE = "original_language";
    private static final String TAG_ORIGINAL_TITLE = "original_title";
    private static final String TAG_OVERVIEW = "overview";
    private static final String TAG_RELEASE_DATE = "release_date";
    private static final String TAG_POSTER_PATH = "poster_path";
    private static final String TAG_POPULARITY = "popularity";
    private static final String TAG_TITLE = "title";
    private static final String TAG_VIDEO = "video";
    private static final String TAG_VOTE_AVERAGE = "vote_average";
    private static final String TAG_VOTE_COUNT = "vote_count";



    public static MovieItem[] getParsedMovies(String jsonStr) throws JSONException {
            JSONObject jsonObj = new JSONObject(jsonStr);
            JSONArray movies = jsonObj.getJSONArray(TAG_RESULTS);
            MovieItem[] movieItems;
            movieItems = new MovieItem[movies.length()];
            for (int i = 0; i < movies.length(); i++){
                JSONObject m = movies.getJSONObject(i);
                MovieItem newItem = new MovieItem();
                newItem.setAdult(m.getString(TAG_ADULT));
                newItem.setBackdrop_path(m.getString(TAG_BACKDROP_PATH));
                newItem.setGenre_ids(m.getString(TAG_GENRE_IDS));
                newItem.setID(m.getString(TAG_ID));
                newItem.setOriginal_language(m.getString(TAG_ORIGINAL_LANGUAGE));
                newItem.setOriginal_title(m.getString(TAG_ORIGINAL_TITLE));
                newItem.setOverview(m.getString(TAG_OVERVIEW));
                newItem.setRelease_date(m.getString(TAG_RELEASE_DATE));
                newItem.setPoster_path(m.getString(TAG_POSTER_PATH));
                newItem.setPopularity(m.getString(TAG_POPULARITY));
                newItem.setTitle(m.getString(TAG_TITLE));
                newItem.setVideo(m.getString(TAG_VIDEO));
                newItem.setVote_average(m.getString(TAG_VOTE_AVERAGE));
                newItem.setVote_count(m.getString(TAG_VOTE_COUNT));
                movieItems[i] = newItem;
            }
            return movieItems;
    }
}
