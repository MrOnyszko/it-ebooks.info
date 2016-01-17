package pl.gratitude.it_ebooks.application.adapters;

import android.content.Context;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pl.gratitude.it_ebooks.R;
import pl.gratitude.it_ebooks.application.interfaces.OnClickDelegate;
import pl.gratitude.it_ebooks.application.models.Books;
import pl.gratitude.it_ebooks.common.ui.SquareImageView;

/**
 * Created 09.11.2015.
 *
 * @author Sławomir
 */
public class SearchedBooksRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final String TAG = SearchedBooksRecyclerAdapter.class.getSimpleName();

    public static final int TYPE_ITEM = 0;
    public static final int TYPE_FOOTER = 1;

    private Context mContext;
    private ArrayList<Books> books;
    private OnClickDelegate onClickDelegate;
    private String total;
    private boolean showProgressBar = false;
    private int additionalItems;

    public SearchedBooksRecyclerAdapter(Context context, OnClickDelegate onClickDelegate) {
        this.mContext = context;
        this.books = new ArrayList<>();
        this.onClickDelegate = onClickDelegate;
        this.additionalItems = 1;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_item, parent, false);
            return new ItemViewHolder(view);
        } else if (viewType == TYPE_FOOTER) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.progress_bar_footer, parent, false);
            return new FooterViewHolder(view);
        }
        throw new RuntimeException("there is no type that matches the type " + viewType + " + make sure your using types correctly");
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder) {
            ItemViewHolder item = (ItemViewHolder) holder;
            int index = position - 1;
            Books book = books.get(position);
            if (book != null) {

                Log.i(TAG, "[Total: " + total + "] [Parsed total: " + Integer.parseInt(total) + "] [Show progress: " + showProgressBar + "]");

                showProgressBar = books.size() != Integer.parseInt(total);

                Log.i(TAG, "[Show progress: " + showProgressBar + "]");

                Glide.with(mContext).load(book.getImage()).diskCacheStrategy(DiskCacheStrategy.ALL).crossFade().into(item.imageView);
                item.bookTitle.setText(book.getTitle());

            } else {
                Log.w(TAG, "Books: ", null);
            }
        } else if(holder instanceof FooterViewHolder) {
            FooterViewHolder footer = (FooterViewHolder) holder;
            if (showProgressBar) {
                footer.contentLoadingProgressBar.show();
                footer.contentLoadingProgressBar.setVisibility(View.VISIBLE);
            } else {
                footer.contentLoadingProgressBar.hide();
                footer.contentLoadingProgressBar.setVisibility(View.GONE);
            }
        }
        Log.d(TAG, "onBindViewHolder() called with: " + "holder = [" + holder + "], position = [" + position + "]");
    }

    @Override
    public int getItemCount() {
        return books == null ? 1 : books.size() + additionalItems;
    }

    @Override
    public int getItemViewType(int position) {
        if (isFooter(position)) {
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }
    }

    public boolean isItem(int position) {
        return position == TYPE_ITEM;
    }

    public boolean isFooter(int position) {
        return position == getItemCount() - 1;
    }

    public void setData(ArrayList<Books> books) {
        if (books != null) {
            this.books = books;
            notifyDataSetChanged();
        } else {
            Log.d(TAG, "setData() called with: " + "books = [" + null + "]");
        }
    }

    public void updateData(ArrayList<Books> books) {
        if (books != null) {
            this.books.addAll(books);
        } else {
            Log.d(TAG, "updateData() called with: " + "books = [" + null + "]");
        }
    }

    public ArrayList<Books> getBooks() {
        return books;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.cover_image)
        SquareImageView imageView;

        @Bind(R.id.book_title)
        TextView bookTitle;

        @Bind(R.id.card_view)
        CardView cardView;

        public ItemViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        @OnClick(R.id.card_view)
        void onClick() {
            onClickDelegate.onItemClick(getAdapterPosition());
        }

    }

    class FooterViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.loading)
        ContentLoadingProgressBar contentLoadingProgressBar;

        public FooterViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

    }

}
