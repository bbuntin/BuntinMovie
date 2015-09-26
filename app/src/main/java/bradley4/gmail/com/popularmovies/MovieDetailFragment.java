package bradley4.gmail.com.popularmovies;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.net.MalformedURLException;
import java.net.URL;

import bradley4.gmail.com.popularmovies.business.FetchMovieReviewTask;
import bradley4.gmail.com.popularmovies.business.FetchMovieTrailerTask;
import bradley4.gmail.com.popularmovies.model.MovieItem;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MovieDetailFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MovieDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MovieDetailFragment extends Fragment {
    private MovieItem mMovieItem;
    private TextView mTitle;
    private TextView mDate;
    private TextView mRating;
    private TextView mOverview;
    private ImageView mPosterImage;
    public ListView mGridTrailerView;
    public ListView mGridReviewView;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MovieDetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MovieDetailFragment newInstance(String param1, String param2) {
        MovieDetailFragment fragment = new MovieDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public MovieDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        Intent intent = getActivity().getIntent();
        mMovieItem = (MovieItem) intent.getSerializableExtra(Constant.DETAIL_INTENT);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movie_detail, container, false);
        mGridTrailerView = (ListView) view.findViewById(R.id.trailers_listview);
        mGridReviewView = (ListView) view.findViewById(R.id.reviews_listview);

        mTitle = (TextView) view.findViewById(R.id.textTitle);
        mDate = (TextView) view.findViewById(R.id.textDate);
        mRating = (TextView) view.findViewById(R.id.textRating);
        mOverview = (TextView) view.findViewById(R.id.textOverview);
        mPosterImage = (ImageView) view.findViewById(R.id.imageViewPoster);

        mTitle.setText(mMovieItem.getTitle());
        try{
            String[] movieDate = mMovieItem.getRelease_date().split("-");
            double d = Double.parseDouble(movieDate[0]); //making sure date is numeric
            mDate.setText(movieDate[0]);
        }catch(Exception e){
            Log.e(Constant.LOG_TAG_NAME, e.getLocalizedMessage());
        }

        mRating.setText(String.format("%s/10 (%,d votes)", mMovieItem.getVote_average(), Integer.parseInt(mMovieItem.getVote_count())));
        mOverview.setText(mMovieItem.getOverview());
        try {
            URL newurl = new URL(Constant.MOVIE_DOMAIN + mMovieItem.getPoster_path());
            Picasso.with(getActivity()).load(newurl.toString()).into(mPosterImage);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        fetchMovieTrailerTask(mMovieItem.getID());
        fetchMovieReviewTask(mMovieItem.getID());

        return view;
    }

    public void fetchMovieTrailerTask(String movieID) {

        ConnectivityManager cm =
                (ConnectivityManager)getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        if (isConnected) {
            FetchMovieTrailerTask movieTask = new FetchMovieTrailerTask(getActivity(), mGridTrailerView);
            movieTask.execute(movieID);
        }else{
            Toast toast = Toast.makeText(getActivity(), Constant.PLEASE_CONNECT, Toast.LENGTH_LONG);
            toast.show();
        }
    }

    public void fetchMovieReviewTask(String movieID) {

        ConnectivityManager cm =
                (ConnectivityManager)getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        if (isConnected) {
            FetchMovieReviewTask movieTask = new FetchMovieReviewTask(getActivity(), mGridReviewView);
            movieTask.execute(movieID);
        }else{
            Toast toast = Toast.makeText(getActivity(), Constant.PLEASE_CONNECT, Toast.LENGTH_LONG);
            toast.show();
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
