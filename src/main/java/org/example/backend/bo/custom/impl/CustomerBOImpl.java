package org.example.backend.bo.custom.impl;

import org.example.backend.bo.custom.CustomerBO;
import org.example.backend.dao.DAOFactory;
import org.example.backend.dao.custom.CustomerDAO;
import org.example.backend.dto.CustomerDTO;
import org.example.backend.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBOImpl implements CustomerBO {

    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    @Override
    public ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException {
        ArrayList<Customer> allCustomers = customerDAO.getAll();
        ArrayList<CustomerDTO> customerDTOS = new ArrayList<>();

        for (Customer customer : allCustomers) {
            customerDTOS.add(new CustomerDTO(customer.getId(), customer.getName(), customer.getAddress(), customer.getContact()));
        }

        return customerDTOS;
    }

    @Override
    public boolean existCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.exist(id);
    }
    @Override
    public boolean saveCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        return customerDAO.save(new Customer(dto.getId(), dto.getName(), dto.getAddress(), dto.getContact()));
    }
    @Override
    public boolean updateCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        return customerDAO.update(new Customer(dto.getId(), dto.getName(), dto.getAddress(), dto.getContact()));
    }
    @Override
    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.delete(id);
    }
    @Override
    public String nextCustomerId() throws SQLException, ClassNotFoundException {
        return customerDAO.nextId();
    }

    @Override
    public CustomerDTO search(String newValue) throws SQLException, ClassNotFoundException {
        Customer search = customerDAO.search(newValue);
        return new CustomerDTO(search.getId(), search.getName(), search.getAddress(), search.getContact());
    }

    @Override
    public ArrayList<CustomerDTO> searchByContact(String newValue) throws SQLException, ClassNotFoundException {
        ArrayList<Customer> search = customerDAO.searchByContact(newValue);
        ArrayList<CustomerDTO> customerDTOS = new ArrayList<>();

        for (Customer customer : search) {
            customerDTOS.add(new CustomerDTO(customer.getId(), customer.getName(), customer.getAddress(), customer.getContact()));
        }
        return customerDTOS;
    }
}
