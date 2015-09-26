package bradley4.gmail.com.popularmovies.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import bradley4.gmail.com.popularmovies.R;
import bradley4.gmail.com.popularmovies.model.ReviewItem;
import bradley4.gmail.com.popularmovies.model.TrailerItem;

/**
 * Created by Bradley on 7/17/15.
 */
public class ReviewAdapter extends ArrayAdapter<ReviewItem> {
    private Context mContext;
    private ReviewItem[] mReviewItem;

    public ReviewAdapter(Context c, ReviewItem[] values) {
        super(c, -1, values);
        mContext = c;
        mReviewItem = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        TrailerHolder holder = null;

        if (row == null) {
            // if it's not recycled, initialize some attributes
            LayoutInflater inflater = ((Activity)mContext).getLayoutInflater();
            row = inflater.inflate(R.layout.list_item_review,parent,false);

            holder = new TrailerHolder();
            holder.txtAuthor = (TextView)row.findViewById(R.id.author_textview);
            holder.txtAuthor.setText(mReviewItem[position].getmAuthor());

            holder.txtContent = (TextView)row.findViewById(R.id.content_textview);
            holder.txtContent.setText(mReviewItem[position].getmContent());

            row.setTag(holder);
        } else {
            holder = (TrailerHolder)row.getTag();
            holder.txtAuthor.setText(mReviewItem[position].getmAuthor());
            holder.txtContent.setText(mReviewItem[position].getmContent());
        }

        return row;
    }

    static class TrailerHolder {
        TextView txtAuthor;
        TextView txtContent;
    }
}
