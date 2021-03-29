package bo.custom.impl;

import bo.custom.ScheduleBo;
import dao.DaoFactory;
import dao.QueryDAO;
import dao.custom.ScheduleDAO;
import dao.custom.WorkOutDAO;
import dto.ScheduleDTO;
import dto.WorkOutDTO;
import entity.Schedule;
import entity.WorkOut;

import java.util.ArrayList;
import java.util.List;

public class ScheduleBoImpl implements ScheduleBo {

    private ScheduleDAO dao = DaoFactory.getInstance().getDao(DaoFactory.DAOType.SHEDULE);

    private QueryDAO qdao = DaoFactory.getInstance().getDao(DaoFactory.DAOType.QUERY);
    @Override
    public boolean saveSchedule(ScheduleDTO dto) throws Exception {
        return dao.save(new Schedule(dto.getSchId(),dto.getmId(),dto.getwId(),dto.getwName(),dto.getDate(),dto.getSetRound()));
    }

    @Override
    public boolean updateSchedule(ScheduleDTO dto) throws Exception {
        return dao.update(new Schedule(dto.getSchId(),dto.getmId(),dto.getwId(),dto.getwName(),dto.getDate(),dto.getSetRound()));
    }

    @Override
    public boolean deleteSchedule(String schId) throws Exception {
        return dao.delete(schId);
    }

    @Override
    public ScheduleDTO getSchedule(String schId) throws Exception {
       Schedule s  = dao.get(schId);
       return new ScheduleDTO(s.getSchId(),s.getmId(),s.getwId(),s.getwName(),s.getDate(),s.getSetRound());
    }

    @Override
    public ArrayList<ScheduleDTO> getAllSchedules() throws Exception {
        List<Schedule> sList =dao.getAll();
        ArrayList<ScheduleDTO>dtoList =new ArrayList();
        for (Schedule s: sList){
            dtoList.add(new ScheduleDTO(s.getSchId(),s.getmId(),s.getwId(),s.getwName(),s.getDate(),s.getSetRound()));
        }
        return dtoList;

    }

    @Override
    public ArrayList<String> getWorkoutsNames() throws Exception {
        return qdao.getAllWorkOuts();
    }

    @Override
    public ArrayList<String> getMembersIdes() throws Exception {
        return qdao.getAllMemebers();
    }
}
