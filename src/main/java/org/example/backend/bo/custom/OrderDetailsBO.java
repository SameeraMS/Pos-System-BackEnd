package org.example.backend.bo.custom;

import org.example.backend.bo.SuperBO;
import org.example.backend.dto.OrderDetailDTO;
import org.example.backend.entity.OrderDetail;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderDetailsBO extends SuperBO {
    boolean save(OrderDetailDTO dto) throws SQLException, ClassNotFoundException;
    ArrayList<OrderDetailDTO> searchByOrderId(String id) throws SQLException, ClassNotFoundException;
}
