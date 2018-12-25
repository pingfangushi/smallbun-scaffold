package org.smallbun.fast.manage.dashboard.service.impl;

import org.smallbun.fast.manage.dashboard.service.DashboardService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 仪表盘业务逻辑类
 * @author SanLi
 * Created by 2689170096@qq.com on 2018/11/7 22:49
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class DashboardServiceImpl implements DashboardService {
}
