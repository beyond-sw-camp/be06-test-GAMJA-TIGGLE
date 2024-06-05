package org.example.tiggle.program.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.tiggle.config.BaseResponse;
import org.example.tiggle.config.BaseResponseMessage;
import org.example.tiggle.program.DAO.ProgramRegisterDAO;
import org.example.tiggle.program.DTO.request.ProgramRegisterReq;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/program/create")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // ------------------- 클라이언트로부터 요청을 받아서 Dto에 저장하는 부분 -------------------
        BufferedReader reader = req.getReader();
        StringBuilder json = new StringBuilder();
        String line;

        //클라이언트에 요청을 받아서 한줄씩 json에 저장
        while((line = reader.readLine()) != null) {
            json.append(line);
        }
        System.out.println("json = " + json);
        //objectMapper로 json 값을 읽어서 key value로 나눈 값을 넣은 DTO 객체 생성
        ObjectMapper mapper = new ObjectMapper();
        ProgramRegisterReq registerDto = mapper.readValue(json.toString(), ProgramRegisterReq.class);

        // ------------------- 회원 가입하는 Dao의 메소드 실행 -------------------
        Boolean result;
        ProgramRegisterDAO registerDao = new ProgramRegisterDAO();
        result = registerDao.register(registerDto);
        // ------------------- ------------------- -------------------


        // ------------------- Dao의 처리 결과에 따른 응답 설정 부분 -------------------
        String jsonResponse;
        if(result) {
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

