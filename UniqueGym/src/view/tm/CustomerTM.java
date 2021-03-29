package view.tm;

import javafx.scene.control.Button;

public class CustomerTM {
    private String  mId;
    private String  name;
    private String address;
    private String contact;
    private String gender;
    private String  report;
    private String cId;
    private String cname;
    private String payment;
    private String  date;
    private Button btn;

    public CustomerTM() {
    }

    public CustomerTM(String mId, String name, String address, String contact, String gender, String report, String cId, String cname, String payment, String date, Button btn) {
        this.mId = mId;
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.gender = gender;
        this.report = report;
        this.cId = cId;
        this.cname = cname;
        this.payment = payment;
        this.date = date;
        this.btn = btn;
    }

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public String getcId() {
        return cId;
    }

    public void setcId(String cId) {
        this.cId = cId;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }

    @Override
    public String toString() {
        return "CustomerTM{" +
                "mId='" + mId + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", contact='" + contact + '\'' +
                ", gender='" + gender + '\'' +
                ", report='" + report + '\'' +
                ", cId='" + cId + '\'' +
                ", cname='" + cname + '\'' +
                ", payment='" + payment + '\'' +
                ", date='" + date + '\'' +
                ", btn=" + btn +
                '}';
    }
}
