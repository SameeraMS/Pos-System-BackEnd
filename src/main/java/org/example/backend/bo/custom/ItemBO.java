package org.example.backend.bo.custom;

import org.example.backend.bo.SuperBO;
import org.example.backend.dto.ItemDTO;
import org.example.backend.entity.Item;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemBO extends SuperBO {
    ArrayList<ItemDTO> getAllItem() throws SQLException, ClassNotFoundException;
    boolean existItem(String id) throws SQLException, ClassNotFoundException;
    boolean saveItem(ItemDTO dto) throws SQLException, ClassNotFoundException;
    boolean updateItem(ItemDTO dto) throws SQLException, ClassNotFoundException;
    boolean deleteItem(String id) throws SQLException, ClassNotFoundException;
    String nextItemId() throws SQLException, ClassNotFoundException;
    ArrayList<ItemDTO> searchByName(String newItemCode) throws SQLException, ClassNotFoundException;

}
