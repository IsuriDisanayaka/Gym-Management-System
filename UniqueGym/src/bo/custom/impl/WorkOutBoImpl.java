package bo.custom.impl;

import bo.custom.WorkOutBo;
import dao.DaoFactory;
import dao.custom.WorkOutDAO;
import dto.SupplementDTO;
import dto.WorkOutDTO;
import entity.Supplement;
import entity.WorkOut;


import java.util.ArrayList;
import java.util.List;

public class WorkOutBoImpl implements WorkOutBo {
    private WorkOutDAO dao = DaoFactory.getInstance().getDao(DaoFactory.DAOType.WORKOUT);
    @Override
    public boolean saveWorkOut(WorkOutDTO dto) throws Exception {
        return dao.save(new WorkOut(dto.getWid(),dto.getName(),dto.getDescription()));
    }

    @Override
    public boolean updateWorkOut(WorkOutDTO dto) throws Exception {
        return dao.update(new WorkOut(dto.getWid(),dto.getName(),dto.getDescription()));
    }

    @Override
    public boolean deleteWorkOut(String wid) throws Exception {
        return dao.delete(wid);
    }

    @Override
    public WorkOutDTO getWorkOut(String wid) throws Exception {
        WorkOut w = dao.get(wid);
        return new WorkOutDTO(w.getWid(), w.getName(), w.getDescription());
    }



    @Override
    public ArrayList<WorkOutDTO> getAllWorkOuts() throws Exception {
        List<WorkOut> wList =dao.getAll();
        ArrayList<WorkOutDTO>dtoList =new ArrayList();
        for (WorkOut w: wList){
            dtoList.add(new WorkOutDTO(w.getWid(), w.getName(), w.getDescription()));
        }
        return dtoList;

    }
}
