package bo.custom.impl;

import bo.custom.SupplementSaleBo;
import dao.DaoFactory;
import dao.custom.SupplementSaleDAO;
import dao.custom.WorkOutDAO;
import dto.CustomerDTO;
import dto.SupplementSaleDTO;
import entity.Customer;
import entity.SupplementSale;

import java.util.ArrayList;
import java.util.List;

public class SupplementSaleBoImpl implements SupplementSaleBo {
    private SupplementSaleDAO dao = DaoFactory.getInstance().getDao(DaoFactory.DAOType.SUPPLEMENTSALE);
    @Override
    public boolean saveSupplementSale(SupplementSaleDTO dto) throws Exception {
        return dao.save(new SupplementSale(dto.getOrderId(),dto.getsId(),dto.getsNmae(),dto.getQty(),dto.getPrice(),
                dto.getDate(),dto.getCustID()));
    }

    @Override
    public boolean deleteSupplementSale(String orderId) throws Exception {
        return false;
    }

    @Override
    public SupplementSaleDTO getSupplementSale(String orderId) throws Exception {
       SupplementSale s = dao.get(orderId);
        return new SupplementSaleDTO(s.getOrderId(),s.getsId(),s.getsNmae(),s.getQty(),s.getPrice(),s.getDate(),
                s.getCustID());
    }

    @Override
    public ArrayList<SupplementSaleDTO> getAllSupplementSale() throws Exception {
        List<SupplementSale> sList =dao.getAll();
        ArrayList<SupplementSaleDTO>dtoList =new ArrayList();
        for (SupplementSale s: sList){
            dtoList.add(new SupplementSaleDTO(s.getOrderId(),s.getsId(),s.getsNmae(),s.getQty(),s.getPrice(),s.getDate(),
                    s.getCustID()));
        }
        return dtoList;
    }

}
