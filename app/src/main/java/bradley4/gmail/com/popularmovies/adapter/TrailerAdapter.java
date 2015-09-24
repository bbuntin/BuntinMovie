package bradley4.gmail.com.popularmovies.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;

import bradley4.gmail.com.popularmovies.model.TrailerItem;

/**
 * Created by Bradley on 7/17/15.
 */
public class TrailerAdapter extends BaseAdapter {
    private Context mContext;
    private TrailerItem[] mTrailerItem;

    public TrailerAdapter(Context c, TrailerItem[] result) {
        mContext = c;
        mTrailerItem = result;
    }

    public int getCount() {
        return mTrailerItem.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout linearLayout;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            linearLayout = new LinearLayout(mContext);
            //imageView.setLayoutParams(new GridView.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 800));
            //imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        } else {
            linearLayout = (LinearLayout) convertView;
        }

        return linearLayout;
    }
}
