package bradley4.gmail.com.popularmovies;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

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
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        mGridView = (GridView) rootView.findViewById(R.id.gridview);

        fetchMovieTask(Constant.SORT_BY_POPULARITY);

        return rootView;
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
        FetchMovieTask movieTask = new FetchMovieTask(getActivity(), mGridView);
        movieTask.execute(sortBy);
    }
}


