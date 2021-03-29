package bo.custom;

import dto.WorkOutDTO;

import java.util.ArrayList;

public interface WorkOutBo {
    public boolean saveWorkOut(WorkOutDTO dto)throws Exception;
    public boolean updateWorkOut(WorkOutDTO dto)throws Exception;
    public boolean deleteWorkOut(String wid)throws Exception;
    public WorkOutDTO getWorkOut(String wid)throws Exception;
    public ArrayList<WorkOutDTO> getAllWorkOuts()throws Exception;
}
