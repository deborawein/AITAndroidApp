package com.example.hiphooray.ui.database;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Database(entities = {BirthdayContact.class}, version = 1, exportSchema = false)
public abstract class HipRoomDatabase extends RoomDatabase {

    public abstract BirthdayContactDao birthdayContactDao();

    private static volatile HipRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;

    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static HipRoomDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (HipRoomDatabase.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), HipRoomDatabase.class, "hip_database")
                            .addCallback(roomCallback)
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        //onCreate will be call only the first time the database is created.
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            //populateInitialData(INSTANCE);
            Log.i("XYZ", "onCreate Called");
        }
        //onOpen will be called every time the database is opened
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            Log.i("XYZ", "onOpen Called");
        }
    };

    private static void populateInitialData(HipRoomDatabase instance) {
        //we execute database operations in threads
        HipRoomDatabase.databaseWriteExecutor.execute( () -> {
            BirthdayContactDao birthdayContactDao = INSTANCE.birthdayContactDao();
            birthdayContactDao.insert(new BirthdayContact("John Preston", "1988-07-26", "johnpreston@gmail.com", "0415678987"));
            birthdayContactDao.insert(new BirthdayContact("Roy Fitzroy", "1983-03-21", "roy@gmail.com", "0415678000"));
            birthdayContactDao.insert(new BirthdayContact("Maria Weizmann", "1985-08-28", "maria@yahoo.com", "0415678555 "));
        });
    }



}
