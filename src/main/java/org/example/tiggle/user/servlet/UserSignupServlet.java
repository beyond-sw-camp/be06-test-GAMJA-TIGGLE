package org.example.tiggle.user.servlet;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.tiggle.config.BaseResponse;
import org.example.tiggle.config.BaseResponseMessage;
import org.example.tiggle.user.DAO.UserDao;
import org.example.tiggle.user.DTO.request.PostUserSignupReq;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/user/signup")
public class UserSignupServlet extends HttpServlet {
    UserDao dao;
    ObjectMapper mapper;

    @Override
    public void init() {
        dao = new UserDao();
        mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // ------------------- 클라이언트로부터 요청을 받아서 Dto에 저장하는 부분 -------------------
        // 역직렬화 | JSON -> 객체
        BufferedReader reader = req.getReader();
        StringBuilder json = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            json.append(line);
        }


        PostUserSignupReq dto = mapper.readValue(json.toString(), PostUserSignupReq.class);
        // ------------------- ------------------- -------------------
        // dto에 값을 잘 전달 했는지 보기 위해서 써봄
        // System.out.println(dto.toString());

        // ------------------- 회원 가입하는 Dao의 메소드 실행 -------------------
        Boolean result = dao.create(dto);
//        Boolean result = false;
        // ------------------- ------------------- -------------------


        // ------------------- Dao의 처리 결과에 따른 응답 설정 부분 -------------------
        // 직렬화 | 객체 -> JSON
        String jsonResponse;
        if (result) {
            BaseResponse response = new BaseResponse(BaseResponseMessage.MEMBER_REGISTER_SUCCESS);
            jsonResponse = mapper.writeValueAsString(response);
        } else {
            BaseResponse response = new BaseResponse(BaseResponseMessage.MEMBER_REGISTER_FAIL_PASSWORD_EMPTY);
            jsonResponse = mapper.writeValueAsString(response);
        }

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(jsonResponse);
        // ------------------- ------------------- -------------------

    }
}
