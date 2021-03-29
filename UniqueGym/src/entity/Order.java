package entity;

public class Order {
   private String orderId ;
  private String  mId ;
   private String date ;

    public Order() {
    }

    public Order(String orderId, String mId, String date) {
        this.orderId = orderId;
        this.mId = mId;
        this.date = date;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", mId='" + mId + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
