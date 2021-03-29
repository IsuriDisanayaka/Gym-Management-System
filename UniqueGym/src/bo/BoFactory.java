package bo;

import bo.custom.SupplementSaleBo;
import bo.custom.impl.*;
import dao.custom.impl.SupplementSaleDAOImpl;

public class BoFactory {
    public static BoFactory boFactory;
    private BoFactory() {
    }
    public static BoFactory getInstance() {
        return (boFactory == null) ?
                (boFactory = new BoFactory()) : (boFactory);
    }
    public enum BOType {
        COACH,MEMBER,SUPPLEMENT,SUPPLEMENTSALE,SHEDULE,WORKOUT,PLACEORDER
    }

    public <T > T getBo(BOType type) {
        switch (type){
            case COACH:
                return  (T) new CoachBoImpl();
            case MEMBER:
                return (T) new CustomerBoImpl();
            case SUPPLEMENT:
                return (T) new SupplementBoImpl();
            case SUPPLEMENTSALE:
                return (T)new SupplementSaleBoImpl();
            case SHEDULE:
                return (T)new ScheduleBoImpl();
            case WORKOUT:
                return (T)new WorkOutBoImpl();
            case PLACEORDER:
                return (T)new PlaceOrderBoImpl();
            default:
                return null;

    }
}}
