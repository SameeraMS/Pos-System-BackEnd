package org.example.backend.bo.custom;

import org.example.backend.bo.SuperBO;
import org.example.backend.dto.ItemDTO;
import org.example.backend.dto.OrderDTO;
import org.example.backend.entity.Order;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderBO extends SuperBO {
    ArrayList<OrderDTO> getAllOrders() throws SQLException, ClassNotFoundException;
    boolean existOrder(String id) throws SQLException, ClassNotFoundException;
    boolean saveOrder(OrderDTO dto) throws SQLException, ClassNotFoundException;
    boolean updateOrder(OrderDTO dto) throws SQLException, ClassNotFoundException;
    boolean deleteOrder(String id) throws SQLException, ClassNotFoundException;
    String nextOrderId() throws SQLException, ClassNotFoundException;
    OrderDTO search(String orderId) throws SQLException, ClassNotFoundException;
    ArrayList<OrderDTO> searchByOrderId(String id) throws SQLException, ClassNotFoundException;
}
