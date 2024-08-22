package org.example.backend.bo.custom;

import org.example.backend.bo.SuperBO;
import org.example.backend.dto.CustomerDTO;
import org.example.backend.entity.Customer;


import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBO extends SuperBO {
    ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException;
    boolean existCustomer(String id) throws SQLException, ClassNotFoundException;
    boolean saveCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException;
    boolean updateCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException;
    boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException;
    String nextCustomerId() throws SQLException, ClassNotFoundException;
    CustomerDTO search(String newValue) throws SQLException, ClassNotFoundException;
    ArrayList<CustomerDTO> searchByContact(String newValue) throws SQLException, ClassNotFoundException;
}
