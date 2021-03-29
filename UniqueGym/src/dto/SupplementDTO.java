package dto;

public class SupplementDTO {
    private String sid;
    private String name;
    private double price;
    private String qty;
    private String mDate;
    private String exDate;

    public SupplementDTO() {
    }

    public SupplementDTO(String sid, String name, double price, String qty, String mDate, String exDate) {
        this.sid = sid;
        this.name = name;
        this.price = price;
        this.qty = qty;
        this.mDate = mDate;
        this.exDate = exDate;
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

    @Override
    public String toString() {
        return "SupplementDTO{" +
                "sid='" + sid + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", qty='" + qty + '\'' +
                ", mDate='" + mDate + '\'' +
                ", exDate='" + exDate + '\'' +
                '}';
    }
}
