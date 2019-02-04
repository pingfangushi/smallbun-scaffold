package org.smallbun.fast.manage.about.service.impl;

import org.smallbun.fast.manage.about.dao.SysAboutMapper;
import org.smallbun.fast.manage.about.entity.SysAboutEntity;
import org.smallbun.fast.manage.about.service.SysAboutService;
import org.smallbun.framework.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 关于业务逻辑实现类
 * @author SanLi
 * Created by 2689170096@qq.com on 2019/2/4 18:05
 */
@Service
public class SysAboutServiceImpl extends BaseServiceImpl<SysAboutMapper, SysAboutEntity>
		implements SysAboutService<SysAboutEntity> {
}
