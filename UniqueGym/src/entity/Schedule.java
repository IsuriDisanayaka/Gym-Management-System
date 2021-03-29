package entity;

import javafx.scene.control.Button;

public class Schedule implements SuperEntity {
    private String schId;
    private String mId;
    private String wId;
    private String wName;
    private String date;
    private String  setRound;


    public Schedule(String schId, String mId, String wId, String wName, String date, String setRound) {
        this.schId = schId;
        this.mId = mId;
        this.wId = wId;
        this.wName = wName;
        this.date = date;
        this.setRound = setRound;
    }

    public Schedule() {
    }

    public String getSchId() {
        return schId;
    }

    public void setSchId(String schId) {
        this.schId = schId;
    }

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public String getwId() {
        return wId;
    }

    public void setwId(String wId) {
        this.wId = wId;
    }

    public String getwName() {
        return wName;
    }

    public void setwName(String wName) {
        this.wName = wName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSetRound() {
        return setRound;
    }

    public void setSetRound(String setRound) {
        this.setRound = setRound;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "schId='" + schId + '\'' +
                ", mId='" + mId + '\'' +
                ", wId='" + wId + '\'' +
                ", wName='" + wName + '\'' +
                ", date='" + date + '\'' +
                ", setRound='" + setRound + '\'' +
                '}';
    }
}
