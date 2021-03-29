package bo.custom.impl;

import bo.custom.SupplementBo;
import dao.DaoFactory;
import dao.QueryDAO;
import dao.custom.SupplementDAO;
import dto.SupplementDTO;
import entity.Supplement;
import entity.WorkOut;


import java.util.ArrayList;
import java.util.List;

public class SupplementBoImpl implements SupplementBo {
    private SupplementDAO dao = DaoFactory.getInstance().getDao(DaoFactory.DAOType.SUPPLEMENT);
    QueryDAO qdao = DaoFactory.getInstance().getDao(DaoFactory.DAOType.QUERY);

    @Override
    public boolean saveSupplement(SupplementDTO dto) throws Exception {
        return dao.save(new Supplement(dto.getSid(), dto.getName(), dto.getPrice(),dto.getQty(), dto.getmDate(), dto.getExDate()));
    }



    @Override
    public boolean deleteSupplement(String Sid) throws Exception {
        return dao.delete(Sid);
    }

    @Override
    public SupplementDTO getSupplement(String Sid) throws Exception {
        Supplement s = dao.get(Sid);
        return new SupplementDTO(s.getSid(), s.getName(), s.getPrice(), s.getQty(),s.getmDate(), s.getExDate());
    }

    @Override
    public ArrayList<SupplementDTO> getAllSupplement() throws Exception {
        List<Supplement> sList =dao.getAll();
        ArrayList<SupplementDTO>dtoList =new ArrayList();
        for (Supplement s: sList){
            dtoList.add(new SupplementDTO(s.getSid(),s.getName(),s.getPrice(),s.getQty(),s.getmDate(),s.getExDate()));
        }
        return dtoList;
    }

    @Override
    public boolean updateSupplement(SupplementDTO dto) throws Exception {
        return dao.update(new Supplement(dto.getSid(),dto.getName(),dto.getPrice(),dto.getQty(),dto.getmDate(),dto.getExDate()));
    }

    @Override
    public String getSupplementId() throws Exception {
        return qdao.getSupplementID();
    }

}