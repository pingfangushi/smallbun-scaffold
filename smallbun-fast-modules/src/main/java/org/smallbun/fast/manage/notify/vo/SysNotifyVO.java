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

package org.smallbun.fast.manage.notify.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.smallbun.fast.manage.notify.entity.SysNotifyEntity;
import org.smallbun.fast.manage.notify.entity.SysNotifyRecordEntity;
import org.smallbun.fast.manage.user.entity.SysUserEntity;
import org.smallbun.framework.annotation.DictValue;

import java.util.List;

/**
 * 通知公告VO对象
 *
 * @author SanLi
 * Created by 2689170096@qq.com on 2019/2/21 22:33
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SysNotifyVO extends SysNotifyEntity {
    /**
     * 接收人集合
     */
    private List<SysUserEntity> receiverUser;
    /**
     * 通知公告-接收人关联
     */
    private List<SysNotifyRecordEntity> record;

    /**
     * 类型
     */
    @DictValue(typeCode = "NOTIFY_GENRE", valueField = "notifyGenre")
    private String notifyGenreName;

    /**
     * 状态
     */
    @DictValue(typeCode = "NOTIFY_STATUS", valueField = "notifyStatus")
    private String notifyStatusName;
}
