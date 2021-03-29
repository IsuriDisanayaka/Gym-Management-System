package bo.custom;

import dto.CustomerDTO;

import java.util.ArrayList;

public interface CustomerBo {
    public int getCustomerCount() throws Exception;
    public boolean saveCustomer(CustomerDTO dto)throws Exception;
    public boolean updateCustomer(CustomerDTO dto)throws Exception;
    public boolean deleteCustomer(String mid)throws Exception;
    public CustomerDTO getCustomer(String mid)throws Exception;
    public ArrayList<CustomerDTO> getAllCustomers()throws Exception;
}
