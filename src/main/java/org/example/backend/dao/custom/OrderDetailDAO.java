package org.example.backend.dao.custom;

import org.example.backend.dao.SuperDAO;
import org.example.backend.entity.OrderDetail;

import java.sql.SQLException;
import java.util.ArrayList;


public interface OrderDetailDAO extends SuperDAO {
    boolean save(OrderDetail dto) throws SQLException, ClassNotFoundException;
    ArrayList<OrderDetail> searchByOrderId(String id) throws SQLException, ClassNotFoundException;
}
