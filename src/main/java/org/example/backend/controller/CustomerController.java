package org.example.backend.controller;

import jakarta.json.JsonException;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.json.bind.JsonbConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.backend.bo.BOFactory;
import org.example.backend.bo.custom.CustomerBO;
import org.example.backend.dto.CustomerDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.SQLException;


@WebServlet(urlPatterns = "/customer")
public class CustomerController extends HttpServlet {

    JsonbConfig config = new JsonbConfig().withFormatting(true);
    Jsonb jsonb = JsonbBuilder.create(config);
    static Logger logger = LoggerFactory.getLogger(CustomerController.class);
    CustomerBO customerBO = (CustomerBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CUSTOMER);

    @Override
    public void init() throws ServletException {


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("Inside customer get method");

        String id = req.getParameter("id");
        String all = req.getParameter("all");
        String search = req.getParameter("search");
        String nextid = req.getParameter("nextid");

        if (all != null) {
            try (var writer = resp.getWriter()) {
                writer.write(jsonb.toJson(customerBO.getAllCustomers()));
            } catch (JsonException | SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else if (id != null) {
            try (var writer = resp.getWriter()) {
                writer.write(jsonb.toJson(customerBO.search(id)));
            } catch (JsonException | SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else if (search != null) {
            try (var writer = resp.getWriter()) {
                writer.write(jsonb.toJson(customerBO.searchByContact(search)));
            } catch (JsonException | SQLException | ClassNotFoundException e) {

            }
        } else if (nextid != null) {
            try (var writer = resp.getWriter()) {
                writer.write(jsonb.toJson(customerBO.nextCustomerId()));
            } catch (JsonException | SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!req.getContentType().toLowerCase().startsWith("application/json") || req.getContentType() == null) {
            resp.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
        }

        try (var writer = resp.getWriter()){

            System.out.println("Inside customer post method");

            CustomerDTO customerDTO = jsonb.fromJson(req.getReader(), CustomerDTO.class);
            System.out.println(customerDTO);
            boolean isSave = customerBO.saveCustomer(customerDTO);

            if (isSave) {
                writer.write("Customer saved successfully");
                resp.setStatus(HttpServletResponse.SC_CREATED);
            } else {
                writer.write("Customer not saved");
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        } catch (JsonException | SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!req.getContentType().toLowerCase().startsWith("application/json") || req.getContentType() == null) {
            resp.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
        }

        try (var writer = resp.getWriter()){

            System.out.println("Inside customer put method");

            CustomerDTO customerDTO = jsonb.fromJson(req.getReader(), CustomerDTO.class);
            System.out.println(customerDTO);
            boolean isUpdate = customerBO.updateCustomer(customerDTO);

            if (isUpdate) {
                writer.write("Customer updated successfully");
                resp.setStatus(HttpServletResponse.SC_CREATED);
            } else {
                writer.write("Customer not updated");
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        } catch (JsonException | SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try (var writer = resp.getWriter()){
            System.out.println("Inside customer Delete method");

            String id = req.getParameter("id");
            System.out.println(id);
            boolean isDelete = customerBO.deleteCustomer(id);

            if (isDelete) {
                writer.write("Customer deleted successfully");
                resp.setStatus(HttpServletResponse.SC_CREATED);
            } else {
                writer.write("Customer not deleted");
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        } catch (JsonException | SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
