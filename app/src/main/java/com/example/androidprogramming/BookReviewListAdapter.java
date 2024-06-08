package com.example.androidprogramming;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.androidprogramming.Book;

import java.util.ArrayList;

public class BookReviewListAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Book> bookList;

    public BookReviewListAdapter(Context context, ArrayList<Book> bookList) {
        this.context = context;
        this.bookList = bookList;
    }

    @Override
    public int getCount() {
        return bookList.size();
    }

    @Override
    public Object getItem(int position) {
        return bookList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.book_review_list_item, parent, false);
            holder = new ViewHolder();
            holder.bookImage = convertView.findViewById(R.id.book_image);
            holder.bookTitle = convertView.findViewById(R.id.book_title);
            holder.bookAuthor = convertView.findViewById(R.id.book_author);
            holder.bookReview = convertView.findViewById(R.id.book_review);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Book book = bookList.get(position);
        holder.bookTitle.setText(book.getTitle());
        holder.bookAuthor.setText(book.getAuthor());
        holder.bookReview.setText(book.getReview());

        // 이미지 데이터를 설정합니다. 예시에서는 byte 배열을 Bitmap으로 변환한 후 설정합니다.
        byte[] imageByteArray = book.getImage();
        if (imageByteArray != null) {
            holder.bookImage.setImageBitmap(BitmapFactory.decodeByteArray(imageByteArray, 0, imageByteArray.length));
        } else {
            // 이미지가 없을 경우 기본 이미지를 설정할 수 있습니다.
            holder.bookImage.setImageResource(R.drawable.question);
        }

        return convertView;
    }

    static class ViewHolder {
        ImageView bookImage;
        TextView bookTitle;
        TextView bookAuthor;
        TextView bookReview;
    }
}
