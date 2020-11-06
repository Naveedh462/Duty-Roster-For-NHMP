package Model;

import androidx.annotation.Nullable;

import java.util.PriorityQueue;

public class Officers {
    private String uid;
    private String Profile_Image;
    private String First_Name;
    private String Last_Name;
    private String Father_Name;
    private String Gender;
    private String officer_ID;
    private String Email_Address;
    private String mobileNo;
    private String CNIC;
    private String Address;


    public Officers() {
    }

    public Officers(String profile_Image, String first_Name, String last_Name,
                    String father_Name, String gender, String officer_ID,
                    String email_Address, String mobileNo, String CNIC, String address) {
        Profile_Image = profile_Image;
        First_Name = first_Name;
        Last_Name = last_Name;
        Father_Name = father_Name;
        Gender = gender;
        this.officer_ID = officer_ID;
        Email_Address = email_Address;
        this.mobileNo = mobileNo;
        this.CNIC = CNIC;
        Address = address;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getProfile_Image() {
        return Profile_Image;
    }

    public void setProfile_Image(String profile_Image) {
        Profile_Image = profile_Image;
    }

    public String getFirst_Name() {
        return First_Name;
    }

    public void setFirst_Name(String first_Name) {
        First_Name = first_Name;
    }

    public String getLast_Name() {
        return Last_Name;
    }

    public void setLast_Name(String last_Name) {
        Last_Name = last_Name;
    }

    public String getFather_Name() {
        return Father_Name;
    }

    public void setFather_Name(String father_Name) {
        Father_Name = father_Name;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getOfficer_ID() {
        return officer_ID;
    }

    public void setOfficer_ID(String officer_ID) {
        this.officer_ID = officer_ID;
    }

    public String getEmail_Address() {
        return Email_Address;
    }

    public void setEmail_Address(String email_Address) {
        Email_Address = email_Address;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getCNIC() {
        return CNIC;
    }

    public void setCNIC(String CNIC) {
        this.CNIC = CNIC;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
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
