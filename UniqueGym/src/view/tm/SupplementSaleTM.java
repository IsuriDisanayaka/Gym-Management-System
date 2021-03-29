package view.tm;

public class SupplementSaleTM {
    private String orderId;
    private String sId;
    private String sNmae;
    private String qty;
    private double price;
    private String date;
    private String custID;


    public SupplementSaleTM() {
    }

    public SupplementSaleTM(String orderId, String sId, String sNmae, String qty, double price, String date, String custID) {
        this.orderId = orderId;
        this.sId = sId;
        this.sNmae = sNmae;
        this.qty = qty;
        this.price = price;
        this.date = date;
        this.custID = custID;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getsId() {
        return sId;
    }

    public void setsId(String sId) {
        this.sId = sId;
    }

    public String getsNmae() {
        return sNmae;
    }

    public void setsNmae(String sNmae) {
        this.sNmae = sNmae;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCustID() {
        return custID;
    }

    public void setCustID(String custID) {
        this.custID = custID;
    }

    @Override
    public String toString() {
        return "SupplementSaleTM{" +
                "orderId='" + orderId + '\'' +
                ", sId='" + sId + '\'' +
                ", sNmae='" + sNmae + '\'' +
                ", qty='" + qty + '\'' +
                ", price=" + price +
                ", date='" + date + '\'' +
                ", custID='" + custID + '\'' +
                '}';
    }
}