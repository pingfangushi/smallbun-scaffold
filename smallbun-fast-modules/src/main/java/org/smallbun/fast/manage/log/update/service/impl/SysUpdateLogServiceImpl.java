package org.smallbun.fast.manage.log.update.service.impl;

import org.smallbun.fast.manage.log.update.dao.SysUpdateLogMapper;
import org.smallbun.fast.manage.log.update.entity.SysUpdateLogEntity;
import org.smallbun.fast.manage.log.update.service.SysUpdateLogService;
import org.smallbun.fast.manage.log.update.vo.SysUpdateLogVO;
import org.smallbun.framework.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

import static org.smallbun.framework.constant.UrlPrefixConstant.UNIQUE;
import static org.smallbun.framework.toolkit.AutoMapperUtil.mapping;

/**
 * 更新记录逻辑实现类
 * @author SanLi
 * Created by 2689170096@qq.com on 2019/2/4 18:05
 */
@Service
public class SysUpdateLogServiceImpl extends BaseServiceImpl<SysUpdateLogMapper, SysUpdateLogEntity>
		implements SysUpdateLogService<SysUpdateLogEntity> {
	/**
	 * model
	 * @param request
	 * @return
	 */
	@Override
	public SysUpdateLogVO model(HttpServletRequest request) {
		if (!request.getRequestURI().contains(UNIQUE)) {
			return StringUtils.isEmpty(request.getParameter(ID)) ?
					new SysUpdateLogVO() :
					mapping(getById(request.getParameter(ID)), new SysUpdateLogVO());
		}
		return new SysUpdateLogVO();
	}
}
