package id.putraprima.skorbola;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;


public class Data implements Parcelable {
    private String homeText, awayText;
    private int homeScore, awayScore;
    private Uri homeUri, awayUri;
    private String homeScorer, awayScorer;

    public Data(){

    }

    public Data(String homeText, String awayText, Uri homeUri, Uri awayUri) {
        this.homeText = homeText;
        this.awayText = awayText;
        this.homeUri = homeUri;
        this.awayUri = awayUri;
    }

    protected Data(Parcel in) {
        homeText = in.readString();
        awayText = in.readString();
        homeScore = in.readInt();
        awayScore = in.readInt();
        homeUri = in.readParcelable(Uri.class.getClassLoader());
        awayUri = in.readParcelable(Uri.class.getClassLoader());
        homeScorer = in.readString();
        awayScorer = in.readString();
    }

    public static final Creator<Data> CREATOR = new Creator<Data>() {
        @Override
        public Data createFromParcel(Parcel in) {
            return new Data(in);
        }

        @Override
        public Data[] newArray(int size) {
            return new Data[size];
        }
    };

    public String getHomeText() {
        return homeText;
    }

    public void setHomeText(String homeText) {
        this.homeText = homeText;
    }

    public String getAwayText() {
        return awayText;
    }

    public void setAwayText(String awayText) {
        this.awayText = awayText;
    }

    public int getHomeScore() {
        return homeScore;
    }

    public void setHomeScore(int homeScore) {
        this.homeScore = homeScore;
    }

    public int getAwayScore() {
        return awayScore;
    }

    public void setAwayScore(int awayScore) {
        this.awayScore = awayScore;
    }

    public Uri getHomeUri() {
        return homeUri;
    }

    public void setHomeUri(Uri homeUri) {
        this.homeUri = homeUri;
    }

    public Uri getAwayUri() {
        return awayUri;
    }

    public void setAwayUri(Uri awayUri) {
        this.awayUri = awayUri;
    }

    public String getHomeScorer() {
        return homeScorer;
    }

    public void setHomeScorer(String homeScorer) {
        this.homeScorer = homeScorer;
    }

    public String getAwayScorer() {
        return awayScorer;
    }

    public void setAwayScorer(String awayScorer) {
        this.awayScorer = awayScorer;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(homeText);
        parcel.writeString(awayText);
        parcel.writeInt(homeScore);
        parcel.writeInt(awayScore);
        parcel.writeParcelable(homeUri, i);
        parcel.writeParcelable(awayUri, i);
        parcel.writeString(homeScorer);
        parcel.writeString(awayScorer);
    }
}
