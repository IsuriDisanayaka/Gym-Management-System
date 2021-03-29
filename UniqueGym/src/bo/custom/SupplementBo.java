package bo.custom;

import dto.ScheduleDTO;
import dto.SupplementDTO;
import entity.Supplement;

import java.util.ArrayList;

public interface SupplementBo {
    public boolean saveSupplement(SupplementDTO dto) throws Exception;
    public boolean deleteSupplement(String Sid) throws Exception ;
    public SupplementDTO getSupplement(String Sid) throws Exception ;
    public ArrayList<SupplementDTO> getAllSupplement() throws Exception ;
    public boolean updateSupplement(SupplementDTO dto)throws Exception;
    public String getSupplementId() throws Exception;
}

