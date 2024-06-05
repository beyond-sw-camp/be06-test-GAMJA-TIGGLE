package org.example.tiggle.reservation.servlet;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.tiggle.config.BaseResponse;
import org.example.tiggle.reservation.DAO.ReservationDao;
import org.example.tiggle.reservation.DTO.EmptySeatRequest;
import org.example.tiggle.reservation.DTO.EmptySeatResponse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import static org.example.tiggle.config.BaseResponseMessage.TICKET_PURCHASE_FAIL_NOT_FOUND_SEAT;

@WebServlet("/reservation/seat")
public class ReservationServlet extends HttpServlet {
    ReservationDao dao;
    ObjectMapper mapper;

    @Override
    public void init() {
        dao = new ReservationDao();
        mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //잔여 좌석 조회


        BufferedReader reader = req.getReader();
        StringBuilder json = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            json.append(line);
        }


        EmptySeatRequest request = mapper.readValue(json.toString(), EmptySeatRequest.class);
        ArrayList<EmptySeatResponse> responses = dao.emptySeat(request);

        String jsonResponse;
        if (!responses.isEmpty()) {
            BaseResponse baseResponse = new BaseResponse(responses);
            jsonResponse = mapper.writeValueAsString(baseResponse);
        }else{
            BaseResponse baseResponse = new BaseResponse(TICKET_PURCHASE_FAIL_NOT_FOUND_SEAT);
            jsonResponse = mapper.writeValueAsString(baseResponse);
        }


        resp.getWriter().write(jsonResponse);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
    }
}
