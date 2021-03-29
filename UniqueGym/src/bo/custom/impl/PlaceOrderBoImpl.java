package bo.custom.impl;

import bo.BoFactory;
import bo.custom.PlaceOrderBo;
import dao.DaoFactory;
import dao.QueryDAO;
import dao.custom.SupplementDAO;
import dao.custom.SupplementSaleDAO;
import db.DBConnection;
import dto.SupplementDTO;
import dto.SupplementSaleDTO;
import entity.Supplement;
import entity.SupplementSale;

import java.sql.Connection;

public class PlaceOrderBoImpl implements PlaceOrderBo {
    SupplementDAO supplementDAO = DaoFactory.getInstance().getDao(DaoFactory.DAOType.SUPPLEMENT);
    SupplementSaleDAO supplementSaleDAO = DaoFactory.getInstance().getDao(DaoFactory.DAOType.SUPPLEMENTSALE);
    QueryDAO dao = DaoFactory.getInstance().getDao(DaoFactory.DAOType.QUERY);

    @Override
    public boolean updateSupplementSale(SupplementSaleDTO saleDTO, String id) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            SupplementSale supplementSale = new SupplementSale(saleDTO.getOrderId(), saleDTO.getsId(), saleDTO.getsNmae(),saleDTO.getQty(), saleDTO.getPrice(), saleDTO.getDate(),saleDTO.getCustID());
            if (supplementSaleDAO.save(supplementSale)) {
                if (dao.updateQTY(id)) {

                    connection.commit();
                    return true;
                } else {
                    connection.rollback();
                    return false;
                }
            } else {
                connection.rollback();
                return false;
            }
        } finally {
            connection.setAutoCommit(true);
        }
    }
}

