package bo.custom;


import dto.SupplementSaleDTO;


import java.util.ArrayList;

public interface SupplementSaleBo {
    public boolean saveSupplementSale(SupplementSaleDTO dto) throws Exception;
    public boolean deleteSupplementSale(String orderId) throws Exception ;
    public SupplementSaleDTO getSupplementSale(String orderId) throws Exception ;
    public ArrayList<SupplementSaleDTO> getAllSupplementSale() throws Exception ;

}
