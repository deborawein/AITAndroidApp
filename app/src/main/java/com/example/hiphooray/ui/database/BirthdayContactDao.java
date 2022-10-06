package com.example.hiphooray.ui.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface BirthdayContactDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(BirthdayContact birthdayContact);

    @Update
    void update(BirthdayContact birthdayContact);

    @Delete
    void delete(BirthdayContact birthdayContact);

    @Query("SELECT * FROM BIRTHDAY_CONTACT")
    LiveData<List<BirthdayContact>> findAll();

    @Query("SELECT COUNT(birthdate) FROM BIRTHDAY_CONTACT")
    LiveData<Integer> getCountAll();

    @Query("SELECT * FROM BIRTHDAY_CONTACT WHERE strftime('%m %d','now') < strftime('%m %d',birthdate)")
    LiveData<List<BirthdayContact>> findUpcoming();

    @Query("SELECT COUNT(birthdate) FROM BIRTHDAY_CONTACT WHERE strftime('%m %d','now') < strftime('%m %d',birthdate)")
    LiveData<Integer> getCountUpcoming();

    @Query("SELECT * FROM BIRTHDAY_CONTACT WHERE strftime('%m %d','now') > strftime('%m %d',birthdate)")
    LiveData<List<BirthdayContact>> findMissed();

    @Query("SELECT COUNT(birthdate) FROM BIRTHDAY_CONTACT WHERE strftime('%m %d','now') > strftime('%m %d',birthdate)")
    LiveData<Integer> getCountMissed();

    @Query("SELECT * FROM BIRTHDAY_CONTACT WHERE strftime('%m %d','now') = strftime('%m %d',birthdate)")
    LiveData<List<BirthdayContact>> findToday();

    @Query("SELECT COUNT(birthdate) FROM BIRTHDAY_CONTACT WHERE strftime('%m %d','now') = strftime('%m %d',birthdate)")
    LiveData<Integer> getCountToday();



}
