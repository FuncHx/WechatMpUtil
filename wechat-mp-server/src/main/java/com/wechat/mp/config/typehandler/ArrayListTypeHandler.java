package com.wechat.mp.config.typehandler;

import com.alibaba.fastjson2.JSON;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;

public class ArrayListTypeHandler extends BaseTypeHandler<ArrayList<Integer>> {

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, ArrayList<Integer> longs, JdbcType jdbcType) throws SQLException {
        String jsonString = JSON.toJSONString(longs);
        preparedStatement.setString(i, jsonString);
    }

    @Override
    public ArrayList<Integer> getNullableResult(ResultSet resultSet, String s) throws SQLException {
        String jsonString = resultSet.getString(s);
        return JSON.parseObject(jsonString, ArrayList.class);
    }

    @Override
    public ArrayList<Integer> getNullableResult(ResultSet resultSet, int i) throws SQLException {
        String jsonString = resultSet.getString(i);
        return JSON.parseObject(jsonString, ArrayList.class);
    }

    @Override
    public ArrayList<Integer> getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        String jsonString = callableStatement.getString(i);
        return JSON.parseObject(jsonString, ArrayList.class);
    }
}