package bo.custom;


import dto.ScheduleDTO;

import java.util.ArrayList;

public interface ScheduleBo {
    public boolean saveSchedule(ScheduleDTO dto)throws Exception;
    public boolean updateSchedule(ScheduleDTO dto)throws Exception;
    public boolean deleteSchedule(String schId)throws Exception;
    public ScheduleDTO getSchedule(String schId)throws Exception;
    public ArrayList<ScheduleDTO> getAllSchedules()throws Exception;
    public ArrayList<String>getWorkoutsNames()throws Exception;
    public ArrayList<String>getMembersIdes()throws Exception;

}
