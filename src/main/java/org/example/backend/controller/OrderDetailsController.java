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
import org.example.backend.bo.custom.ItemBO;
import org.example.backend.bo.custom.OrderDetailsBO;
import org.example.backend.dto.OrderDetailDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/orderDetails")
public class OrderDetailsController extends HttpServlet {

    JsonbConfig config = new JsonbConfig().withFormatting(true);
    Jsonb jsonb = JsonbBuilder.create(config);
    static Logger logger = LoggerFactory.getLogger(OrderDetailsController.class);
    OrderDetailsBO orderDetailsBO = (OrderDetailsBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ORDER_DETAILS);
    ItemBO itemBO = (ItemBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ITEM);

    @Override
    public void init() throws ServletException {


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("Inside order detail get method");

        String id = req.getParameter("id");

        if (id != null) {
            try (var writer = resp.getWriter()) {
                writer.write(jsonb.toJson(orderDetailsBO.searchByOrderId(id)));
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

            System.out.println("Inside order detail post method");

            OrderDetailDTO orderDetailDTO = jsonb.fromJson(req.getReader(), OrderDetailDTO.class);
            boolean isSave = orderDetailsBO.save(orderDetailDTO);
            boolean isUpdate = itemBO.updateQty(orderDetailDTO.getItem_id(), String.valueOf(orderDetailDTO.getQty()));

            if (isSave && isUpdate) {
                writer.write("Order Details saved successfully");
                resp.setStatus(HttpServletResponse.SC_CREATED);
            } else {
                writer.write("Order Details not saved");
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        } catch (JsonException | SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
