package com.app.android.popularmovie.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by SYSTEM on 2016/6/13.
 */
public class User implements Parcelable {
    private String time;
    private String words;

    public User(String time, String words) {
        this.time = time;
        this.words = words;
    }

    public User(Parcel in)
    {
        time = in.readString();
        words = in.readString();

    }
    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel parcel) {
            return new User(parcel);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }

    };

        public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getWords() {
        return words;
    }

    public void setWords(String words) {
        this.words = words;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(time);
           dest.writeString(words);
    }
}
