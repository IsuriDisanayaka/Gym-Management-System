package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.ScheduleDAO;
import entity.Schedule;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ScheduleDAOImpl implements ScheduleDAO {
    @Override
    public boolean save(Schedule schedule) throws Exception {
        return CrudUtil.execute("INSERT INTO Schedule VALUES(?,?,?,?,?,?)",
                schedule.getSchId(),schedule.getmId(), schedule.getwId(), schedule.getwName(),
                schedule.getDate(), schedule.getSetRound());

}

    @Override
    public boolean update(Schedule schedule) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String schId) throws Exception {
        return CrudUtil.execute("DELETE FROM  Schedule WHERE  schId=?", schId);
    }

    @Override
    public Schedule get(String schId) throws Exception {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM Schedule WHERE schId=?", schId);
        if (resultSet.next()) {
            return new Schedule(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6));
        } else {
            return null;
        }
    }


    @Override
    public List<Schedule> getAll() throws Exception {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM Schedule ");
        ArrayList<Schedule> scheduleList = new ArrayList<>();
        while (resultSet.next()) {
            scheduleList.add(new Schedule(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6)

            ));
        }
        return scheduleList;
    }
}