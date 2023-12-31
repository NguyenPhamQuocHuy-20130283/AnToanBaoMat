package org.example.demo.mapper;

import org.example.demo.dao.AccountDao;
import org.example.demo.dao.OrderDao;
import org.example.demo.dao.SignDao;
import org.example.demo.model.Sign;
import org.example.demo.model.Verification;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VerificationMapper {
    public static Verification mapRow(ResultSet rs) throws SQLException {
        Verification verification = new Verification();
        verification.setId(rs.getLong("ID"));
        verification.setOrder(OrderDao.findOneById(rs.getLong("ORDER_ID")));
        verification.setHashValue(rs.getString("HASH_VALUE"));
        verification.setSign(SignDao.findOneById(rs.getLong("SIGN_ID")));
        verification.setOK(rs.getBoolean("IS_OK"));
        verification.setCreatedDate(rs.getTimestamp("CREATED_DATE"));
        verification.setCreatedBy(rs.getString("CREATED_BY"));
        verification.setModifiedDate(rs.getTimestamp("MODIFIED_DATE"));
        verification.setModifiedBy(rs.getString("MODIFIED_BY"));
        return verification;
    }
}
