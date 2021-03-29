package dto;

public class ScheduleDTO {
    private String schId;
    private String mId;
    private String wId;
    private String wName;
    private String date;

    public ScheduleDTO(String schId, String mId, String wId, String wName, String date, String setRound) {
        this.schId = schId;
        this.mId = mId;
        this.wId = wId;
        this.wName = wName;
        this.date = date;
        this.setRound = setRound;
    }

    private String  setRound;
;

    public ScheduleDTO() {
    }



    @Override
    public String toString() {
        return "ScheduleDTO{" +
                "schId='" + getSchId() + '\'' +
                ", mId='" + getmId() + '\'' +
                ", wId='" + getwId() + '\'' +
                ", wName='" + getwName() + '\'' +
                ", date='" + getDate() + '\'' +
                ", setRound='" + getSetRound() + '\'' +
                '}';
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
}
