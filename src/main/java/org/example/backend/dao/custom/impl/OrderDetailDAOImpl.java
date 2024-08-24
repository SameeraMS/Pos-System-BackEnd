package org.example.backend.dao.custom.impl;



import org.example.backend.dao.SQLUtil;
import org.example.backend.dao.custom.OrderDetailDAO;
import org.example.backend.entity.OrderDetail;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class OrderDetailDAOImpl implements OrderDetailDAO {

    @Override
    public boolean save(OrderDetail dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO order_details VALUES (?,?,?,?,?)",dto.getOrder_id(),dto.getItem_id(),dto.getQty(),dto.getUnit_price(),dto.getTotal());
    }

    @Override
    public ArrayList<OrderDetail> searchByOrderId(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM order_details WHERE order_id=?",id);
        ArrayList<OrderDetail> orderDetails = new ArrayList<>();

        while (rst.next()) {
            orderDetails.add(new OrderDetail(rst.getString("order_id"), rst.getString("item_id"), rst.getInt("qty"), rst.getDouble("unit_price"), rst.getDouble("total")));
        }
        return orderDetails;
    }
}
