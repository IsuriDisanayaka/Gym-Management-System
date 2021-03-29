package dao.custom.impl;

import dao.CrudUtil;
import dao.QueryDAO;
import entity.Schedule;
import entity.WorkOut;

import java.lang.reflect.Member;
import java.sql.ResultSet;
import java.util.ArrayList;


public class QueryDaoImpl implements QueryDAO {


    @Override
    public String getId() throws Exception {
        ResultSet set = CrudUtil.
                execute("SELECT cid FROM Coach ORDER BY id DESC LIMIT 1");
        String cid = "C001";
        if (set.next()) {
            String temp = set.getString(1);
            String[] cs = temp.split("C");
            int selectedId = Integer.parseInt(cs[1]);
            if (selectedId > 10) {
                cid = "C0" + (selectedId + 1);
            }
        }
        return cid;
    }


    @Override
    public ArrayList<String> getAllMemebers() throws Exception {
        ResultSet resultSet = CrudUtil.execute("SELECT name from Member");
        ArrayList<String> memberList = new ArrayList<>();
        while (resultSet.next()) {
            memberList.add(
                    resultSet.getString(1)
            );
        }

        return memberList;
    }

    @Override
    public int getCustomerCount() throws Exception {
        ResultSet set = CrudUtil.execute("SELECT COUNT(*) FROM member");
        int count = 0;
        while(set.next()){
            count = set.getInt(1);
        }
        return count;
    }

    @Override
    public int getCoachCount() throws Exception {
        ResultSet set = CrudUtil.execute("SELECT COUNT(*) FROM coach");
        int count = 0;
        while(set.next()){
            count = set.getInt(1);
        }
        return count;

    }

    @Override
    public String getSupplementID() throws Exception {
        ResultSet set = CrudUtil.
                execute("SELECT sid FROM supplement ORDER BY sid DESC LIMIT 1");
        String sid = "S001";
        if (set.next()) {
            String temp = set.getString(1);
            String[] cs = temp.split("S");
            int selectedId = Integer.parseInt(cs[1]);
            if (selectedId > 10) {
                sid = "S0" + (selectedId + 1);
            }
        }
        return sid;
    }

    @Override
    public boolean updateQTY(String id) throws Exception {
        ResultSet set = CrudUtil.
                execute("SELECT qty FROM supplement WHERE sID=?",id);
        int num=0;
        if (set.next()){
            num=set.getInt(1);
        }
        num--;
        return CrudUtil.execute("UPDATE Supplement SET qty=? WHERE sID=?",num,id);
    }

    @Override
    public ArrayList<String> getAllWorkOuts() throws Exception {
        ResultSet resultSet = CrudUtil.execute("SELECT name From WorkOut");
        ArrayList<String> workoutList = new ArrayList<>();
        while (resultSet.next()) {
            workoutList.add(
                    resultSet.getString(1)
            );
        }

        return workoutList;
    }




}
