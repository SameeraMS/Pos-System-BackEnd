package org.example.backend.dao.custom;


import org.example.backend.dao.CrudDAO;
import org.example.backend.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerDAO extends CrudDAO<Customer> {
    ArrayList<Customer> searchByContact(String newValue) throws SQLException, ClassNotFoundException;

}
