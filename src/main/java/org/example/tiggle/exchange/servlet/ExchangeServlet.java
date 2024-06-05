package org.example.tiggle.exchange.servlet;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.tiggle.config.BaseResponse;
import org.example.tiggle.config.BaseResponseMessage;
import org.example.tiggle.exchange.DAO.ExchangeDao;
import org.example.tiggle.exchange.DTO.request.GetAvailableExchangeReq;
import org.example.tiggle.exchange.DTO.request.GetAvailableExchangeRes;
import org.example.tiggle.exchange.DTO.request.PostExchangeReq;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/exchange")
public class ExchangeServlet extends HttpServlet {
    ExchangeDao dao;
    ObjectMapper mapper;

    public void init() {
        dao = new ExchangeDao();
        mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();
        StringBuilder json = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            json.append(line);
        }

        GetAvailableExchangeReq dto = mapper.readValue(json.toString(), GetAvailableExchangeReq.class);

        List<GetAvailableExchangeRes> result = dao.read(dto);

        String jsonResponse;
        if (result.size() > 0) {
            BaseResponse response = new BaseResponse(result);
            jsonResponse = mapper.writeValueAsString(response);
        } else {
            BaseResponse response = new BaseResponse(BaseResponseMessage.EXCHANGE_FAIL_ABNORMAL_ACCESS);
            jsonResponse = mapper.writeValueAsString(response);
        }

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(jsonResponse);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();
        StringBuilder json = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            json.append(line);
        }

        PostExchangeReq dto = mapper.readValue(json.toString(), PostExchangeReq.class);

        Boolean result = dao.create(dto);

        String jsonResponse;
        if (result) {
            BaseResponse response = new BaseResponse(BaseResponseMessage.EXCHANGE_SUCCESS);
            jsonResponse = mapper.writeValueAsString(response);
        } else {
            BaseResponse response = new BaseResponse(BaseResponseMessage.EXCHANGE_FAIL_ABNORMAL_ACCESS);
            jsonResponse = mapper.writeValueAsString(response);
        }

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(jsonResponse);
    }
}
