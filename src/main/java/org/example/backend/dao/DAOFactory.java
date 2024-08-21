package org.example.backend.dao;

import org.example.backend.dao.custom.impl.CustomerDAOImpl;
import org.example.backend.dao.custom.impl.ItemDAOImpl;
import org.example.backend.dao.custom.impl.OrderDAOImpl;
import org.example.backend.dao.custom.impl.OrderDetailDAOImpl;

public class DAOFactory {

    private static DAOFactory daoFactory;

    private DAOFactory() {

    }

    public static DAOFactory getDaoFactory() {
        return daoFactory == null ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes {
        CUSTOMER, ITEM, ORDER, ORDER_DETAIL
    }
    public SuperDAO getDAO(DAOTypes daoTypes){
        switch (daoTypes){
            case CUSTOMER:
                return new CustomerDAOImpl();
            case ITEM:
                return new ItemDAOImpl();
            case ORDER:
                return new OrderDAOImpl();
            case ORDER_DETAIL:
                return new OrderDetailDAOImpl();
            default:
                return null;
        }
    }
}
