package com.example.androidprogramming;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

// DBHelper.java
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DiaryDBHelper extends SQLiteOpenHelper {
    // 데이터베이스 버전과 이름을 정의합니다.
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "DiaryDB.db";

    public DiaryDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        // 일기 테이블을 생성합니다.
        String CREATE_DIARY_TABLE = "CREATE TABLE diary (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "date TEXT," +
                "weather TEXT," +
                "mood TEXT," +
                "content TEXT" + ")";
        db.execSQL(CREATE_DIARY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // 데이터베이스를 업그레이드할 때 기존 테이블을 삭제하고 새로 생성합니다.
        db.execSQL("DROP TABLE IF EXISTS diary");
        onCreate(db);
    }

    public ArrayList<Diary> getAllDiaries() {
        ArrayList<Diary> diaryList = new ArrayList<>();

        String selectQuery = "SELECT * FROM diary ORDER BY date DESC";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Diary diary = new Diary(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4)
                );
                diaryList.add(diary);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return diaryList;
    }

    public int updateDiary(Diary diary) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("date", diary.getDate());
        values.put("weather", diary.getWeather());
        values.put("mood", diary.getMood());
        values.put("content", diary.getContent());

        // id를 기준으로 일기를 업데이트합니다.
        return db.update("diary", values, "id = ?",
                new String[] { String.valueOf(diary.getId()) });
    }

    public void deleteDiary(Diary diary) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("diary", "id = ?",
                new String[] { String.valueOf(diary.getId()) });
        db.close();
    }


    public boolean hasDiaryOnDate(String date) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"id"}; // 'id' 컬럼만 가져옵니다.
        String selection = "date = ?"; // 'date' 컬럼을 기준으로 검색합니다.
        String[] selectionArgs = {date}; // 검색할 날짜 인자를 제공합니다.

        Cursor cursor = db.query("diary", columns, selection, selectionArgs, null, null, null);
        boolean hasEntry = cursor.getCount() > 0; // 결과가 하나 이상이면 해당 날짜에 일기가 있는 것입니다.
        cursor.close();
        db.close();
        return hasEntry;
    }

    public String getMoodOnDate(String date) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT mood FROM diary WHERE date = ?", new String[]{date});
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                String mood = cursor.getString(0);
                cursor.close();
                return mood;
            }
            cursor.close();
        }
        return null;
    }
}
