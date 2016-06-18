package com.app.android.popularmovie.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by SYSTEM on 2016/6/8.
 */
public class MovieFlavor  implements Parcelable{

    private String movie_Name ;//电影名称
    private String movie_Date; //电影上映时间
    private String movie_Synopsis;//电影概要
    private String movie_Poster; //电影画报
    private  String movie_Score; //电影评分

    public MovieFlavor(String movie_Name, String movie_Date, String movie_Synopsis,
                       String movie_Poster,String movie_Score)
    {
        this.movie_Name = movie_Name;
        this.movie_Date = movie_Date;
        this.movie_Synopsis = movie_Synopsis;
        this.movie_Poster = movie_Poster;
        this.movie_Score =movie_Score;
    }

    public MovieFlavor(Parcel in)
    {
        movie_Name = in.readString();
        movie_Date = in.readString();
        movie_Synopsis = in.readString();
        movie_Poster = in.readString();
       movie_Score = in.readString();
    }


    public String getMovie_Name() {
        return movie_Name;
    }

    public void setMovie_Name(String movie_Name) {
        this.movie_Name = movie_Name;
    }

    public String getMovie_Synopsis() {
        return movie_Synopsis;
    }

    public void setMovie_Synopsis(String movie_Synopsis) {
        this.movie_Synopsis = movie_Synopsis;
    }

    public String getMovie_Date() {
        return movie_Date;
    }

    public void setMovie_Date(String movie_Date) {
        this.movie_Date = movie_Date;
    }

    public String getMovie_Poster() {
        return movie_Poster;
    }

    public void setMovie_Poster(String movie_Poster) {
        this.movie_Poster = movie_Poster;
    }

    public String getMovie_Score() {
        return movie_Score;
    }

    public void setMovie_Score(String movie_Score) {
        this.movie_Score = movie_Score;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(movie_Name);
        dest.writeString(movie_Date);
        dest.writeString(movie_Synopsis);
        dest.writeString(movie_Poster);
        dest.writeString(movie_Score);
    }

    public static final Parcelable.Creator<MovieFlavor> CREATOR = new Parcelable.Creator<MovieFlavor>() {
        @Override
        public MovieFlavor createFromParcel(Parcel parcel) {
            return new MovieFlavor(parcel);
        }

        @Override
        public MovieFlavor[] newArray(int i) {
            return new MovieFlavor[i];
        }

    };
}
