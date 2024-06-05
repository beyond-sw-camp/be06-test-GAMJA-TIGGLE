package org.example.tiggle.exchange.DAO;

import com.zaxxer.hikari.HikariDataSource;
import org.example.tiggle.config.DataSourceConfig;
import org.example.tiggle.exchange.DTO.request.GetAvailableExchangeReq;
import org.example.tiggle.exchange.DTO.request.GetAvailableExchangeRes;
import org.example.tiggle.exchange.DTO.request.PostExchangeReq;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExchangeDao {
    HikariDataSource dataSourceConfig;

    public ExchangeDao() {
        dataSourceConfig = DataSourceConfig.getInstance();
    }

//    교환 가능 좌석 조회
public List<GetAvailableExchangeRes> read(GetAvailableExchangeReq dto){
    Connection connection = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    try {
        connection = dataSourceConfig.getConnection();
        pstmt = connection.prepareStatement("SELECT reservation.seatId, reservation.state " +
                "FROM reservation " +
                "JOIN seat ON reservation.seatId = seat.seatId " +
                "WHERE reservation.programId = ? AND reservation.timesId = ? AND seat.section = ? AND reservation.state = 1;");

        pstmt.setInt(1, dto.getProgramId());
        pstmt.setInt(2, dto.getTimes());
        pstmt.setString(3, dto.getSection());

        rs = pstmt.executeQuery();

        List<GetAvailableExchangeRes> result = new ArrayList<>();

        System.out.println(rs);


        while (rs.next()) {
            GetAvailableExchangeRes getAvailableExchangeRes = new GetAvailableExchangeRes(rs.getInt("seatId"), rs.getInt("state"));
            result.add(getAvailableExchangeRes);
        }
        return result;
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

}

//    교환 신청
    public Boolean create(PostExchangeReq dto){
        Connection connection = null;
        PreparedStatement pstmt = null;
        Integer result = null;

        try {
            connection = dataSourceConfig.getConnection();
            pstmt = connection.prepareStatement("INSERT INTO web.member (id, pw, name) VALUES (?, ?, ?)");
            pstmt.setInt(1, dto.getProgramId());
            pstmt.setInt(2, dto.getTimes());
            pstmt.setString(3, dto.getSection());
            pstmt.setInt(3, dto.getSeatId());
            result = pstmt.executeUpdate();

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
