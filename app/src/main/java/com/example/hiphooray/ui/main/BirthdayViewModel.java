package com.example.hiphooray.ui.main;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.hiphooray.ui.database.BirthdayContact;
import com.example.hiphooray.ui.database.HipRepository;

import java.util.List;

public class BirthdayViewModel extends AndroidViewModel {

    private HipRepository hipRepository;
    private LiveData<List<BirthdayContact>> allBirthdays;
    private LiveData<List<BirthdayContact>> upcomingBirthdays;
    private LiveData<List<BirthdayContact>> missedBirthdays;
    private LiveData<List<BirthdayContact>> todayBirthdays;

    public BirthdayViewModel(@NonNull Application application) {
        super(application);
        hipRepository = new HipRepository(application);
        allBirthdays = hipRepository.getAllBirthdays();
        upcomingBirthdays = hipRepository.getUpcomingBirthdays();
        missedBirthdays = hipRepository.getMissedBirthdays();
        todayBirthdays = hipRepository.getTodayBirthdays();
    }
    public void insert(BirthdayContact birthdayContact){
        hipRepository.insert(birthdayContact);
    }

    public void update(BirthdayContact birthdayContact){
        hipRepository.update(birthdayContact);
    }
    public void delete(BirthdayContact birthdayContact){
        hipRepository.delete(birthdayContact);
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

    public LiveData<Integer> getCountToday(){
        return hipRepository.getCountToday();
    }

    public LiveData<Integer> getCountMissed(){
        return hipRepository.getCountMissed();
    }

    public LiveData<Integer> getCountUpcoming() {
        return hipRepository.getCountUpcoming();
    }

    public LiveData<Integer> getCountAll() {
        return hipRepository.getCountAll();
    }

}