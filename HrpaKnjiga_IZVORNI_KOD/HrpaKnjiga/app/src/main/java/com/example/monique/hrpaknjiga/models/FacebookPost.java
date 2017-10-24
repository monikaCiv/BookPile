package com.example.monique.hrpaknjiga.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.regex.Pattern;

public class FacebookPost implements Parcelable {

    private String mId;
    private String mImage;
    private String mDescription;
    private String mTitle;
    private String mAuthor;
    private String mDate;

    public FacebookPost() {
        this.mId = "Unknown";
        this.mImage = "Unknown";
        this.mDescription = "Unknown";
        this.mTitle = "Unknown";
        this.mAuthor = "Unknown";
        this.mDate = "Unknown";
    }

    public FacebookPost(String id, String image, String description, String title, String author, String date) {
        this.mId = id;
        this.mImage = image;
        this.mDescription = description;
        this.mTitle = title;
        this.mAuthor = author;
        this.mDate = date;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        this.mId = id;
    }

    public String getImage() {
        return mImage;
    }

    public void setImage(String image) {
        this.mImage = image;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        if(description.contains("[")) {
            String[] parts = description.split(Pattern.quote("["));
            this.mDescription = parts[0];
        } else {
            this.mDescription = "Opis ove knjige nije dostupan.";
        }
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        if(title.contains("]")) {
            String[] parts = title.split(Pattern.quote("]"),2);
            String string2 = parts[0];
            if(string2.contains("[")) {
                parts = string2.split(Pattern.quote("["),2);
                String string3 = parts[1];
                if(string3.contains("-")) {
                    parts = string3.split(Pattern.quote("-"),2);
                    this.mTitle = parts[1];
                    this.mTitle = this.mTitle.trim();
                } else {
                    this.mTitle = "Nepoznat naslov";
                }
            } else {
                this.mTitle = "Nepoznat naslov";
            }
        } else {
            this.mTitle = "Nepoznat naslov";
        }
    }

    public String getAuthor() {return mAuthor;}

    public void setAuthor(String author) {
        if(author.contains("]")) {
            String[] parts = author.split(Pattern.quote("]"),2);
            String string2 = parts[0];
            if(string2.contains("[")) {
                parts = string2.split(Pattern.quote("["),2);
                String string3 = parts[1];
                if(string3.contains("-")) {
                    parts = string3.split(Pattern.quote("-"),2);
                    this.mAuthor = parts[0];
                    this.mAuthor = this.mAuthor.trim();
                } else {
                    this.mAuthor = "Nepoznat naslov";
                }
            } else {
                this.mAuthor = "Nepoznat naslov";
            }
        } else {
            this.mAuthor = "Nepoznat naslov";
        }
    }

    public String getDate() {
        return mDate;
    }
    //Prikaži datum u korisnički prihvatljivom formatu
    public void setDate(String date) {
        if(date.contains("-")) {
            String[] parts = date.split(Pattern.quote("-"));
            String string1 = parts[0];
            String string2 = parts[1];
            String string3 = parts[2];
            if(string3.contains("T")) {
                parts = string3.split(Pattern.quote("T"));
                String string4 = parts[0];
                this.mDate = string4 + "." + string2 + "." + string1 + ".";
            } else {
                this.mDate = "Nepoznat datum.";
            }
        } else {
            this.mDate = "Nepoznat datum.";
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }


    //Za prosljeđivanje u INTENT
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mId);
        dest.writeString(mTitle);
        dest.writeString(mAuthor);
        dest.writeString(mDescription);
        dest.writeString(mImage);
        dest.writeString(mDate);
    }
    private FacebookPost(Parcel in) {
        mId = in.readString();
        mTitle = in.readString();
        mAuthor = in.readString();
        mDescription = in.readString();
        mImage = in.readString();
        mDate = in.readString();
    }
    public static final Creator<FacebookPost> CREATOR = new Creator<FacebookPost>() {
        @Override
        public FacebookPost createFromParcel(Parcel source) {
            return new FacebookPost(source);
        }

        @Override
        public FacebookPost[] newArray(int size) {
            return new FacebookPost[size];
        }
    };
}
