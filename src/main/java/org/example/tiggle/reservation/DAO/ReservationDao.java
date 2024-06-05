package org.example.tiggle.reservation.DAO;

import com.zaxxer.hikari.HikariDataSource;
import org.example.tiggle.config.DataSourceConfig;
import org.example.tiggle.reservation.DTO.EmptySeatRequest;
import org.example.tiggle.reservation.DTO.EmptySeatResponse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReservationDao {
    HikariDataSource dataSourceConfig;

    public ReservationDao() {
        dataSourceConfig = DataSourceConfig.getInstance();
    }

    public ArrayList<EmptySeatResponse> emptySeat(EmptySeatRequest dto) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            connection = dataSourceConfig.getConnection();
            pstmt = connection.prepareStatement("select " +
                    "seat.seatNumber, seat.section, times.date, times.round, program.programName, reservation.state" +
                    " from seat\n" +
                    "left join reservation on reservation.seatId = seat.seatId \n" +
                    "left join program on program.programId = reservation.programId\n" +
                    "left join times on reservation.timesId = times.timesId \n" +
                    "where times.timesid = ? AND program.programId = ?");
            pstmt.setInt(1, dto.getTimesId());
            pstmt.setInt(2, dto.getProgramId());

            rs = pstmt.executeQuery();

            System.out.println("rs = " + rs);
            ArrayList<EmptySeatResponse> result = new ArrayList<>();

            while (rs.next()) {
                EmptySeatResponse res
                        = new EmptySeatResponse(rs.getInt("seatNumber")
                        , rs.getString("section")
                        , rs.getDate("date")
                        , rs.getString("round")
                        , rs.getString("programName")
                        , rs.getBoolean("state"));
                result.add(res);
            }
            return result;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException sqlEx) {
                } // ignore
                pstmt = null;
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException sqlEx) {
                } // ignore
                connection = null;
            }
        }
    }
}
