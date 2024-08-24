package org.example.backend.bo.custom.impl;

import org.example.backend.bo.custom.OrderDetailsBO;
import org.example.backend.dao.DAOFactory;
import org.example.backend.dao.custom.OrderDetailDAO;
import org.example.backend.dto.OrderDetailDTO;
import org.example.backend.entity.OrderDetail;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailsBOImpl implements OrderDetailsBO {

    OrderDetailDAO orderDetailDAO = (OrderDetailDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER_DETAIL);

    @Override
    public boolean save(OrderDetailDTO dto) throws SQLException, ClassNotFoundException {
        return orderDetailDAO.save(new OrderDetail(dto.getOrder_id(), dto.getItem_id(), dto.getQty(), dto.getUnit_price(), dto.getTotal()));
    }

    @Override
    public ArrayList<OrderDetailDTO> searchByOrderId(String id) throws SQLException, ClassNotFoundException {
        ArrayList<OrderDetail> search = orderDetailDAO.searchByOrderId(id);
        ArrayList<OrderDetailDTO> orderDetailDTOS = new ArrayList<>();
        for (OrderDetail orderDetail : search) {
            orderDetailDTOS.add(new OrderDetailDTO(orderDetail.getOrder_id(), orderDetail.getItem_id(), orderDetail.getQty(), orderDetail.getUnit_price(), orderDetail.getTotal()));
        }
        return orderDetailDTOS;
    }
}
