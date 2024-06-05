package org.example.tiggle.program.DAO;

import com.zaxxer.hikari.HikariDataSource;
import org.example.tiggle.config.DataSourceConfig;
import org.example.tiggle.program.DTO.request.ProgramRegisterReq;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ProgramRegisterDAO {
    // hikari CP 객체 생성
    HikariDataSource dataSourceConfig;
    // 생성자
    public ProgramRegisterDAO() {
        dataSourceConfig = DataSourceConfig.getInstance();
    }

    public Boolean register(ProgramRegisterReq registerDto) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        Integer result = null;

        //현재 시간을 가져오는 객체
        Date date = new Date();
        출처: https://developer-talk.tistory.com/408 [DevStory:티스토리]

        try {
            //dataSourceConfig안의 정보를 바탕으로 커넥션 형성
            connection = dataSourceConfig.getConnection();
            // createdAt을 현재 시간으로 변경
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String strCreatedDate = formatter.format(date);
            //prepareStatement 생성 및 sql 입력 -> setString 메소드로 각 ?에 데이터 주입
            pstmt = connection.prepareStatement("INSERT INTO program (locationId, categoryId, programName, programInfo, reservationOpenDate, reservationCloseDate, imgUrl, age, runtime, salerInfo, createdAt, modifiedAt, programStartDate, programEndDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?,  ?, ?, ?, ?, ?, ?)");
            pstmt.setString(1,  "1");
            pstmt.setString(2, "1");
            pstmt.setString(3, registerDto.getProgramName());
            pstmt.setString(4, registerDto.getProgramInfo());
            pstmt.setString(5, registerDto.getResvationOpenDate().toString());
            pstmt.setString(6, registerDto.getResvationCloseDate().toString());
            pstmt.setString(7, registerDto.getImgUrl());
            pstmt.setString(8, registerDto.getAge().toString());
            pstmt.setString(9, registerDto.getRuntime().toString());
            pstmt.setString(10, registerDto.getSallerInfo());
            pstmt.setString(11, strCreatedDate);
            pstmt.setString(12, null);
            pstmt.setString(13, registerDto.getProgramStartDate().toString());
            pstmt.setString(14, registerDto.getProgramEndDate().toString());
            //result : sql의 결과로 인해 DB의 데이터에 변경 내역이 있을 경우 = 0보다 큰값 저장
            result = pstmt.executeUpdate();
            System.out.println(result);

            if (result > 0) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException sqlEx) { } // ignore
                pstmt = null;
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException sqlEx) { } // ignore
                connection = null;
            }
        }
        return false;
    }
}
