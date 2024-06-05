package org.example.tiggle.user.DAO;

import com.zaxxer.hikari.HikariDataSource;

import org.example.tiggle.config.DataSourceConfig;
import org.example.tiggle.user.DTO.request.PostUserLoginReq;
import org.example.tiggle.user.DTO.request.PostUserLoginRes;
import org.example.tiggle.user.DTO.request.PostUserSignupReq;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    HikariDataSource dataSourceConfig;

    public UserDao() {
        dataSourceConfig = DataSourceConfig.getInstance();
    }

    public Boolean create(PostUserSignupReq dto) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        Integer result = null;
        try {
            connection = dataSourceConfig.getConnection();
            pstmt = connection.prepareStatement("INSERT INTO tiggle1000.user (name, email, id, password, createdAt, loginType) VALUES (?, ?, ?, ?, ?, ?)");

            pstmt.setString(1, dto.getName());
            pstmt.setString(2, dto.getEmail());
            pstmt.setString(3, dto.getId());
            pstmt.setString(4, dto.getPassword());
            pstmt.setString(5, dto.getCreatedAt());
            pstmt.setString(6, dto.getLoginType());

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
        return false;
    }

    public PostUserLoginRes find(PostUserLoginReq dto) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            connection = dataSourceConfig.getConnection();
            pstmt = connection.prepareStatement("SELECT * FROM user WHERE id=? AND password=?");
            pstmt.setString(1, dto.getId());
            pstmt.setString(2, dto.getPassword());
            rs = pstmt.executeQuery();
            PostUserLoginRes postUserLoginRes = null;
            if (rs.next()) {
                postUserLoginRes = new PostUserLoginRes(rs.getInt("userId"), rs.getString("id"), rs.getString("name"));
            }

            return postUserLoginRes;
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

            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) {
                } // ignore
                rs = null;
            }
        }
    }
}


