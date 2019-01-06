package org.smallbun.fast.manage.role.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.smallbun.fast.manage.menu.service.SysMenuService;
import org.smallbun.fast.manage.org.service.SysOrgService;
import org.smallbun.fast.manage.org.vo.SysOrgVO;
import org.smallbun.fast.manage.role.dao.SysRoleMapper;
import org.smallbun.fast.manage.role.entity.SysRoleEntity;
import org.smallbun.fast.manage.role.service.SysRoleMenuService;
import org.smallbun.fast.manage.role.service.SysRoleOrgService;
import org.smallbun.fast.manage.role.service.SysRoleService;
import org.smallbun.fast.manage.role.vo.SysRoleVO;
import org.smallbun.framework.base.BaseServiceImpl;
import org.smallbun.framework.result.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;

import static org.smallbun.framework.permission.constant.DataScopeConstant.DATA_SCOPE_CUSTOM;
import static org.smallbun.framework.toolkit.AutoMapperUtil.mapping;
import static org.smallbun.framework.toolkit.AutoMapperUtil.mappingList;

/**
 * @author SanLi
 * Created by 2689170096@qq.com on 2018/11/15 23:42
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class, readOnly = true)
public class SysRoleServiceImpl extends BaseServiceImpl<SysRoleMapper, SysRoleEntity> implements SysRoleService {


    @Autowired
    public SysRoleServiceImpl(SysRoleMenuService sysRoleMenuService, SysRoleOrgService sysRoleOrgService,
                              SysMenuService sysMenuService, SysOrgService sysOrgService) {
        this.sysRoleMenuService = sysRoleMenuService;
        this.sysRoleOrgService = sysRoleOrgService;
        this.sysMenuService = sysMenuService;
        this.sysOrgService = sysOrgService;
    }

    /**
     * 根据编号获取id
     *
     * @param id
     * @return
     */
    @Override
    public SysRoleVO getById(Serializable id) {
        SysRoleVO role = new SysRoleVO();
        if (!StringUtils.isEmpty(id)) {
            role = mapping(super.getById(id), new SysRoleVO());
            if (!StringUtils.isEmpty(id)) {
                //放入当前用户拥有的菜单JSON字符串
                role.setMenusJson(JSONObject.toJSONString(sysMenuService.findByRoleId(id)));
            }
            //如果是按照明细设置
            if (DATA_SCOPE_CUSTOM.getNumber().equals(role.getDataScope())) {
                //放入当前用户拥有的部门权限JSON字符串
                role.setOrgsJson(JSONObject
                        .toJSONString(mappingList(sysOrgService.findByRoleId(id), new ArrayList<>(), SysOrgVO.class)));
            }
        }
        return role;
    }

    /**
     * 唯一验证
     *
     * @param role {@link SysRoleEntity}
     * @return {@link AjaxResult}
     */
    @Override
    public AjaxResult unique(SysRoleEntity role) {
        //构建查询条件
        LambdaQueryWrapper<SysRoleEntity> queryWrapper = new QueryWrapper<>(role).lambda().eq(false, SysRoleEntity::getRoleName, role.getRoleName());
        return uniqueResult(role.getId(), queryWrapper);
    }

    /**
     * <p>
     * TableId 注解存在更新记录，否插入一条记录
     * </p>
     *
     * @param entity 实体对象
     * @return boolean
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveOrUpdate(SysRoleEntity entity) {
        boolean flag;
        //添加角色表信息,
        flag = super.saveOrUpdate(entity)
                //添加角色-用户菜单表信息
                && sysRoleMenuService.saveOrUpdateRoleMenu(entity, entity.getMenuList())
                //添加角色-明细设置部门范围
                && sysRoleOrgService.saveOrUpdateRoleOrg(entity, entity.getOrgList());
        return flag;
    }

    /**
     * 注入角色-菜单表
     */
    private final SysRoleMenuService sysRoleMenuService;
    /**
     * 注入角色-部门
     */
    private final SysRoleOrgService sysRoleOrgService;
    /**
     * 注入菜单
     */
    private final SysMenuService sysMenuService;
    /**
     * 注入部门
     */
    private final SysOrgService sysOrgService;
}
