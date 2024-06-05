package org.example.tiggle.user.servlet;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.tiggle.config.BaseResponse;
import org.example.tiggle.config.BaseResponseMessage;
import org.example.tiggle.user.DAO.UserDao;
import org.example.tiggle.user.DTO.request.PostUserLoginReq;
import org.example.tiggle.user.DTO.request.PostUserLoginRes;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;


@WebServlet("/user/login")
public class UserLoginServlet extends HttpServlet {
    UserDao dao;
    ObjectMapper mapper;
    String jsonResponse;

    @Override
    public void init() {
        dao = new UserDao();
        mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // ------------------- 클라이언트로부터 요청을 받아서 Dto에 저장하는 부분 -------------------
        BufferedReader reader = req.getReader();
        StringBuilder json = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            json.append(line);
        }

        PostUserLoginReq dto = mapper.readValue(json.toString(), PostUserLoginReq.class);

        if (dto.getId() == null) {
            BaseResponse response = new BaseResponse(BaseResponseMessage.MEMBER_REGISTER_FAIL_ID_EMPTY);
            jsonResponse = mapper.writeValueAsString(response);
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write(jsonResponse);
            return;
        }
        if (dto.getPassword() == null) {
            BaseResponse response = new BaseResponse(BaseResponseMessage.MEMBER_REGISTER_FAIL_PASSWORD_EMPTY);
            jsonResponse = mapper.writeValueAsString(response);
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write(jsonResponse);
            return;
        }


        // ------------------- ------------------- -------------------

        // ------------------- 로그인 확인하는 Dao의 메소드 실행 -------------------
        PostUserLoginRes result = dao.find(dto);
//        Boolean result = false;
        // ------------------- ------------------- -------------------


        // ------------------- Dao의 처리 결과에 따른 응답 설정 부분 -------------------
        if (result != null) {
            req.getSession().setAttribute("isLogin", true);
            req.getSession().setAttribute("userId", result.getId());
            req.getSession().setAttribute("userName", result.getName());

            BaseResponse response = new BaseResponse(BaseResponseMessage.MEMBER_LOGIN_SUCCESS);
            jsonResponse = mapper.writeValueAsString(response);
        } else {
            BaseResponse response = new BaseResponse(BaseResponseMessage.MEMBER_LOGIN_FAIL);
            jsonResponse = mapper.writeValueAsString(response);
        }

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(jsonResponse);
        // ------------------- ------------------- -------------------

    }
}
