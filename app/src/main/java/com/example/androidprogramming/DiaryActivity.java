package com.example.androidprogramming;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class DiaryActivity extends AppCompatActivity {

    private TextView textViewDate;
    private ImageView imageViewWeather, imageViewMood, imageViewPhoto;
    private EditText editTextDiary;
    private ImageButton buttonBack, buttonSave, buttonAddPhoto;

    private int year, month, day;
    private String weather, mood;

    private Bitmap selectedImageBitmap;
    private static final int REQUEST_CODE_SELECT_IMAGE = 1;

    private DiaryDBHelper diaryDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);

        initializeViews();
        diaryDBHelper = new DiaryDBHelper(this);

        if (getIntent().getExtras() != null) {
            extractDataFromBundle(getIntent().getExtras());
            updateUI();
        }

        setButtonListeners();
    }

    private void initializeViews() {
        textViewDate = findViewById(R.id.textView_date);
        imageViewWeather = findViewById(R.id.ImageView_weather);
        imageViewMood = findViewById(R.id.ImageView_mood);
        editTextDiary = findViewById(R.id.editText_diary);
        buttonSave = findViewById(R.id.button_save);
        buttonBack = findViewById(R.id.button_back);
        imageViewPhoto = findViewById(R.id.imageView_photo);
        buttonAddPhoto = findViewById(R.id.button_add_photo);
    }

    private void extractDataFromBundle(Bundle bundle) {
        year = bundle.getInt("year");
        month = bundle.getInt("month");
        day = bundle.getInt("day");
        weather = bundle.getString("weather");
        mood = bundle.getString("mood");
        textViewDate.setText(year + "년 " + month + "월 " + day + "일");
    }

    private void updateUI() {
        setWeatherImage();
        setMoodImage();
        setBackgroundImage();
    }

    private void setWeatherImage() {
        imageViewWeather.setImageResource(getWeatherImageResource(weather));
    }

    private int getWeatherImageResource(String weather) {
        switch (weather) {
            case "sunny": return R.drawable.weather_sunny;
            case "rainy": return R.drawable.weather_rainy;
            case "cloudy": return R.drawable.weather_cloudy;
            case "windy": return R.drawable.weather_windy;
            case "lightning": return R.drawable.weather_lightning;
            case "snowy": return R.drawable.weather_snowy;
            case "rainbow": return R.drawable.weather_rainbow;
            default: return R.drawable.question;
        }
    }

    private void setMoodImage() {
        imageViewMood.setImageResource(getMoodImageResource(mood));
    }

    private int getMoodImageResource(String mood) {
        switch (mood) {
            case "happy": return R.drawable.mood_happy;
            case "sad": return R.drawable.mood_sad;
            case "angry": return R.drawable.mood_angry;
            case "lucky": return R.drawable.mood_lucky;
            case "soso": return R.drawable.mood_soso;
            case "flutter": return R.drawable.mood_flutter;
            case "tired": return R.drawable.mood_tired;
            case "comfortable": return R.drawable.mood_comfortable;
            case "exciting": return R.drawable.mood_exciting;
            default: return R.drawable.question;
        }
    }

    private void setBackgroundImage() {
        SharedPreferences sharedPreferences = getSharedPreferences("AppSettings", MODE_PRIVATE);
        int background = sharedPreferences.getInt("background", R.drawable.background1);
        View rootView = findViewById(android.R.id.content);
        rootView.setBackgroundResource(background);
    }

    private void setButtonListeners() {
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleSaveButtonClick();
            }
        });

        buttonAddPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openImageSelector();
            }
        });
    }

    private void openImageSelector() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQUEST_CODE_SELECT_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SELECT_IMAGE && resultCode == RESULT_OK && data != null) {
            Uri selectedImageUri = data.getData();
            displaySelectedImage(selectedImageUri);
        }
    }

    private void displaySelectedImage(Uri imageUri) {
        try {
            // 이미지를 로드할 때 BitmapFactory.Options 객체를 생성합니다.
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true; // 이미지를 메모리에 로드하지 않고 이미지의 크기 정보만 읽습니다.

            InputStream inputStream = getContentResolver().openInputStream(imageUri);
            BitmapFactory.decodeStream(inputStream, null, options);
            inputStream.close();

            // 이미지의 원본 해상도 정보를 얻습니다.
            int imageWidth = options.outWidth;
            int imageHeight = options.outHeight;

            // 원하는 해상도로 이미지를 스케일링하기 위한 샘플링 계수를 계산합니다.
            int scaleFactor = calculateScaleFactor(imageWidth, imageHeight);

            // BitmapFactory.Options 객체를 다시 설정하여 이미지를 실제로 로드합니다.
            options.inJustDecodeBounds = false;
            options.inSampleSize = scaleFactor;

            inputStream = getContentResolver().openInputStream(imageUri);
            selectedImageBitmap = BitmapFactory.decodeStream(inputStream, null, options);
            inputStream.close();

            // 이미지를 이미지뷰에 설정합니다.
            imageViewPhoto.setImageBitmap(selectedImageBitmap);
            imageViewPhoto.setVisibility(View.VISIBLE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int calculateScaleFactor(int imageWidth, int imageHeight) {
        final int targetWidth = 1024; // 원하는 해상도의 너비
        final int targetHeight = 768; // 원하는 해상도의 높이

        int scaleFactor = 1;
        if (imageWidth > targetWidth || imageHeight > targetHeight) {
            final int halfWidth = imageWidth / 2;
            final int halfHeight = imageHeight / 2;

            while ((halfWidth / scaleFactor) >= targetWidth
                    && (halfHeight / scaleFactor) >= targetHeight) {
                scaleFactor *= 2;
            }
        }
        return scaleFactor;
    }


    private void handleSaveButtonClick() {
        saveDiaryEntry();
        navigateBack();
    }

    private void saveDiaryEntry() {
        String content = editTextDiary.getText().toString();
        String date = getFormattedDate();
        saveDiaryToDatabase(date, weather, mood, content, selectedImageBitmap);
    }

    private String getFormattedDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day);
        return new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(calendar.getTime());
    }

    private void saveDiaryToDatabase(String date, String weather, String mood, String content, Bitmap image) {
        SQLiteDatabase db = diaryDBHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("date", date);
        values.put("weather", weather);
        values.put("mood", mood);
        values.put("content", content);

        if (image != null) {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            image.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();
            values.put("image", byteArray);
        }

        db.insert("diary", null, values);
        db.close();
    }

    private void navigateBack() {
        getSupportFragmentManager().beginTransaction().commit();
        finish();
    }
}
