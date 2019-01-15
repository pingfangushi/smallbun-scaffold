package org.smallbun.fast.manage.dict.service.impl;

import org.smallbun.fast.manage.dict.dao.SysDictTypeMapper;
import org.smallbun.fast.manage.dict.entity.SysDictTypeEntity;
import org.smallbun.fast.manage.dict.service.SysDictTypeService;
import org.smallbun.fast.manage.dict.vo.SysDictTypeVO;
import org.smallbun.framework.base.BaseServiceImpl;
import org.smallbun.framework.toolkit.AutoMapperUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

import static org.smallbun.framework.constant.UrlPrefixConstant.UNIQUE;

/**
 * 系统字典类型 服务实现类
 * @author SanLi
 * Created by 2689170096@qq.com on 2018/10/2
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysDictTypeServiceImpl extends BaseServiceImpl<SysDictTypeMapper, SysDictTypeEntity>
		implements SysDictTypeService {

	/**
	 * model
	 * @param request
	 * @return
	 */
	@Override
	public SysDictTypeVO model(HttpServletRequest request) {
		if (!request.getRequestURI().contains(UNIQUE)) {
			return StringUtils.isEmpty(request.getParameter("id")) ?
					new SysDictTypeVO() :
					AutoMapperUtil.mapping(getById(request.getParameter("id")), new SysDictTypeVO());
		}
		return new SysDictTypeVO();
	}


}
