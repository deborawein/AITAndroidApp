package com.example.hiphooray.ui.database;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class HipRepository {

    private HipRoomDatabase db;

    private BirthdayContactDao birthdayContactDao;
    private LiveData<List<BirthdayContact>> allBirthdays;
    private LiveData<List<BirthdayContact>> upcomingBirthdays;
    private LiveData<List<BirthdayContact>> missedBirthdays;
    private LiveData<List<BirthdayContact>> todayBirthdays;



    private BirthdayContact birthdayContact;

    public HipRepository(Application application) {
        db = HipRoomDatabase.getDatabase(application);
        birthdayContactDao = db.birthdayContactDao();
        allBirthdays = birthdayContactDao.findAll();
        upcomingBirthdays = birthdayContactDao.findUpcoming();
        missedBirthdays = birthdayContactDao.findMissed();
        todayBirthdays = birthdayContactDao.findToday();
    }


    public void insert(BirthdayContact birthdayContact) {
       HipRoomDatabase.databaseWriteExecutor.execute(() -> birthdayContactDao.insert(birthdayContact));
   }


    public void update(BirthdayContact birthdayContact) {
        HipRoomDatabase.databaseWriteExecutor.execute(() -> birthdayContactDao.update(birthdayContact));
    }

    public void delete(BirthdayContact birthdayContact) {
       HipRoomDatabase.databaseWriteExecutor.execute(() -> birthdayContactDao.delete(birthdayContact));
    }

    public LiveData<List<BirthdayContact>> getAllBirthdays() {
        return allBirthdays;
    }

    public LiveData<List<BirthdayContact>> getUpcomingBirthdays() {
    return upcomingBirthdays;
    }

    public LiveData<List<BirthdayContact>> getMissedBirthdays() {

        return missedBirthdays;
    }

    public LiveData<List<BirthdayContact>> getTodayBirthdays() {


        return todayBirthdays;
    }

    public LiveData<Integer> getCountToday() {
        return birthdayContactDao.getCountToday();
    }

    public LiveData<Integer> getCountMissed() {
        return birthdayContactDao.getCountMissed();
    }

    public LiveData<Integer> getCountUpcoming() {
        return birthdayContactDao.getCountUpcoming();
    }

    public LiveData<Integer> getCountAll() {
        return birthdayContactDao.getCountAll();
    }

}

