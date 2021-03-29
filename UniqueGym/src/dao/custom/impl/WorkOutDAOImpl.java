package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.WorkOutDAO;
import entity.Supplement;
import entity.WorkOut;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class WorkOutDAOImpl implements WorkOutDAO {
    @Override
    public boolean save(WorkOut workOut) throws Exception {
        return CrudUtil.execute("INSERT INTO WorkOut VALUES(?,?,?)",workOut.getWid(),workOut.getName(),workOut.getDescription());
    }

    @Override
    public boolean update(WorkOut workOut) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String wid) throws Exception {

        return CrudUtil.execute("DELETE FROM WorkOut WHERE wid=?",wid);
    }

    @Override
    public WorkOut get(String wid) throws Exception {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM WorkOut WHERE wid=?",wid);
        if (resultSet.next()) {
            return new WorkOut(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3));
        } else {
            return null;
        }
    }

    @Override
    public List<WorkOut> getAll() throws Exception {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM WorkOut ");
        ArrayList<WorkOut> workOutList = new ArrayList<>();
        while (resultSet.next()) {
            workOutList.add(new WorkOut(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3)


            ));
        }
        return workOutList;

    }
}
