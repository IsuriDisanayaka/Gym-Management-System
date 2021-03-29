package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.SupplementSaleDAO;
import entity.SupplementSale;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SupplementSaleDAOImpl implements SupplementSaleDAO {
    @Override
    public boolean save(SupplementSale supplementSale) throws Exception {
        return CrudUtil.execute("INSERT INTO SupplementSale VALUES (?,?,?,?,?,?,?)",
                supplementSale.getOrderId(),
                supplementSale.getsId(),
                supplementSale.getsNmae(),
                supplementSale.getQty(),
                supplementSale.getPrice(),
                supplementSale.getDate(),
                supplementSale.getCustID());
    }

    @Override
    public boolean update(SupplementSale supplementSale) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String orderId ) throws Exception {
        return CrudUtil.execute("DELETE FROM Member WHERE orderId =?",orderId );
    }

    @Override
    public SupplementSale get(String orderId) throws Exception {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM SupplementSale WHERE orderId=?",orderId);
        if (resultSet.next()) {
            return new SupplementSale(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getDouble(5),
                    resultSet.getString(6),
                    resultSet.getString(7));
        } else {
            return null;
        }
    }

    @Override
    public List<SupplementSale> getAll() throws Exception {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM SupplementSale");
        ArrayList<SupplementSale> supplementList = new ArrayList<>();
        while (resultSet.next()) {
            supplementList .add(
                    new SupplementSale(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getDouble(5),
                            resultSet.getString(6),
                            resultSet.getString(7)));
        }
        return supplementList;
    }
}