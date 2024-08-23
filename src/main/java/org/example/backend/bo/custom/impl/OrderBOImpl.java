package org.example.backend.bo.custom.impl;

import org.example.backend.bo.custom.OrderBO;
import org.example.backend.dao.DAOFactory;
import org.example.backend.dao.custom.OrderDAO;
import org.example.backend.dto.OrderDTO;
import org.example.backend.entity.Order;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderBOImpl implements OrderBO {

    OrderDAO orderDAO = (OrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER);

    @Override
    public ArrayList<OrderDTO> getAllOrders() throws SQLException, ClassNotFoundException {
        ArrayList<Order> allOrders = orderDAO.getAll();
        ArrayList<OrderDTO> orderDTOS = new ArrayList<>();

        for (Order order : allOrders) {
            orderDTOS.add(new OrderDTO(order.getId(), order.getDate(), order.getDiscount_value(), order.getSub_total(), order.getCustomer_id()));
        }

        return orderDTOS;
    }

    @Override
    public boolean existOrder(String id) throws SQLException, ClassNotFoundException {
        return orderDAO.exist(id);
    }

    @Override
    public boolean saveOrder(OrderDTO dto) throws SQLException, ClassNotFoundException {
        return orderDAO.save(new Order(dto.getId(), dto.getDate(), dto.getDiscount_value(), dto.getSub_total(), dto.getCustomer_id()));
    }

    @Override
    public boolean updateOrder(OrderDTO dto) throws SQLException, ClassNotFoundException {
        return orderDAO.update(new Order(dto.getId(), dto.getDate(), dto.getDiscount_value(), dto.getSub_total(), dto.getCustomer_id()));
    }

    @Override
    public boolean deleteOrder(String id) throws SQLException, ClassNotFoundException {
        return orderDAO.delete(id);
    }

    @Override
    public String nextOrderId() throws SQLException, ClassNotFoundException {
        return orderDAO.nextId();
    }

    @Override
    public OrderDTO search(String orderId) throws SQLException, ClassNotFoundException {
        Order search = orderDAO.search(orderId);
        return new OrderDTO(search.getId(), search.getDate(), search.getDiscount_value(), search.getSub_total(), search.getCustomer_id());
    }
}
