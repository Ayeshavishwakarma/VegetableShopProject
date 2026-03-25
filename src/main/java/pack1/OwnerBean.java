package pack1;

import java.io.Serializable;

public class OwnerBean implements Serializable {
    private String uName, upword, fname, lname, addr, mid, phno;

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getUpwd() {
        return upword;
    }

    public void setUpwd(String upword) {
        this.upword = upword;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getPhno() { // Maps to PHONE in the database
        return phno;
    }

    public void setPhno(String phno) {
        this.phno = phno;
    }
}