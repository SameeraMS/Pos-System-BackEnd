package org.example.backend.bo.custom.impl;

import org.example.backend.bo.custom.ItemBO;
import org.example.backend.dao.DAOFactory;
import org.example.backend.dao.custom.ItemDAO;
import org.example.backend.dto.ItemDTO;
import org.example.backend.entity.Item;

import java.sql.SQLException;
import java.util.ArrayList;

public class ItemBOImpl implements ItemBO {

    ItemDAO itemDAO = (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);

    @Override
    public ArrayList<ItemDTO> getAllItem() throws SQLException, ClassNotFoundException {
        ArrayList<Item> allitems = itemDAO.getAll();
        ArrayList<ItemDTO> itemDTOS = new ArrayList<>();

        for (Item item : allitems) {
            itemDTOS.add(new ItemDTO(item.getId(), item.getDescription(), item.getUnitPrice(), item.getQtyOnHand()));
        }

        return itemDTOS;
    }

    @Override
    public boolean existItem(String id) throws SQLException, ClassNotFoundException {
        return itemDAO.exist(id);
    }
    @Override
    public boolean saveItem(ItemDTO dto) throws SQLException, ClassNotFoundException {
        return itemDAO.save(new Item(dto.getId(), dto.getDescription(), dto.getUnitPrice(), dto.getQty()));
    }
    @Override
    public boolean updateItem(ItemDTO dto) throws SQLException, ClassNotFoundException {
        return itemDAO.update(new Item(dto.getId(), dto.getDescription(), dto.getUnitPrice(), dto.getQty()));
    }
    @Override
    public boolean deleteItem(String id) throws SQLException, ClassNotFoundException {
        return itemDAO.delete(id);
    }
    @Override
    public String nextItemId() throws SQLException, ClassNotFoundException {
        return itemDAO.nextId();
    }
}
