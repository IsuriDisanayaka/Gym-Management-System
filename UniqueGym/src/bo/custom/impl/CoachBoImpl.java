package bo.custom.impl;

import bo.custom.CoachBo;
import dao.DaoFactory;
import dao.QueryDAO;
import dao.custom.CoachDAO;
import dto.CoachDTO;
import entity.Coach;

import java.util.ArrayList;
import java.util.List;

public class CoachBoImpl implements CoachBo {
    private CoachDAO dao = DaoFactory.getInstance().getDao(DaoFactory.DAOType.COACH);
    QueryDAO qDao = DaoFactory.getInstance().getDao(DaoFactory.DAOType.QUERY);
    @Override
    public boolean saveCoach(CoachDTO dto) throws Exception {
        return dao.save(new Coach(dto.getCId(),dto.getName(),dto.getAddress(),dto.getBirthday(),dto.getContact(),dto.getQualification(),dto.getSalary()));
    }

    @Override
    public boolean updateCoach(CoachDTO dto) throws Exception {
        return dao.update(new Coach(dto.getCId(),
                dto.getName(),dto.getAddress(),dto.getBirthday(),dto.getContact(),dto.getQualification(),dto.getSalary()));
    }

    @Override
    public boolean deleteCoach(String cid) throws Exception {
        return dao.delete(cid);
    }

    @Override
    public CoachDTO getCoach(String cid) throws Exception {
        Coach c=dao.get(cid);
        return new CoachDTO(c.getCId(),c.getName(),c.getAddress(),c.getBirthday(),c.getContact(),c.getQualification(),c.getSalary());
    }

    @Override
    public ArrayList<CoachDTO> getAllCoaches() throws Exception {
        List<Coach> cList = dao.getAll();
        ArrayList<CoachDTO> dtoList = new ArrayList();
        for (Coach c : cList) {

            dtoList.add(new CoachDTO(c.getCId(),c.getName(),c.getAddress(),c.getBirthday(),c.getContact(),c.getQualification(),c.getSalary()));
        }
        return dtoList;
    }


    @Override
    public int getCoachCount() throws Exception {
        return qDao.getCoachCount();
    }
}
