package cn.smallbun.scaffold.manage.handler;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;
import cn.smallbun.scaffold.manage.enums.UserStatus;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * UserStatusTypeHandler
 * @author SanLi
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on 2019/11/25 21:11
 */
public class UserStatusTypeHandler implements TypeHandler<UserStatus> {

	@Override
	public void setParameter(PreparedStatement preparedStatement, int i, UserStatus status, JdbcType jdbcType)
			throws SQLException {
		preparedStatement.setString(i, status.getCode());
	}

	@Override
	public UserStatus getResult(ResultSet resultSet, String s) throws SQLException {
		return UserStatus.getStatus(resultSet.getString(s));
	}

	@Override
	public UserStatus getResult(ResultSet resultSet, int i) throws SQLException {
		return UserStatus.getStatus(resultSet.getString(i));
	}

	@Override
	public UserStatus getResult(CallableStatement callableStatement, int i) throws SQLException {
		return UserStatus.getStatus(callableStatement.getString(i));
	}
}
