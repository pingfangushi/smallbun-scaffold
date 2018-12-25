package org.smallbun.framework.injector;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import org.smallbun.framework.injector.methods.SelectDataScopePage;

import java.util.List;

/**
 * 数据权限过滤
 * @author SanLi
 * Created by 2689170096@qq.com on 2018/11/14 20:55
 */
public class DataScopeSqlInjector extends LogicSqlInjector {

	/**
	 * 获取方法列表
	 * @author SanLi
	 * Created by 2689170096@qq.com on 2018/11/14 20:57
	 */
	@Override
	public List<AbstractMethod> getMethodList() {
		List<AbstractMethod> methodList = super.getMethodList();
		methodList.add(new SelectDataScopePage());
		return methodList;
	}

}
