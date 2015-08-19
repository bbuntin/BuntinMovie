package bradley4.gmail.com.popularmovies.business;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.GridView;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import bradley4.gmail.com.popularmovies.adapter.ImageAdapter;
import bradley4.gmail.com.popularmovies.model.MovieItem;
import bradley4.gmail.com.popularmovies.R;

public class FetchMovieTask extends AsyncTask<Void, Void, MovieItem[]> {

    private final String LOG_TAG = FetchMovieTask.class.getSimpleName();
    Context mContext;
    GridView mGridView;


    public FetchMovieTask(Context context,GridView gridView){
        this.mContext = context;
        this.mGridView = gridView;
    }

    @Override
    protected MovieItem[] doInBackground(Void... params) {
        // These two need to be declared outside the try/catch
        // so that they can be closed in the finally block.
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        MovieItem[] movies = null;

        String movieJsonStr = null;

        try {
            // Construct the URL for the OpenWeatherMap query
            // Possible parameters are available at OWM's forecast API page, at
            // http://openweathermap.org/API#forecast

            final String MOVIE_BASE = "http://api.themoviedb.org/3/discover/movie?";
            final String SORT_BY = "sort_by";
            final String API_KEY = "api_key";
            final String API_KEY_VALUE = "460ad9e6a622c1e1ff1552540628b972";

            String sort_by = "popularity.desc";

            int numDays = 7;

            Uri builtUri = Uri.parse(MOVIE_BASE).buildUpon()
                    .appendQueryParameter(SORT_BY, sort_by)
                    .appendQueryParameter(API_KEY, API_KEY_VALUE)
            .build();

            URL url = new URL(builtUri.toString());
            Log.i(LOG_TAG,builtUri.toString());


            //Create the request to OpenWeatherMap, and open the connection
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();


            // Read the input stream into a String
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                // Nothing to do.
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                // But it does make debugging a *lot* easier if you print out the completed
                // buffer for debugging.
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0) {
                // Stream was empty.  No point in parsing.
                return null;
            }
            movieJsonStr = buffer.toString();
            try {
                movies = MovieDBJsonParser.getParsedMovies(movieJsonStr);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Log.v(LOG_TAG, movieJsonStr);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error ", e);
            // If the code didn't successfully get the weather data, there's no point in attempting
            // to parse it.
            movieJsonStr = null;
        } finally{
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e(LOG_TAG, "Error closing stream", e);
                }
            }
        }
        return movies;
    }

    @Override
    protected void onPostExecute(MovieItem[] result) {


        mGridView.setAdapter(new ImageAdapter(mContext, result));


    }

    // references to our images
    private Integer[] mThumbIds = {
            R.drawable.sample_2, R.drawable.sample_3,
            R.drawable.sample_4, R.drawable.sample_5,
            R.drawable.sample_6, R.drawable.sample_7,
            R.drawable.sample_0, R.drawable.sample_1,
            R.drawable.sample_2, R.drawable.sample_3,
            R.drawable.sample_4, R.drawable.sample_5,
            R.drawable.sample_6, R.drawable.sample_7,
            R.drawable.sample_0, R.drawable.sample_1,
            R.drawable.sample_2, R.drawable.sample_3,
            R.drawable.sample_4, R.drawable.sample_5,
            R.drawable.sample_6, R.drawable.sample_7
    };
}