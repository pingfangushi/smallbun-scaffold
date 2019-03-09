/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2019 ‭‭‭‭‭‭‭‭‭‭‭‭[smallbun] www.smallbun.org
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package org.smallbun.fast.manage.notify.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.google.common.collect.Lists;
import org.smallbun.fast.common.utils.AutoMapperUtil;
import org.smallbun.fast.manage.notify.dao.SysNotifyMapper;
import org.smallbun.fast.manage.notify.entity.SysNotifyEntity;
import org.smallbun.fast.manage.notify.entity.SysNotifyRecordEntity;
import org.smallbun.fast.manage.notify.service.SysNotifyService;
import org.smallbun.fast.manage.notify.vo.SysNotifyVO;
import org.smallbun.fast.manage.role.service.SysNotifyRecordService;
import org.smallbun.fast.manage.user.entity.SysUserEntity;
import org.smallbun.framework.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.smallbun.framework.constant.UrlPrefixConstant.UNIQUE;

/**
 * 通知公告实现类
 *
 * @author SanLi
 * Created by 2689170096@qq.com on 2019/2/14 19:24
 */
@Service
public class SysNotifyServiceImpl extends BaseServiceImpl<SysNotifyMapper, SysNotifyEntity>
    implements SysNotifyService {

    @Autowired
    public SysNotifyServiceImpl(SysNotifyRecordService notifyRecordService) {
        this.notifyRecordService = notifyRecordService;
    }

    /**
     * model
     *
     * @param request
     * @return
     */
    @Override
    public SysNotifyVO model(HttpServletRequest request) {
        if (!request.getRequestURI().contains(UNIQUE)) {
            return StringUtils.isEmpty(request.getParameter(ID)) ?
                new SysNotifyVO() :
                getById(request.getParameter(ID));
        }
        return new SysNotifyVO();
    }

    /**
     * 根据id获取一条记录
     *
     * @param id {@link Serializable}
     * @return {@link SysNotifyVO}
     */
    @Override
    public SysNotifyVO getById(Serializable id) {
        SysNotifyVO vo = AutoMapperUtil.mapping(super.getById(id), new SysNotifyVO());
        List<SysUserEntity> users = notifyRecordService.findUserByNotifyId(vo.getId());
        if (!CollectionUtils.isEmpty(users)) {
            //用户名
            StringBuilder names = new StringBuilder();
            users.forEach(u -> names.append(u.getUsername()).append(","));
            vo.setReceiverName(names.substring(0, names.length() - 1));
            //id
            StringBuilder ids = new StringBuilder();
            users.forEach(u -> ids.append(u.getId()).append(","));
            vo.setReceivers(ids.substring(0, ids.length() - 1));
        }
        return vo;
    }

	/**
	 * 根据用户id获取未阅读的通知公告
	 * @param userId
	 * @return
	 */
	@Override
	public List<SysNotifyEntity> findNotifyOnUnreadByUserId(Long userId) {
		return baseMapper.findNotifyOnUnreadByUserId(userId);
	}

	/**
     * 自定义更新
     *
     * @param vo
     * @return
     */
    @Override
    public boolean saveOrUpdate(SysNotifyVO vo) {
        //转义
        vo.setContent(HtmlUtils.htmlUnescape(vo.getContent()));
        //保存任务
        boolean saveOrUpdate = super.saveOrUpdate(vo);
        if (saveOrUpdate) {
            //封装 List<SysNotifyRecordEntity>
            List<SysNotifyRecordEntity> recordEntities = Lists.newArrayList();
            if (!StringUtils.isEmpty(vo.getReceivers()) && !StringUtils.isEmpty(vo.getReceivers().split(","))) {
                Arrays.stream(vo.getReceivers().split(",")).forEach(u -> {
                    SysNotifyRecordEntity entity = new SysNotifyRecordEntity();
                    entity.setNotifyId(vo.getId().toString());
                    entity.setUserId(u);
                    recordEntities.add(entity);
                });
            }
            //根据任务id删除
            notifyRecordService.delByNotifyId(vo.getId().toString());
            //批量保存
            saveOrUpdate = notifyRecordService.saveBatch(recordEntities);
        }
        return saveOrUpdate;
    }

    /**
     * 删除数据
     *
     * @param idList
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeByIds(Collection<? extends Serializable> idList) {
        idList.forEach(u -> notifyRecordService.remove(new LambdaQueryWrapper<SysNotifyRecordEntity>().eq(SysNotifyRecordEntity::getNotifyId, u)));
        return super.removeByIds(idList);
    }

    /**
     * 注入公告和用户关联service
     */
    private final SysNotifyRecordService notifyRecordService;
}
