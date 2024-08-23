package org.example.backend.dao.custom.impl;


import org.example.backend.dao.SQLUtil;
import org.example.backend.dao.custom.OrderDAO;
import org.example.backend.entity.Order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDAOImpl implements OrderDAO {

    @Override
    public String nextId() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT id FROM orders ORDER BY id DESC LIMIT 1;");

        return rst.next() ? String.format("OID-%03d", (Integer.parseInt(rst.getString("id").replace("OID-", "")) + 1)) : "OID-001";
    }

    @Override
    public Order search(String newValue) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM orders WHERE id=?",newValue + "");

        rst.next();
        Order order = new Order(rst.getString("id"), rst.getString("date"), rst.getDouble("discount_value"), rst.getDouble("sub_total"), rst.getString("customer_iD"));

        return order;
    }

    @Override
    public ArrayList<Order> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM orders");

        ArrayList<Order> getAllOrders = new ArrayList<>();

        while (rst.next()) {
            Order order = new Order(rst.getString("id"), rst.getString("date"), rst.getDouble("discount_value"), rst.getDouble("sub_total"), rst.getString("customer_iD"));
            getAllOrders.add(order);
        }

        return getAllOrders;
    }

    @Override
    public boolean save(Order dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO orders VALUES (?,?,?,?,?)",dto.getId(),dto.getDate(),dto.getDiscount_value(),dto.getSub_total(),dto.getCustomer_id());
    }

    @Override
    public boolean update(Order dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean exist(String orderId ) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT id FROM orders WHERE id=?",orderId);
        return rst.next();

    }
}
