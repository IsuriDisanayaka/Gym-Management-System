package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.SupplementDAO;
import entity.Supplement;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SupplementDAOImpl implements SupplementDAO {
    @Override
    public boolean save(Supplement supplement) throws Exception {
        return CrudUtil.execute("INSERT INTO  Supplement VALUES(?,?,?,?,?,?)",
supplement.getSid(),supplement.getName(),supplement.getPrice(),supplement.getQty(),supplement.getmDate(),supplement.getExDate());
}
    @Override
    public boolean update(Supplement supplement) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String Sid) throws Exception {
        return CrudUtil.execute("DELETE FROM Supplement WHERE Sid=?",Sid);
    }

    @Override
    public Supplement get(String Sid) throws Exception {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM Supplement WHERE Sid=?",Sid);
        if (resultSet.next()) {
            return new Supplement(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getDouble(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6));
        } else {
            return null;
        }
    }

    @Override
    public List<Supplement> getAll() throws Exception {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM Supplement ");
        ArrayList<Supplement> supplementList = new ArrayList<>();
        while (resultSet.next()) {
            supplementList.add(new Supplement(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getDouble(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6)

            ));
        }
        return supplementList;
    }
    }

