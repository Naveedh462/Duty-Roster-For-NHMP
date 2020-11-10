package AdminOfficer;

public class Admin {
    private String First_Name;
    private String Type;
    private String Last_Name;
    private String Email_Address;
    private String mobileNo;
    private String CNIC;
    private String Address;
    private int admin_ID;

    public Admin() {
    }

    public Admin(String first_Name, String last_Name, String email_address, String type, String mobileno, String cnic, String address, int id) {
        First_Name = first_Name;
        Last_Name = last_Name;
        Email_Address = email_address;
        Type = type;
        mobileNo = mobileno;
        CNIC = cnic;
        Address = address;
        admin_ID = id;


    }

    //getters
    public String getFirst_Name() {
        return First_Name;
    }

    public String getLast_Name() {
        return Last_Name;
    }

    public String getEmail_address() {
        return Email_Address;
    }

    public String getType() {
        return Type;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public String getCNIC() {
        return CNIC;
    }

    public String getAddress() {
        return Address;
    }

    public int getAdmin_ID() {
        return admin_ID;
    }

    ;
    //setters

    public void setFirst_Name(String first_Name) {
        First_Name = first_Name;
    }

    public void setLast_Name(String last_Name) {
        Last_Name = last_Name;
    }

    public void setEmail_address(String email_address) {
        Email_Address = email_address;
    }

    public void setType(String type) {
        Type = type;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public void setCNIC(String CNIC) {
        this.CNIC = CNIC;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public void setAdmin_ID(int id) { admin_ID = id; }
}

