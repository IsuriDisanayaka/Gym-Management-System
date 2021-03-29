package view.tm;

import javafx.scene.control.Button;

public class SupplementTM {
    private String sid;
    private String name;
    private double price;
    private String qty;
    private String mDate;
    private String exDate;
    private Button btn;

    public SupplementTM() {
    }

    public SupplementTM(String sid, String name, double price, String qty, String mDate, String exDate, Button btn) {
        this.sid = sid;
        this.name = name;
        this.price = price;
        this.qty = qty;
        this.mDate = mDate;
        this.exDate = exDate;
        this.btn = btn;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getmDate() {
        return mDate;
    }

    public void setmDate(String mDate) {
        this.mDate = mDate;
    }

    public String getExDate() {
        return exDate;
    }

    public void setExDate(String exDate) {
        this.exDate = exDate;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }
}
