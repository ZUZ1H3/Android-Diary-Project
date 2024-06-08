package com.example.androidprogramming;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class BookSearchListAdapter extends ArrayAdapter<BookResponse.Item> {

    public BookSearchListAdapter(Context context, List<BookResponse.Item> books) {
        super(context, 0, books);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BookResponse.Item book = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.book_search_list_item, parent, false);
        }

        ImageView bookImage = convertView.findViewById(R.id.book_image);
        TextView bookTitle = convertView.findViewById(R.id.book_title);
        TextView bookAuthor = convertView.findViewById(R.id.book_author);
        ImageButton addBtn = convertView.findViewById(R.id.button_add);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), BookReviewActivity.class);
                intent.putExtra("imageUrl", book.getImage());
                intent.putExtra("title", book.getTitle());
                intent.putExtra("author", book.getAuthor());
                getContext().startActivity(intent);
            }
        });

        bookTitle.setText(book.getTitle());
        bookAuthor.setText(book.getAuthor());
        Picasso.get().load(book.getImage()).into(bookImage);

        return convertView;
    }
}
