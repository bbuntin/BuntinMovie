package bradley4.gmail.com.popularmovies.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.net.URL;

import bradley4.gmail.com.popularmovies.R;
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
        return mThumbIds.length;
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
            imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }



        try {
            Bitmap mIcon_val;
            URL newurl = new URL("http://image.tmdb.org/t/p/w185//" + mMovieItem[position].getPoster_path());
            //http://www.image-ny.com/9700-large_default/dunk-high-prm-sh-send-help-2.jpg
            mIcon_val = BitmapFactory.decodeStream(newurl.openConnection().getInputStream());
            imageView.setImageBitmap(mIcon_val);
            //imageView.setImageResource(mIcon_val);

        } catch(Exception e){

        }

        return imageView;
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
