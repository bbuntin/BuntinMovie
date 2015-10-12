package bradley4.gmail.com.popularmovies.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import bradley4.gmail.com.popularmovies.R;
import bradley4.gmail.com.popularmovies.model.TrailerItem;

/**
 * Created by Bradley on 7/17/15.
 */
public class TrailerAdapter extends ArrayAdapter<TrailerItem> {
    private Context mContext;
    private TrailerItem[] mTrailerItem;

    public TrailerAdapter(Context c, TrailerItem[] values) {
        super(c, -1, values);
        mContext = c;
        mTrailerItem = values;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        TrailerHolder holder = null;

        if (row == null) {
            // if it's not recycled, initialize some attributes
            LayoutInflater inflater = ((Activity)mContext).getLayoutInflater();
            row = inflater.inflate(R.layout.list_item_trailer,parent,false);

            holder = new TrailerHolder();
            holder.txtTitle = (TextView)row.findViewById(R.id.trailer_title_textview);
            holder.txtTitle.setText(mTrailerItem[position].getmName());
            row.setTag(holder);
        } else {
            holder = (TrailerHolder)row.getTag();

            holder.txtTitle.setText(mTrailerItem[position].getmName());

        }

        return row;
    }

    static class TrailerHolder {
        TextView txtTitle;
    }
}
