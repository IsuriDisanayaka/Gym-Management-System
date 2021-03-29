package dao;

import entity.WorkOut;

import java.util.ArrayList;

public interface QueryDAO extends SuperDAO {
    public String getId() throws Exception;
    public ArrayList<String>getAllWorkOuts()throws Exception;
    public ArrayList<String>getAllMemebers()throws Exception;

    public int getCustomerCount() throws Exception;
    public int getCoachCount() throws Exception;
    public String getSupplementID() throws Exception;
    public boolean updateQTY(String id) throws Exception;
}
