package org.example.backend.dao.custom.impl;

import org.example.backend.dao.SQLUtil;
import org.example.backend.dao.custom.ItemDAO;
import org.example.backend.entity.Item;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemDAOImpl implements ItemDAO {
    @Override
    public ArrayList<Item> getAll() throws SQLException, ClassNotFoundException {

        ResultSet rst = SQLUtil.execute("SELECT * FROM item");

        ArrayList<Item> getallItem = new ArrayList<>();

        while (rst.next()) {
            Item item = new Item(rst.getString("id"), rst.getString("description"), rst.getDouble("unit_price"), rst.getInt("qty"));
            getallItem.add(item);
        }

        return getallItem;
    }
    @Override
    public boolean delete(String code) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("DELETE FROM item WHERE id=?",code);
    }
    @Override
    public boolean save(Item item) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("INSERT INTO item (id, description, unit_price, qty) VALUES (?,?,?,?)",
                item.getId(),item.getDescription(),item.getUnitPrice(),item.getQtyOnHand());
    }
    @Override
    public boolean update(Item item) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("UPDATE item SET description=?, unit_price=?, qty=? WHERE id=?",
                item.getDescription(),item.getUnitPrice(),item.getQtyOnHand(),item.getId());
    }
    @Override
    public boolean exist(String code) throws SQLException, ClassNotFoundException {

        ResultSet set = SQLUtil.execute("SELECT id FROM item WHERE id=?",code);
        return set.next();

    }
    @Override
    public String nextId() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT id FROM item ORDER BY id DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("id");
            int newItemId = Integer.parseInt(id.replace("I00-", "")) + 1;
            return String.format("I00-%03d", newItemId);
        } else {
            return "I00-001";
        }
    }
    @Override
    public Item search(String newItemCode) throws SQLException, ClassNotFoundException {

        ResultSet rst = SQLUtil.execute("SELECT * FROM item WHERE code=?",newItemCode + "");
        rst.next();
        Item item = new Item(newItemCode + "", rst.getString("description"), rst.getDouble("unit_price"), rst.getInt("qty"));

        return item;
    }

    @Override
    public ArrayList<Item> searchByName(String newItemCode) throws SQLException, ClassNotFoundException {

        ResultSet rst = SQLUtil.execute("SELECT * FROM item WHERE description like ?",newItemCode+"%");

        ArrayList<Item> item = new ArrayList<>();

        while (rst.next()) {
            item.add(new Item(rst.getString("id"), rst.getString("description"), rst.getDouble("unit_price"), rst.getInt("qty")));
        }
        return item;
    }




}
