package com.wy.shopping.common.service.typehandler;

import com.wy.shopping.common.service.enumeration.BaseEnum;
import com.wy.shopping.common.service.utils.EnumUtil;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author wy
 * @description
 * @date 2019-05-18
 */
public class EnumTypeHandler<T extends Enum<?> & BaseEnum> extends BaseTypeHandler<BaseEnum> {

    private Class<T> type;

    public EnumTypeHandler(Class<T> type) {
        if (type == null) {
            throw new IllegalArgumentException("类型参数不能为空！");
        }
        this.type = type;
    }

    /**
     * 类型转换
     *
     * @param preparedStatement 预编译的sql声明语句
     * @param i                 参数的索引
     * @param baseEnum          java对象
     * @param jdbcType          jdbcType
     * @throws SQLException
     */
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, BaseEnum baseEnum, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i, baseEnum.getCode());
    }

    /**
     * @param resultSet
     * @param columnName
     * @return
     * @throws SQLException
     */
    @Override
    public BaseEnum getNullableResult(ResultSet resultSet, String columnName) throws SQLException {
        int code = resultSet.getInt(columnName);
        return resultSet.wasNull() ? null : codeOf(code);
    }

    @Override
    public BaseEnum getNullableResult(ResultSet resultSet, int i) throws SQLException {
        int code = resultSet.getInt(i);
        return resultSet.wasNull() ? null : codeOf(code);
    }

    @Override
    public BaseEnum getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        int code = callableStatement.getInt(i);
        return callableStatement.wasNull() ? null : codeOf(code);
    }

    /**
     * 根据code得到泛型实例
     *
     * @param code
     * @return
     */
    private BaseEnum codeOf(int code) {
        return EnumUtil.codeOf(type, code);
    }
}
