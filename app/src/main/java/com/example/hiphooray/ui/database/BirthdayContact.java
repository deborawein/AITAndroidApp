package com.example.hiphooray.ui.database;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@Entity(tableName = "BIRTHDAY_CONTACT")
public class BirthdayContact implements Serializable {


    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "ID")
    private int id;

    @ColumnInfo
    private String name;
    private String birthdate;
    private String email;
    private String phone;


    @Ignore
    public BirthdayContact() {
    }

    public BirthdayContact(String name, String birthdate, String email, String phone) {
        this.name = name;
        this.birthdate = birthdate;
        this.email = email;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthdate() {

        return birthdate;
    }

    public void setBirthdate(String birthdate) {

        //tranformar input dd/MM/yyyy para Date Type
        SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date inputDate = null;
        try {
            inputDate = inputFormat.parse(birthdate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //Melhor forma é solicitar que o usuario coloque a data no formato desejado pelo sistema ou faça campos diferenciados de input


        //transformar Date para string no formato do DAO yyyy-MM-dd
        SimpleDateFormat daoFormat = new SimpleDateFormat("yyyy-MM-dd");
        this.birthdate = daoFormat.format(inputDate);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String displayBirthdate(String birthdate) {

        //transformar data do DAO (yyyy-MM-dd) para Data type
        Date dbDate = null;
        SimpleDateFormat dbFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            dbDate = dbFormat.parse(birthdate);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        //transformar date para display string no formato MMM dd.
        SimpleDateFormat viewFormat = new SimpleDateFormat("dd MMM");
        return  viewFormat.format(dbDate);

    }

    public String displayCompletedBirthdate(String birthdate) {

        //transformar data do DAO (yyyy-MM-dd) para Data type
        Date dbDate = null;
        SimpleDateFormat dbFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            dbDate = dbFormat.parse(birthdate);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        //transformar date para display string no formato dd/MM/yyyy.
        SimpleDateFormat viewFormat = new SimpleDateFormat("dd/MM/yyyy");
        return  viewFormat.format(dbDate);

    }



    @NonNull
    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthdate='" + birthdate + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

}