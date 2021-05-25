package com.example.helloworld.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {

   @PrimaryKey(autoGenerate = true)
   private int id;

    @ColumnInfo (name = "gender_id")
    private String genderId;

   @ColumnInfo (name = "reminder_time")
    private int remindTime;

   @ColumnInfo (name = "miles_search")
    private int searchMiles;

   @ColumnInfo (name = "account")
    private boolean accountStatus;

   @ColumnInfo (name = "age_range_min")
    private int ageRangeMin;

   @ColumnInfo (name = "age_range_max")
    private int ageRangeMax;


    @NonNull
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGenderId() {
        return genderId; }

    public void setGenderId(String genderId) {
        this.genderId = genderId;
    }

    public int getRemindTime() {
        return remindTime;
    }

    public void setRemindTime(int remindTime) {
        this.remindTime = remindTime;
    }

    public int getSearchMiles() {
        return searchMiles;
    }

    public void setSearchMiles(int searchMiles) {
        this.searchMiles = searchMiles;
    }

    public boolean getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(boolean accountStatus) {
        this.accountStatus = accountStatus;
    }

    public int getAgeRangeMin() {
        return ageRangeMin;
    }

    public void setAgeRangeMin(int ageRangeMin) {
        this.ageRangeMin = ageRangeMin;
    }

    public int getAgeRangeMax() {
        return ageRangeMax;
    }

    public void setAgeRangeMax(int ageRangeMax) {
        this.ageRangeMax = ageRangeMax;
    }
}
