package org.example.backend.dao.custom;


import org.example.backend.dao.CrudDAO;

import org.example.backend.entity.Item;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemDAO extends CrudDAO<Item> {
    ArrayList<Item> searchByName(String newItemCode) throws SQLException, ClassNotFoundException;
}
