package bo.custom;

import dto.CoachDTO;
import entity.Coach;

import java.util.ArrayList;

public interface CoachBo {
    public boolean saveCoach(CoachDTO dto)throws Exception;
    public boolean updateCoach(CoachDTO dto)throws Exception;
    public boolean deleteCoach(String cid)throws Exception;
    public CoachDTO getCoach(String cid)throws Exception;
    public ArrayList<CoachDTO> getAllCoaches()throws Exception;
    public int getCoachCount() throws Exception;
}
