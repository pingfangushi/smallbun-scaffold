package org.smallbun.fast.manage.config.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.smallbun.fast.manage.config.dao.SysConfigMapper;
import org.smallbun.fast.manage.config.entity.SysConfigEntity;
import org.smallbun.fast.manage.config.service.SysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 系统部门接口
 * @author SanLi
 * Created by 2689170096@qq.com on 2018/8/3
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysConfigServiceImpl extends ServiceImpl<SysConfigMapper, SysConfigEntity> implements SysConfigService {


}
