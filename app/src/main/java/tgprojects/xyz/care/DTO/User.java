package tgprojects.xyz.care.DTO;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by mvieck on 6/17/17.
 */

public class User implements Parcelable {
    private String userId;
    private String name;
    private String email;
    private String address;
    private String dob;
    private String phoneNum;

    public User() {
    }

    public User(String userId, String name, String email, String address, String dob, String phoneNum) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.address = address;
        this.dob = dob;
        this.phoneNum = phoneNum;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.userId);
        dest.writeString(this.name);
        dest.writeString(this.email);
        dest.writeString(this.address);
        dest.writeString(this.dob);
        dest.writeString(this.phoneNum);
    }

    protected User(Parcel in) {
        this.userId = in.readString();
        this.name = in.readString();
        this.email = in.readString();
        this.address = in.readString();
        this.dob = in.readString();
        this.phoneNum = in.readString();
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
