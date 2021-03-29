package view.tm;

import javafx.scene.control.Button;

public class CoachTM {
    private String cId;
    private String name;
    private String address;
    private String birthday;
    private String contact;
    private String qualification;
    private double salary;
    private Button btn;

    public CoachTM() {
    }

    public CoachTM(String cId, String name, String address, String birthday, String contact, String qualification, double salary, Button btn) {
        this.cId = cId;
        this.name = name;
        this.address = address;
        this.birthday = birthday;
        this.contact = contact;
        this.qualification = qualification;
        this.salary = salary;
        this.btn = btn;
    }

    public String getCId() {
        return cId;
    }

    public void setCId(String cId) {
        this.cId = cId;
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

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }
}