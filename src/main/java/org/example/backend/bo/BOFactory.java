package org.example.backend.bo;


import org.example.backend.bo.custom.impl.CustomerBOImpl;
import org.example.backend.bo.custom.impl.ItemBOImpl;
import org.example.backend.bo.custom.impl.OrderBOImpl;
import org.example.backend.bo.custom.impl.OrderDetailsBOImpl;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {
    }

    public static BOFactory getBoFactory() {
        return (boFactory == null) ? boFactory = new BOFactory() : boFactory;
    }
    public enum BOTypes {
        CUSTOMER, ITEM, ORDER, ORDER_DETAILS
    }

    public SuperBO getBO(BOTypes boTypes){
        switch (boTypes){
            case CUSTOMER:
                return new CustomerBOImpl();
            case ITEM:
                return new ItemBOImpl();
            case ORDER:
                return new OrderBOImpl();
            case ORDER_DETAILS:
                return new OrderDetailsBOImpl();
            default:
                return null;
        }
    }
}
