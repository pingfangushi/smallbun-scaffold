package cn.smallbun.scaffold.manage.handler;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;
import cn.smallbun.scaffold.manage.enums.DictStatus;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * DictDefaultTypeHandler
 * @author SanLi
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on 2019/11/25 21:11
 */
public class DictStatusTypeHandler implements TypeHandler<DictStatus> {

	@Override
	public void setParameter(PreparedStatement preparedStatement, int i, DictStatus status, JdbcType jdbcType)
			throws SQLException {
		preparedStatement.setString(i, status.getCode());
	}

	@Override
	public DictStatus getResult(ResultSet resultSet, String s) throws SQLException {
		return DictStatus.getStatus(resultSet.getString(s));
	}

	@Override
	public DictStatus getResult(ResultSet resultSet, int i) throws SQLException {
		return DictStatus.getStatus(resultSet.getString(i));
	}

	@Override
	public DictStatus getResult(CallableStatement callableStatement, int i) throws SQLException {
		return DictStatus.getStatus(callableStatement.getString(i));
	}
}
