package com.wy.shopping.common.service.typehandler;

import com.wy.shopping.common.service.utils.JsonUtil;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author wy
 * @date 2019-07-06
 * @description json格式的字段解析
 */
public class JsonTypeHandler<T> extends BaseTypeHandler<T> {

    private Class<T> clazz;

    public JsonTypeHandler(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, T parameter, JdbcType jdbcType) throws SQLException {
        String s = JsonUtil.toJson(parameter);
        ps.setString(i, s);
    }

    @Override
    public T getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String string = rs.getString(columnName);
        return JsonUtil.parse(string, clazz);
    }

    @Override
    public T getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String string = rs.getString(columnIndex);
        return JsonUtil.parse(string, clazz);
    }

    @Override
    public T getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return JsonUtil.parse(cs.getString(columnIndex), clazz);
    }
}
