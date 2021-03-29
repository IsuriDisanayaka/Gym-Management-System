package dao;

import bo.custom.impl.PlaceOrderBoImpl;
import dao.custom.SupplementSaleDAO;
import dao.custom.impl.*;
import entity.Schedule;

public class DaoFactory {
    private static DaoFactory daoFactory;
    private  DaoFactory(){}

    public static DaoFactory getInstance(){
        return (daoFactory==null)?
                (daoFactory = new DaoFactory()):(daoFactory);

    }
    public enum DAOType{
        COACH,MEMBER,SUPPLEMENT,SUPPLEMENTSALE,SHEDULE,WORKOUT,QUERY
    }

    public<T extends SuperDAO >T getDao(DAOType type){
        switch (type){
            case COACH:
                return  (T) new CoachDAOImpl();
            case MEMBER:
                return (T) new CustomerDAOImpl ();
            case SUPPLEMENT:
                return (T) new SupplementDAOImpl();
            case SUPPLEMENTSALE:
                return (T)new SupplementSaleDAOImpl();
            case SHEDULE:
                return (T)new ScheduleDAOImpl();
            case WORKOUT:
                return (T) new WorkOutDAOImpl();
            case QUERY:
                return (T)new QueryDaoImpl();
            default:
                return null;
        }
    }
}
