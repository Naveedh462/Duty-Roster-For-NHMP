package Model;

import androidx.annotation.Nullable;

import java.util.PriorityQueue;

public class Officers {
    private String uid;
    private String First_Name;
    private String Last_Name;
    private String Email_Address;
    private String mobileNo;
    private String CNIC;
    private String Address;
    private String Profile_Image;

    public Officers() {
    }

    public Officers(String profile_Image, String first_Name, String last_Name, String email_address) {
        Profile_Image = profile_Image;
        First_Name = first_Name;
        Last_Name = last_Name;
        Email_Address = email_address;

    }

    public String getProfile_Image() {
        return Profile_Image;
    }

    public String getFirst_Name() {
        return First_Name;
    }

    public String getLast_Name() {
        return Last_Name;
    }

    public String getEmail_address() {
        return Email_Address;
    }

    public String getUid() {
        return uid;
    }

    public void setProfile_Image(String profile_Image) {
        Profile_Image = profile_Image;
    }

    public void setFirst_Name(String first_Name) {
        First_Name = first_Name;
    }

    public void setLast_Name(String last_Name) {
        Last_Name = last_Name;
    }

    public void setEmail_address(String email_address) {
        Email_Address = email_address;
    }


    public void setUid(String uid) {
        this.uid = uid;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj instanceof Officers) {
            Officers officers = (Officers) obj;
            return this.uid.equals(officers.getUid());
        }
        return false;
    }
}
