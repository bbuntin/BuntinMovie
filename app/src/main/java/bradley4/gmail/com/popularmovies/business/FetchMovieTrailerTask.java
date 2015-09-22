package bradley4.gmail.com.popularmovies.business;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import bradley4.gmail.com.popularmovies.Constant;
import bradley4.gmail.com.popularmovies.MovieDetail;
import bradley4.gmail.com.popularmovies.adapter.ImageAdapter;
import bradley4.gmail.com.popularmovies.model.MovieItem;

public class FetchMovieTrailerTask extends AsyncTask<String, Void, MovieItem[]> {

    private final String LOG_TAG = FetchMovieTrailerTask.class.getSimpleName();
    public Context mContext;
    public GridView mGridView;
    public ProgressDialog mProgressDialog;


    public FetchMovieTrailerTask(Context context, GridView gridView){
        this.mContext = context;
        this.mGridView = gridView;
        mProgressDialog = new ProgressDialog(mContext);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mProgressDialog.setMessage(Constant.GETTING_MOVIES);
       // mProgressDialog.show(); //comment out for now. Only shows for a split second and looks odd

    }

    @Override
    protected MovieItem[] doInBackground(String... params) {
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

            final String MOVIE_BASE = "http://api.themoviedb.org/3/movie/%s";
            final String API_KEY = "api_key";
            final String API_KEY_VALUE = "460ad9e6a622c1e1ff1552540628b972";

            String sort_by = params[0];

            Uri builtUri = Uri.parse(MOVIE_BASE).buildUpon()
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
    protected void onPostExecute(final MovieItem[] result) {
        mGridView.setAdapter(new ImageAdapter(mContext, result));
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Intent intent = new Intent(mContext, MovieDetail.class);
                MovieItem movieItem = result[position];
                intent.putExtra(Constant.DETAIL_INTENT, movieItem);
                mContext.startActivity(intent);
            }
        });
        //mProgressDialog.dismiss();
    }
}