package bradley4.gmail.com.popularmovies.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.squareup.picasso.Picasso;

import java.net.URL;

import bradley4.gmail.com.popularmovies.Constant;
import bradley4.gmail.com.popularmovies.model.MovieItem;

/**
 * Created by Bradley on 7/17/15.
 */
public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    private MovieItem[] mMovieItem;

    public ImageAdapter(Context c, MovieItem[] result) {
        mContext = c;
        mMovieItem = result;
    }

    public int getCount() {
        return mMovieItem.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 800));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        } else {
            imageView = (ImageView) convertView;
        }

        try {
            Bitmap mIcon_val;
            URL newurl = new URL(Constant.MOVIE_DOMAIN + mMovieItem[position].getPoster_path());
            Picasso.with(mContext).load(newurl.toString()).into(imageView);

        } catch(Exception e){

        }

        return imageView;
    }
}
