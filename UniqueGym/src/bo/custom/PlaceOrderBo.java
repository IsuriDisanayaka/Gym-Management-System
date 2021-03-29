package bo.custom;


import dto.OrderDTO;
import dto.ScheduleDTO;
import dto.SupplementDTO;
import dto.SupplementSaleDTO;
import entity.Supplement;

public interface PlaceOrderBo {
    public boolean updateSupplementSale(SupplementSaleDTO saleDTO,String id)throws Exception;
    }


