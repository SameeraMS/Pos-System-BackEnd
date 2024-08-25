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
import org.example.backend.bo.custom.OrderBO;
import org.example.backend.dto.OrderDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/order")
public class OrderController extends HttpServlet {

    JsonbConfig config = new JsonbConfig().withFormatting(true);
    Jsonb jsonb = JsonbBuilder.create(config);
    static Logger logger = LoggerFactory.getLogger(OrderController.class);
    OrderBO orderBO = (OrderBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ORDER);

    @Override
    public void init() throws ServletException {


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("Inside order get method");

        String id = req.getParameter("id");
        String all = req.getParameter("all");
        String search = req.getParameter("search");
        String nextid = req.getParameter("nextid");

        if (all != null) {
            try (var writer = resp.getWriter()) {
                writer.write(jsonb.toJson(orderBO.getAllOrders()));
            } catch (JsonException | SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else if (id != null) {
            try (var writer = resp.getWriter()) {
                writer.write(jsonb.toJson(orderBO.search(id)));
            } catch (JsonException | SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else if (search != null) {
            try (var writer = resp.getWriter()) {
                writer.write(jsonb.toJson(orderBO.searchByOrderId(search)));
            } catch (JsonException | SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else if (nextid != null) {
            try (var writer = resp.getWriter()) {
                writer.write(jsonb.toJson(orderBO.nextOrderId()));
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

            System.out.println("Inside order post method");

            OrderDTO orderDTO = jsonb.fromJson(req.getReader(), OrderDTO.class);
            System.out.println(orderDTO);
            boolean isSave = orderBO.saveOrder(orderDTO);

            if (isSave) {
                writer.write("Order saved successfully");
                resp.setStatus(HttpServletResponse.SC_CREATED);
            } else {
                writer.write("Order not saved");
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

            System.out.println("Inside order put method");

            OrderDTO orderDTO = jsonb.fromJson(req.getReader(), OrderDTO.class);
            System.out.println(orderDTO);
            boolean isUpdate = orderBO.updateOrder(orderDTO);

            if (isUpdate) {
                writer.write("Order updated successfully");
                resp.setStatus(HttpServletResponse.SC_CREATED);
            } else {
                writer.write("Order not updated");
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        } catch (JsonException | SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try (var writer = resp.getWriter()){
            System.out.println("Inside order Delete method");

            String id = req.getParameter("id");
            System.out.println(id);
            boolean isDelete = orderBO.deleteOrder(id);

            if (isDelete) {
                writer.write("Order deleted successfully");
                resp.setStatus(HttpServletResponse.SC_CREATED);
            } else {
                writer.write("Order not deleted");
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        } catch (JsonException | SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
