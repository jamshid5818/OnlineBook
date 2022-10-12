package jama.bookApp.onlinebook.presentation.admin.home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.utils.widget.ImageFilterView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import jama.bookApp.onlinebook.R;
import jama.bookApp.onlinebook.data.model.PdfBooksModel;

public class HomeAdapter extends BaseAdapter {
    ArrayList<PdfBooksModel> bookList;
    private final Context context;

    public HomeAdapter(ArrayList<PdfBooksModel> bookList, Context context) {
        this.bookList = bookList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return bookList.size();
    }

    @Override
    public Object getItem(int i) {
        return bookList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @SuppressLint({"ViewHolder", "SetTextI18n"})
    @Override
    public View getView(int index, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_admin_home,viewGroup,false);
        ImageView imageFilterView = view.findViewById(R.id.imageBookItem);
        TextView nametxt = view.findViewById(R.id.book_name_admin);
        TextView authortxt = view.findViewById(R.id.authorNameAdmin);
        TextView amountSoldtxt = view.findViewById(R.id.amoundSold);
        PdfBooksModel book = (PdfBooksModel) getItem(index);
//        Glide.with(context).load(book.getImageBookUri()).into(imageFilterView);
        Glide.with(context).load(book.getImageBookUri()).placeholder(android.R.drawable.progress_indeterminate_horizontal).error(android.R.drawable.stat_notify_error).into(imageFilterView);

        nametxt.setText(book.getNameBook());
        authortxt.setText(book.getAuthorBook());
        amountSoldtxt.setText(String.valueOf(book.getAmountSold()));
        return view;
    }
}
