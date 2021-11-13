package com.meowu.account.portal.service.core.account.dao.mapper.handler;

import com.meowu.account.portal.service.core.account.entity.AccountState;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountStateTypeHandler extends BaseTypeHandler<AccountState>{

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, AccountState accountState, JdbcType jdbcType) throws SQLException{
        preparedStatement.setInt(i, accountState.getId());
    }

    @Override
    public AccountState getNullableResult(ResultSet resultSet, String s) throws SQLException{
        return AccountState.getById(resultSet.getInt(s));
    }

    @Override
    public AccountState getNullableResult(ResultSet resultSet, int i) throws SQLException{
        return AccountState.getById(resultSet.getInt(i));
    }

    @Override
    public AccountState getNullableResult(CallableStatement callableStatement, int i) throws SQLException{
        return AccountState.getById(callableStatement.getInt(i));
    }
}
