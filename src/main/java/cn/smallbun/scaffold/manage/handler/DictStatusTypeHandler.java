/*
 * Copyright (c) 2018-2019. ‭‭‭‭‭‭‭‭‭‭‭‭[zuoqinggang] www.pingfangushi.com
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package cn.smallbun.scaffold.manage.handler;

import cn.smallbun.scaffold.manage.enums.DictStatus;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

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
