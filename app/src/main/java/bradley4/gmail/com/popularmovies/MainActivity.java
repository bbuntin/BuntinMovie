package bradley4.gmail.com.popularmovies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import bradley4.gmail.com.popularmovies.model.MovieItem;


public class MainActivity extends AppCompatActivity implements MainActivityFragment.OnFragmentInteractionListener {

    private static final String DETAILFRAGMENT_TAG = "DFTAG";
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (findViewById(R.id.detail_container) != null){
            mTwoPane = true;
            if (savedInstanceState == null){
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.detail_container, new MovieDetailFragment())
                        .commit();
            }
        }else{
            mTwoPane = false;
        }
    }

    @Override
    public void onResume(){
        super.onResume();
        //MainActivityFragment maf = (MainActivityFragment)getSupportFragmentManager().findFragmentById(R.id.main_fragment);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onVideoSelected(MovieItem movieItem, Boolean initialLoad) {
        if (mTwoPane){
            Bundle bundle = new Bundle();
            bundle.putSerializable(Constant.DETAIL_INTENT, movieItem);
            MovieDetailFragment fragment = new MovieDetailFragment();
            fragment.setArguments(bundle);

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.detail_container, fragment)
                    .commit();
        }else
        {
            if (!initialLoad) {
                Intent intent = new Intent(this, MovieDetail.class);
                intent.putExtra(Constant.DETAIL_INTENT, movieItem);
                this.startActivity(intent);
            }
        }
    }
}
