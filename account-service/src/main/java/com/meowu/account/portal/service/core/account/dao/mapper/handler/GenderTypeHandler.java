package com.meowu.account.portal.service.core.account.dao.mapper.handler;

import com.meowu.account.portal.service.core.account.entity.Gender;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GenderTypeHandler extends BaseTypeHandler<Gender>{
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Gender gender, JdbcType jdbcType) throws SQLException{
        preparedStatement.setInt(i, gender == null ? Gender.UNKNOWN.getId() : gender.getId());
    }

    @Override
    public Gender getNullableResult(ResultSet resultSet, String s) throws SQLException{
        return Gender.getById(resultSet.getInt(s));
    }

    @Override
    public Gender getNullableResult(ResultSet resultSet, int i) throws SQLException{
        return Gender.getById(resultSet.getInt(i));
    }

    @Override
    public Gender getNullableResult(CallableStatement callableStatement, int i) throws SQLException{
        return Gender.getById(callableStatement.getInt(i));
    }
}
