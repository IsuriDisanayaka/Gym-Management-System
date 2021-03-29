package bo.custom.impl;

import bo.custom.CustomerBo;
import dao.DaoFactory;
import dao.QueryDAO;
import dao.custom.CustomerDAO;
import dao.custom.WorkOutDAO;
import dto.CustomerDTO;
import dto.WorkOutDTO;
import entity.Customer;
import entity.WorkOut;

import java.util.ArrayList;
import java.util.List;

public class CustomerBoImpl implements CustomerBo {
    private CustomerDAO dao = DaoFactory.getInstance().getDao(DaoFactory.DAOType.MEMBER);
    QueryDAO qDao = DaoFactory.getInstance().getDao(DaoFactory.DAOType.QUERY);


    @Override
    public int getCustomerCount() throws Exception {
        return qDao.getCustomerCount();
    }

    @Override
    public boolean saveCustomer(CustomerDTO dto) throws Exception {
        return dao.save(new Customer(dto.getmId(), dto.getName(), dto.getAddress(), dto.getContact(), dto.getGender(), dto.getReport(),
                dto.getcId(), dto.getCname(), dto.getPayment(), dto.getDate()));
    }

    @Override
    public boolean updateCustomer(CustomerDTO dto) throws Exception {
        return dao.update(new Customer(dto.getmId(),dto.getName(),dto.getAddress(),dto.getContact(),dto.getGender(),dto.getReport(),
                dto.getcId(),dto.getCname(),dto.getPayment(),dto.getDate()));
    }

    @Override
    public boolean deleteCustomer(String mid) throws Exception {
        return dao.delete(mid);
    }

    @Override
    public CustomerDTO getCustomer(String mid) throws Exception {
       Customer c = dao.get(mid);
        return new CustomerDTO(c.getmId(),c.getName(),c.getAddress(),c.getContact(),c.getGender(),c.getReport(),c.getcId(),c.getCname(),
                c.getPayment(),c.getDate());


    }

    @Override
    public ArrayList<CustomerDTO> getAllCustomers() throws Exception {
        List<Customer> cList =dao.getAll();
        ArrayList<CustomerDTO>dtoList =new ArrayList();
        for (Customer c: cList){
            dtoList.add(new CustomerDTO(c.getmId(),c.getName(),c.getAddress(),c.getContact(),c.getGender(),c.getReport(),c.getcId(),c.getCname(),
                    c.getPayment(),c.getDate()));
        }
        return dtoList;
    }
}
