package com.example.androidprogramming;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
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
        new ImageDownloaderTask(bookImage).execute(book.getImage());

        return convertView;
    }

    private static class ImageDownloaderTask extends AsyncTask<String, Void, Bitmap> {
        private ImageView imageView;

        public ImageDownloaderTask(ImageView imageView) {
            this.imageView = imageView;
        }

        @Override
        protected Bitmap doInBackground(String... urls) {
            String url = urls[0];
            Bitmap bitmap = null;
            try {
                InputStream input = new java.net.URL(url).openStream();
                bitmap = BitmapFactory.decodeStream(input);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            if (result != null) {
                imageView.setImageBitmap(result);
            } else {
                // 기본 이미지나 에러 이미지를 설정할 수 있습니다.
                imageView.setImageResource(R.drawable.question);
            }
        }
    }
}
