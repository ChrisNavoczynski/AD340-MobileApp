package com.example.helloworld;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.firebase.firestore.Exclude;
import com.google.firebase.firestore.IgnoreExtraProperties;
import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class Matches implements Parcelable {
    public String name;
    public String imageUrl;
    public boolean liked;
    public String lat;
    public String longitude;
    public String uid;

    public Matches() {
        //Default
    }

    public Matches(String mName, String imageUrl, boolean liked, String lat, String longitude) {
        this.name = mName;
        this.imageUrl = imageUrl;
        this.liked = liked;
        this.lat = lat;
        this.longitude = longitude;
    }

    public Matches(Parcel in) {
        name = in.readString();
        imageUrl = in.readString();
        liked = in.readByte() != 0;
        lat = in.readString();
        longitude = in.readString();
    }

    public static final Creator<Matches> CREATOR = new Creator<Matches>() {
        @Override
        public Matches createFromParcel(Parcel in) {
            return new Matches(in);
        }

        @Override
        public Matches[] newArray(int size) {
            return new Matches[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(name);
        parcel.writeByte((byte) (liked ? 1 : 0));
        parcel.writeString(imageUrl);
        parcel.writeString(lat);
        parcel.writeString(longitude);
    }
}
