package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.CoachDAO;
import entity.Coach;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CoachDAOImpl implements CoachDAO {
    @Override
    public boolean save(Coach coach) throws Exception {
        return CrudUtil.execute
                ("INSERT INTO Coach VALUES(?,?,?,?,?,?,?)",
                        coach.getCId(),coach.getName(),coach.getAddress(),coach.getBirthday(),
                        coach.getContact(),coach.getQualification(),coach.getSalary());

    }

    @Override
    public boolean update(Coach coach) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String cid) throws Exception {
        return CrudUtil.execute("DELETE FROM Coach WHERE cid=?",cid);
    }

    @Override
    public Coach get(String cid) throws Exception {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM Coach WHERE cid=?",cid);
        if (resultSet.next()) {
            return new Coach(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getDouble(7));
        } else {
            return null;
        }
    }

    @Override
    public List<Coach> getAll() throws Exception {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM Coach");
        ArrayList<Coach> coachList = new ArrayList<>();
        while (resultSet.next()) {
            coachList.add(new Coach(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5),
                            resultSet.getString(6),
                            resultSet.getDouble(7))

            );
           //System.out.println("dao"+coachList.get(1));
        }
        return coachList;
    }
    }

