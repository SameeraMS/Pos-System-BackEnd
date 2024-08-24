package org.example.backend.dao.custom;


import org.example.backend.dao.CrudDAO;
import org.example.backend.entity.Order;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderDAO extends CrudDAO<Order> {
    ArrayList<Order> searchByOrderId(String id) throws SQLException, ClassNotFoundException;
}
