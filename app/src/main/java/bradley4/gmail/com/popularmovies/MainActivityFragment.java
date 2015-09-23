package bradley4.gmail.com.popularmovies;

import android.app.Fragment;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import bradley4.gmail.com.popularmovies.business.FetchMovieTask;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public GridView mGridView;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        mGridView = (GridView) view.findViewById(R.id.gridview);
        fetchMovieTask(Constant.SORT_BY_POPULARITY);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        inflater.inflate(R.menu.moviefragment, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_by_rating) {
            fetchMovieTask(Constant.SORT_BY_RATING);

        }

        if (id == R.id.order_by_popular) {
            fetchMovieTask(Constant.SORT_BY_POPULARITY);
        }

        return super.onOptionsItemSelected(item);
    }


    public void fetchMovieTask(String sortBy) {

        ConnectivityManager cm =
                (ConnectivityManager)getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        if (isConnected) {
            FetchMovieTask movieTask = new FetchMovieTask(getActivity(), mGridView);
            movieTask.execute(sortBy);
        }else{
            Toast toast = Toast.makeText(getActivity(), Constant.PLEASE_CONNECT, Toast.LENGTH_LONG);
            toast.show();
        }
    }
}


