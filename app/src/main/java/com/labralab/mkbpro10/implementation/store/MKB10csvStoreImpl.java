package com.labralab.mkbpro10.implementation.store;

import android.content.ContentValues;
import android.content.Context;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.labralab.mkbpro10.R;
import com.labralab.mkbpro10.model.entity.Section;
import com.labralab.mkbpro10.model.store.MKB10Store;
import com.opencsv.CSVReader;

import io.reactivex.Single;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

public class MKB10csvStoreImpl implements MKB10Store {

    private final static String TABLE_NAME = "mcb10";

    private final static String ID = "name";
    private final static String CODE = "code";
    private final static String DESCRIPTION = "description";
    private final static String PARENT = "parent";

    private InputStream inputStream;

    private final Context context;

    private List<String[]> rows;

    private DBHelper dbHelper;

    SQLiteDatabase database;

    @Inject
    public MKB10csvStoreImpl(Context context) {
        this.context = context;
        dbHelper = new DBHelper(context);
        database = dbHelper.getWritableDatabase();
        inputStream = context.getResources().openRawResource(R.raw.mkb10);
    }

    @NotNull
    @Override
    public Single<List<Section>> getMKBList(final String parent) {
        return Single.fromCallable(new Callable<List<Section>>() {
            @Override
            public List<Section> call() throws Exception {
                List<Section> resultList = new ArrayList<>();
                resultList = getChildList(parent);

                if (resultList.isEmpty()) {
                    rows = parse();
                    saveToDb();
                    resultList = getChildList(parent);
                }

                return resultList;
            }
        });
    }

    private void saveToDb() {
        for (String[] row : rows) {
            ContentValues contentValues = new ContentValues();

            contentValues.put(ID, row[0]);
            contentValues.put(CODE, row[2]);
            contentValues.put(DESCRIPTION, row[3]);
            contentValues.put(PARENT, row[4]);

            database.insert(TABLE_NAME, null, contentValues);
        }
    }

    private List<Section> getChildList(String parent) {
        List<Section> resultList = new ArrayList<>();

        Cursor cursor = database.query(TABLE_NAME, null, PARENT + " = ?", new String[]{parent}, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                int codeID = cursor.getColumnIndex(CODE);
                int descriptionID = cursor.getColumnIndex(DESCRIPTION);
                int parentID = cursor.getColumnIndex(PARENT);
                int idID = cursor.getColumnIndex(ID);

                Section section = new Section(cursor.getString(idID), cursor.getString(codeID), cursor.getString(descriptionID), cursor.getString(parentID));
                resultList.add(section);
            } while (cursor.moveToNext());
            cursor.close();
        }

        database.close();

        return resultList;
    }

//    private List<Section> createList() {
//        List<Section> resultList = new ArrayList<>();
//        String parent = String.valueOf(currentPos + 1);
//
//        while (true) {
//            currentPos++;
//            currentRow = rows.get(currentPos);
//
//            Section section = createSection(currentRow);
//            Log.i("createList()", " : parent: " + parent + " cPos: " + currentPos + " : section created");
//
//            if (rows.size() > currentPos + 1 && !rows.get(currentPos + 1)[4].equals("") && Integer.parseInt(rows.get(currentPos + 1)[4]) > Integer.parseInt(parent)) {
//                Log.i("createList()", " : parent: " + parent + " cPos: " + currentPos + " : new list");
//                section.setList(createList());
//            }
//
//            resultList.add(section);
//            Log.i("createList()", " : parent: " + parent + " cPos: " + currentPos + " : section added");
//
//            if (isTheLastItem() || rows.get(currentPos + 1)[4].equals("") || Integer.parseInt(rows.get(currentPos + 1)[4]) < Integer.parseInt(parent)){
//                Log.i("createList()", " : parent: " + parent + " cPos: " + currentPos + " size: " + rows.size() +" : list returned");
//                return resultList;
//            }
//        }
//    }

//    private boolean isTheLastItem() {
//        return currentPos == rows.size() - 1;
//    }
//
//    private Section createSection(String[] row) {
//        return new Section(row[2], row[3], null);
//    }

    private List<String[]> parse() {
        List<String[]> resultList = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        CSVReader csvReader = new CSVReader(reader);
        try {
            resultList = csvReader.readAll();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return resultList;
    }

    class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context) {
            super(context, "DBName", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL("create table " + TABLE_NAME + "("
                    + ID + " integer, "
                    + CODE + " text, "
                    + DESCRIPTION + " text, "
                    + PARENT + " text" + ");");
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }
    }
}
