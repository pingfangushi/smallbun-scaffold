package org.smallbun.framework.injector.methods;

import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.extension.injector.AbstractLogicMethod;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.smallbun.framework.injector.enums.SqlMethod;

/**
 * 数据过滤并且分页
 * @author SanLi
 * Created by 2689170096@qq.com on 2018/11/14 20:47
 */
public class SelectDataScopePage extends AbstractLogicMethod {
	/**
	 * 注入自定义 MappedStatement
	 *
	 * @param mapperClass mapper 接口
	 * @param modelClass  mapper 泛型
	 * @param tableInfo   数据库表反射信息
	 * @return MappedStatement
	 */
	@Override
	public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
		SqlMethod sqlMethod = SqlMethod.SELECT_DATA_SCOPE_PAGE;
		String sql = String.format(sqlMethod.getSql(), sqlSelectColumns(tableInfo, true), tableInfo.getTableName(),
				sqlWhereEntityWrapper(true, tableInfo));
		SqlSource sqlSource = languageDriver.createSqlSource(configuration, sql, modelClass);
		return addSelectMappedStatement(mapperClass, sqlMethod.getMethod(), sqlSource, modelClass, tableInfo);
	}
}
