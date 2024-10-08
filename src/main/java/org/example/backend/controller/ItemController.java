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
import org.example.backend.dto.ItemDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/item")
public class ItemController extends HttpServlet {

    JsonbConfig config = new JsonbConfig().withFormatting(true);
    Jsonb jsonb = JsonbBuilder.create(config);
    static Logger logger = LoggerFactory.getLogger(ItemController.class);
    ItemBO itemBO = (ItemBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ITEM);

    @Override
    public void init() throws ServletException {


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        logger.info("Inside item get method");

        String id = req.getParameter("id");
        String all = req.getParameter("all");
        String search = req.getParameter("search");
        String nextid = req.getParameter("nextid");

        if (all != null) {
            try (var writer = resp.getWriter()) {
                writer.write(jsonb.toJson(itemBO.getAllItem()));
            } catch (JsonException | SQLException | ClassNotFoundException e) {
                logger.error("Faild with: ",e.getMessage());
                throw new RuntimeException(e);
            }
        } else if (id != null) {
            try (var writer = resp.getWriter()) {
                writer.write(jsonb.toJson(itemBO.search(id)));
            } catch (JsonException | SQLException | ClassNotFoundException e) {
                logger.error("Faild with: ",e.getMessage());
                throw new RuntimeException(e);
            }
        } else if (search != null) {
            try (var writer = resp.getWriter()) {
                writer.write(jsonb.toJson(itemBO.searchByName(search)));
            } catch (JsonException | SQLException | ClassNotFoundException e) {
                logger.error("Faild with: ",e.getMessage());
                throw new RuntimeException(e);
            }
        } else if (nextid != null) {
            try (var writer = resp.getWriter()) {
                writer.write(jsonb.toJson(itemBO.nextItemId()));
            } catch (JsonException | SQLException | ClassNotFoundException e) {
                logger.error("Faild with: ",e.getMessage());
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

            logger.info("Inside item post method");

            ItemDTO itemDTO = jsonb.fromJson(req.getReader(), ItemDTO.class);
            boolean isSave = itemBO.saveItem(itemDTO);

            if (isSave) {
                writer.write("Item saved successfully");
                logger.info("Item saved successfully");
                resp.setStatus(HttpServletResponse.SC_CREATED);
            } else {
                writer.write("Item not saved");
                logger.info("Item not saved");
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        } catch (JsonException | SQLException | ClassNotFoundException e) {
            logger.error("Faild with: ",e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!req.getContentType().toLowerCase().startsWith("application/json") || req.getContentType() == null) {
            resp.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
        }

        try (var writer = resp.getWriter()){

            logger.info("Inside item put method");

            ItemDTO itemDTO = jsonb.fromJson(req.getReader(), ItemDTO.class);
            boolean isUpdate = itemBO.updateItem(itemDTO);

            if (isUpdate) {
                writer.write("Item updated successfully");
                logger.info("Item updated successfully");
                resp.setStatus(HttpServletResponse.SC_CREATED);
            } else {
                writer.write("Item not updated");
                logger.info("Item not updated");
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        } catch (JsonException | SQLException | ClassNotFoundException e) {
            logger.error("Faild with: ",e.getMessage());
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try (var writer = resp.getWriter()){

            logger.info("Inside item delete method");

            String id = req.getParameter("id");
            boolean isDelete = itemBO.deleteItem(id);

            if (isDelete) {
                writer.write("Item deleted successfully");
                logger.info("Item deleted successfully");
                resp.setStatus(HttpServletResponse.SC_CREATED);
            } else {
                writer.write("Item not deleted");
                logger.info("Item not deleted");
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        } catch (JsonException | SQLException | ClassNotFoundException e) {
            logger.error("Faild with: ",e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
