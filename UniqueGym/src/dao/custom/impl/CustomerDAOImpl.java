package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.CustomerDAO;
import entity.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public  class CustomerDAOImpl  implements CustomerDAO {
    @Override
    public boolean save(Customer customer) throws SQLException, ClassNotFoundException {
            return CrudUtil.execute("INSERT INTO Member VALUES (?,?,?,?,?,?,?,?,?,?)",
                    customer.getmId(), customer.getName(), customer.getAddress(), customer.getContact(), customer.getGender(), customer.getReport(),
                    customer.getcId(), customer.getCname(), customer.getPayment(), customer.getDate());
    }


    @Override
    public boolean update(Customer customer) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String mid) throws Exception {
        return CrudUtil.execute("DELETE FROM Member WHERE mid=?",mid);
    }

    @Override
    public Customer get(String mid) throws Exception {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM Member WHERE mid=?",mid);
        if (resultSet.next()) {
            return new Customer(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    resultSet.getString(8),
                    resultSet.getString(9),
                    resultSet.getString(10));
        } else {
            return null;
        }
    }


    @Override
    public List<Customer> getAll() throws Exception {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM Member");
        ArrayList<Customer> customerList = new ArrayList<>();
        while (resultSet.next()) {
           customerList .add(
                    new Customer(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5),
                            resultSet.getString(6),
                            resultSet.getString(7),
                            resultSet.getString(8),
                            resultSet.getString(9),
                            resultSet.getString(10))

            );
        }
        return customerList;
    }
    }

