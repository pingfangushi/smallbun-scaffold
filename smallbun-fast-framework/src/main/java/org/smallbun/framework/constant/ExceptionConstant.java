/*
 *
 *  * Copyright(c)[2018] [smallbun] www.smallbun.org
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *     http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package org.smallbun.framework.constant;

/**
 * 错误代码定义
 * @author SanLi
 * Created by 2689170096@qq.com on 2018/10/11
 */
public class ExceptionConstant {

	/**
	 * 拦截器不通过
	 */
	public static final String EX999999 = "999999";
	/**
	 * 参数校验失败(为空、错值提示)
	 */
	public static final String EX900000 = "900000";

	/**
	 * 系统异常
	 */
	public static final String EX900001 = "900001";
	/**
	 * 获取配置信息错误
	 */
	public static final String EX900002 = "900002";
	/**
	 * 参数校验错误
	 */
	public static final String EX900003 = "900003";
	/**
	 * 未定义错误消息
	 */
	public static final String EX900004 = "900004";
	/**
	 * 数字签名校验错误
	 */
	public static final String EX900005 = "900005";
	/**
	 * 参数类型不对
	 */
	public static final String EX900006 = "900006";
	/**
	 * APP客户端连接超时
	 */
	public static final String EX888888 = "888888";
	/**
	 * APP客户端异地登陆
	 */
	public static final String EX888889 = "888889";
	/**
	 * APP客户端异地登陆
	 */
	public static final String EX888887 = "888887";


	/**
	 * 商户未认证
	 */
	public static final String EX000001 = "000001";

	/**
	 * 登陆错误次数超限，商户被冻结
	 */
	public static final String EX000101 = "000101";
	/**
	 * 密码错误!
	 */
	public static final String EX000102 = "000102";
	/**
	 * 登录失败：更新登陆信息异常！
	 */
	public static final String EX000103 = "000103";

	/**
	 * 商户注册失败：1.商户注册异常! 2.商户注册失败!
	 */
	public static final String EX000201 = "000201";
	/**
	 * 商户已被注册！
	 */
	public static final String EX000202 = "000202";
	/**
	 * 商户信息修改失败：1.商户修改或找回密码异常! 2.商户修改或找回密码失败 3.该密码类型不存在 4.实名认证更新失败
	 */
	public static final String EX000205 = "000205";
	/**
	 * 1.商户校验异常！ 2.商户不存在
	 */
	public static final String EX000206 = "000206";
	/**
	 * 商户查询失败
	 */
	public static final String EX000207 = "000207";
	/**
	 * 1.商户账户查询失败 2、商户账户更新失败 3、商户账户新增失败
	 */
	public static final String EX000208 = "000208";
	/**
	 * 1、商户账户查询异常 2、商户账户更新异常 3、商户账户新增异常
	 */
	public static final String EX000212 = "000212";
	/**
	 * 1.商户账户历史查询失败 2、商户账户历史更新失败 3、商户账户历史新增失败
	 */
	public static final String EX000213 = "000213";
	/**
	 * 1、商户账户历史查询异常 2、商户账户历史更新异常 3、商户账户历史新增异常
	 */
	public static final String EX000214 = "000214";
	/**
	 * 商户已被禁用
	 */
	public static final String EX000215 = "000215";
	/**
	 * 绑定银行卡信息失败 : 1.失败和 银行卡信息查询异常 2.银行卡信息修改异常 3. 银行卡信息解绑异常
	 */
	public static final String EX000209 = "000209";
	/**
	 * 信息已在审核中： 银行卡信息已被编辑更新，请等待审核后修改
	 */
	public static final String EX000210 = "000210";
	/**
	 * 卡号不存在
	 */
	public static final String EX000211 = "000211";

	/**
	 * 查询银行卡失败
	 */
	public static final String EX000200 = "000200";
	/**
	 * 银行卡修改失败
	 */
	public static final String EX000199 = "000199";
	/**
	 * 当前为认证卡T0提现金额不足
	 */
	public static final String EX000197 = "000197";
	/**
	 * 银行卡删除失败
	 */
	public static final String EX000198 = "000198";

	/**
	 * 银行卡类型限定为贷记卡
	 */
	public static final String EX000196 = "000196";

	/**
	 * 银行卡未绑定或不存在
	 */
	public static final String EX000217 = "000217";
	/**
	 * 银行卡已被商户绑定
	 */
	public static final String EX000222 = "000222";
	/**
	 * 银行卡类型限定为借记卡
	 */
	public static final String EX000223 = "000223";
	/**
	 * 联行号异常：1联行号列表获取异常，2银行查询异常
	 */
	public static final String EX000224 = "000224";
	/**
	 * 银行名查询失败
	 */
	public static final String EX000225 = "000225";
	/**
	 * {账户/钱包}余额不足。
	 */
	public static final String EX000218 = "000218";
	/**
	 * 商户账户不存在
	 */
	public static final String EX000219 = "000219";

	/**
	 * 不支持该卡片
	 */
	public static final String EX000220 = "000220";

	/**
	 * 无效手机号！
	 */
	public static final String EX000250 = "000250";

	/**
	 * 商户被禁用,请联系客服！
	 */
	public static final String EX000251 = "000251";
	/**
	 * 登陆密码被冻结,请联系客服！
	 */
	public static final String EX000252 = "000252";

	/**
	 * 获取信息失败: 获取省信息失败 获取市信息失败 ...
	 */
	public static final String EX001008 = "001008";

	/**
	 * 获取验证码失败
	 */
	public static final String EX000411 = "000411";
	/**
	 * 验证码验证失败
	 */
	public static final String EX000412 = "000412";

	/**
	 * 商户信息已存在
	 */
	public static final String EX000600 = "000600";
	/**
	 * 商户信息不存在
	 */
	public static final String EX000601 = "000601";

	/*************************************
	 * 代理商业务 start
	 *************************************

	 /**
	 * 代理商查询异常
	 */
	public static final String EX000500 = "000500";
	/**
	 * 代理商已被禁用
	 */
	public static final String EX000501 = "000501";
	/**
	 * 代理商已被冻结
	 */
	public static final String EX000502 = "000502";
	/**
	 * 代理商不存在
	 */
	public static final String EX000503 = "000503";
	/**
	 * 未绑定代理商.
	 */
	public static final String EX000510 = "000510";


	/************************************
	 * 代理商业务 end
	 ************************************
	 /**
	 * 业务不可用 ： 路由已停用 ，查询路由信息异常（终端或快捷或便民）
	 */
	public static final String EX000913 = "000913";

	/**
	 * 终端不可用(查无数据，未绑定终端，终端信息状态校验失败)
	 */
	public static final String EX000301 = "000301";
	/**
	 * 终端绑定失败(更新)
	 */
	public static final String EX000303 = "000303";

	/**
	 * 终端工作密钥更新失败
	 */
	public static final String EX000304 = "000304";

	/**
	 * 查询app版本信息失败
	 */
	public static final String EX000701 = "000701";

	/**
	 * 交易失败
	 */
	public static final String EX000801 = "000801";

	/**
	 * 激活码已使用
	 */
	public static final String EX000305 = "000305";
	/**
	 * 无效激活码
	 */
	public static final String EX000306 = "000306";
	/**
	 * 激活失败,请联系客服。
	 */
	public static final String EX000307 = "000307";

	/**
	 * 输入错误,请在｛｝分钟后重新输入。
	 */
	public static final String EX000308 = "000308";

	/**
	 * 单笔消费金额必须在{}~{}之间
	 */
	public static final String EX000811 = "000811";
	/**
	 * 单卡单日消费不得超过{}次
	 */
	public static final String EX000812 = "000812";

	/**
	 * {}累计收款金额不得超过{}元
	 */
	public static final String EX000813 = "000813";

	/**
	 * 交易流水业务异常
	 */
	public static final String EX000814 = "000814";
	/**
	 * 交易信息新增失败
	 */
	public static final String EX000815 = "000815";
	/**
	 * 获取账户余额异常
	 */
	public static final String EX000816 = "000816";
	/**
	 * 获取账户余额失败
	 */
	public static final String EX000817 = "000817";
	/**
	 * 账户操作失败。
	 */
	public static final String EX000818 = "000818";

	/**
	 * 提现金额不得小于{}元
	 */
	public static final String EX000808 = "000808";

	/**
	 * 交易金额有误
	 */
	public static final String EX000819 = "000819";
	/**
	 * 文件存储失败!
	 */
	public static final String EX001001 = "001001";

	/**
	 * 卡BIN信息获取失败！
	 */
	public static final String EX001002 = "001002";

	/**
	 * 电子签名上传失败！
	 */
	public static final String EX001003 = "001003";

	/**
	 * 未找到电子签名记录！
	 */
	public static final String EX001004 = "001004";

	/**
	 * 轮播图获取失败！
	 */
	public static final String EX001005 = "001005";

	/**
	 * uu卡图片上传失败
	 */
	public static final String EX001006 = "001006";
	/**
	 * 短信验证码获取失败！
	 */
	public static final String EX001101 = "001101";

	/**
	 * 短信校验码错误！
	 */
	public static final String EX001102 = "001102";

	/**
	 * 短信校验码已过期！
	 */
	public static final String EX001103 = "001103";

	/**
	 * 短信发送模版获取失败！
	 */
	public static final String EX001104 = "001104";

	/**
	 * 短信发送失败！
	 */
	public static final String EX001105 = "001105";

	/**
	 * 短信发送记录保存失败！
	 */
	public static final String EX001106 = "001106";

	/**
	 * 更新短信发送状态失败！
	 */
	public static final String EX001107 = "001107";

	/**
	 * 磁道数据错误。
	 */
	public static final String EX100101 = "100101";

	/**
	 * 计算MAC失败
	 */
	public static final String EX100201 = "100201";
	/**
	 * 转加密PIN失败
	 */
	public static final String EX100202 = "100202";
	/**
	 * 生成密钥失败（终端主密钥、工作密钥、MACKEY转本地主密钥失败、PINKEY转本地主密钥失败）
	 */
	public static final String EX100203 = "100203";

	/**
	 * 密钥分算失败
	 */
	public static final String EX100208 = "100208";
	/**
	 * 数据解密失败
	 */
	public static final String EX100209 = "100209";

	/**
	 * 通道方签到失败
	 */
	public static final String EX100205 = "100205";

	/**
	 * 通道方签到失败,密钥校验不通过
	 */
	public static final String EX100206 = "100206";

	/**
	 * 交易失败(发送第三方异常)
	 */
	public static final String EX100301 = "100301";
	/**
	 * 交易超时 接收第三方系统数据超时
	 */
	public static final String EX100302 = "100302";

	/**
	 * 交易失败(快捷发送第三方失败)
	 */
	public static final String EX100401 = "100401";
	/**
	 * 交易超时(快捷发送第三方连接失败)
	 */
	public static final String EX100402 = "100402";

	/**
	 * 交易失败(便民业务发送失败)
	 */
	public static final String EX100501 = "100501";
	/**
	 * 交易失败(便民业务服务器连接失败)
	 */
	public static final String EX100502 = "100502";

	/**
	 * 终端业务 终端不可用
	 */
	public static final String EX030001 = "030001";

	/**
	 * 终端业务 不能跨代理商绑定终端
	 */
	public static final String EX030002 = "030002";

	/**
	 * 终端业务 终端已绑定
	 */
	public static final String EX030003 = "030003";

	/**
	 * 商品订单不存在
	 */
	public static final String EX080001 = "080001";

	/**
	 * 支付金额与商品订单金额不一致
	 */
	public static final String EX080002 = "080002";

	/**
	 * 创建支付订单失败
	 */
	public static final String EX080003 = "080003";
	/**
	 * 商品订单已支付,请重新下单
	 */
	public static final String EX080004 = "080004";
	/**
	 * 支付密码错误
	 */
	public static final String EX080005 = "080005";
	/**
	 * 商品订单处理中
	 */
	public static final String EX080006 = "080006";
	/**
	 * 支付密码不能为空
	 */
	public static final String EX080007 = "080007";

	/**
	 * 提现订单异常
	 */
	public static final String EX060000 = "060000";
	/**
	 * 提现订单创建失败
	 */
	public static final String EX060001 = "060001";
	/**
	 * 费率查询失败 异常
	 */
	public static final String EX060002 = "060002";

	/**
	 * 通道单笔消费金额必须在{}~{}之间
	 */
	public static final String EX000821 = "000821";
	/**
	 * 通道单日消费不得超过{}元
	 */
	public static final String EX000822 = "000822";
	/**
	 * 通道单月消费不得超过{}元
	 */
	public static final String EX000823 = "000823";

	/**
	 * 通道限额检查异常
	 */
	public static final String EX000825 = "000825";
	/**
	 * 通道限额添加异常
	 */
	public static final String EX000826 = "000826";

	/**
	 * 无效推荐人
	 */
	public static final String EX000221 = "000221";

	/**
	 * 无效推荐人 推荐人未认证。
	 */
	public static final String EX000255 = "000255";

	/**
	 * 无效金额
	 */
	public static final String EX200313 = "200313";
	/**
	 * 无效卡号
	 */
	public static final String EX200314 = "200314";
	/**
	 * 无效应答
	 */
	public static final String EX200320 = "200320";
	/**
	 * 作弊卡
	 */
	public static final String EX200334 = "200334";
	/**
	 * 无效交易
	 */
	public static final String EX200345 = "200345";
	/**
	 * 密码错误次数超限
	 */
	public static final String EX200338 = "200338";
	/**
	 * 发卡方不支持的交易
	 */
	public static final String EX200340 = "200340";
	/**
	 * 此卡被没收
	 */
	public static final String EX200343 = "200343";
	/**
	 * 资金不足（可用余额不足）
	 */
	public static final String EX200351 = "200351";
	/**
	 * 该卡已过期
	 */
	public static final String EX200354 = "200354";
	/**
	 * 密码错误
	 */
	public static final String EX200355 = "200355";
	/**
	 * 卡片校验错误
	 */
	public static final String EX200359 = "200359";
	/**
	 * 交易金额超限
	 */
	public static final String EX200361 = "200361";
	/**
	 * 受限制的卡
	 */
	public static final String EX200362 = "200362";
	/**
	 * 超出取款次数限制
	 */
	public static final String EX200365 = "200365";
	/**
	 * 交易超时,请重试
	 */
	public static final String EX200368 = "200368";
	/**
	 * 密码错误次数超限
	 */
	public static final String EX200375 = "200375";
	/**
	 * 发卡方超时
	 */
	public static final String EX200398 = "200398";
	/**
	 * 发卡方超时
	 */
	public static final String EX200400 = "200400";
	/**
	 * PIN格式错误,请重新签到
	 */
	public static final String EX200399 = "200399";
	/**
	 * 交易次数超限
	 */
	public static final String EX200317 = "200317";
	/**
	 * 超过限额
	 */
	public static final String EX200318 = "200318";
	/**
	 * 芯片卡不能降级使用
	 */
	public static final String EX200333 = "200333";
	/**
	 * 其他错误（一般直接提示给用户看）
	 */
	public static final String EX200312 = "200312";
	/**
	 * MAC错误
	 */
	public static final String EX2003A0 = "2003A0";

	/**
	 * 转账失败
	 */
	public static final String EX000228 = "000228";
	/**
	 * 转账账号无效
	 */
	public static final String EX000227 = "000227";
	/**
	 * 收款账号无效
	 */
	public static final String EX000226 = "000226";


	/**
	 * 该银行卡已被限制交易
	 */
	public static final String EX000850 = "000850";
	/**
	 * 最低提现额不能小于{}元。
	 */
	public static final String EX000230 = "000230";
	/**
	 * 提现时间段为早上{}点至下午{}点。
	 */
	public static final String EX000231 = "000231";

	/**
	 * 提现订单查询失败
	 */
	public static final String EX060005 = "060005";

	/**
	 * 获取提现时间失败！
	 */
	public static final String EX060003 = "060003";

	/**
	 * 单笔交易提现金额不能小于{}元。
	 */
	public static final String EX001851 = "001851";

	/**
	 * 单笔交易金额不能小于{}元。
	 */
	public static final String EX000851 = "000851";

	/**
	 * 单笔交易金额不能大于{}元。
	 */
	public static final String EX000852 = "000852";

	/**
	 * 日交易金额不能大于{}元。
	 */
	public static final String EX000853 = "000853";
	/**
	 * 日交易次数不能超过{}笔。
	 */
	public static final String EX000854 = "000854";
	/**
	 * 月交易金额不能大于{}元。
	 */
	public static final String EX000855 = "000855";

	/**
	 * 月交易次数不能超过{}笔。
	 */
	public static final String EX000856 = "000856";

	/**
	 * 单笔提现金额不能小于{}元。
	 */
	public static final String EX000857 = "000857";

	/**
	 * 单笔提现金额不能大于{}元。
	 */
	public static final String EX000858 = "000858";

	/**
	 * 日提现金额不能大于{}元。
	 */
	public static final String EX000859 = "000859";
	/**
	 * 日提现次数不能超过{}笔。
	 */
	public static final String EX000860 = "000860";

	/**
	 * 黑名单卡
	 */
	public static final String EX000898 = "000898";

	/**
	 * 交易限额不通过。
	 */
	public static final String EX000899 = "000899";

	/**
	 * 无效金额
	 */
	public static final String EX000800 = "000800";

	/**
	 * 金额超限（超过系统最大处理金额）
	 */
	public static final String EX000802 = "000802";

	/**
	 * 余额账户可存储资金超限
	 */
	public static final String EX000803 = "000803";

	/**
	 * 交易时间段为早上{}点至下午{}点。
	 */
	public static final String EX000233 = "000233";

	/**
	 * T0交易关闭,请刷T1。
	 */
	public static final String EX008000 = "008000";
	/**
	 * T1交易关闭,请刷T0。
	 */
	public static final String EX008001 = "008001";
	/**
	 * T0&T1交易关闭。
	 */
	public static final String EX008002 = "008002";

	/**
	 * 代理商为T+0开启提现服务。
	 */
	public static final String EX006100 = "006100";
	/**
	 * 代理商为T+0资金池余额不足。
	 */
	public static final String EX006101 = "006101";

	/**
	 * 快速代付失败.
	 */
	public static final String EX090001 = "090001";

	/**
	 * 融宝代付失败.
	 */
	public static final String EX090002 = "090002";

	/**
	 * 快捷支付签到失败.
	 */
	public static final String EX090003 = "090003";

	/**
	 * 快捷支付获取订单流水失败.
	 */
	public static final String EX090004 = "090004";

	/**
	 * 快捷支付查询订单信息失败.
	 */
	public static final String EX090005 = "090005";

	/**
	 * 微信支付下单失败.
	 */
	public static final String EX090010 = "090010";
	/**
	 * 微信支付注册失败.
	 */
	public static final String EX090011 = "090011";
	/**
	 * 微信支付下载密钥失败.
	 */
	public static final String EX090012 = "090012";
	/**
	 * 微信支付查询订单失败.
	 */
	public static final String EX090013 = "090013";
	/**
	 * 微信支付异步回调解析错误.
	 */
	public static final String EX090014 = "090014";
	/**
	 * 微信支付验卡错误.
	 */
	public static final String EX090015 = "090015";

	/**
	 * app菜单查询失败
	 */
	public static final String EX010001 = "010001";

	public static final String EX200301 = "200301";
	public static final String EX200302 = "200302";
	public static final String EX200303 = "200303";
	public static final String EX200304 = "200304";
	public static final String EX200305 = "200305";
	public static final String EX200307 = "200307";
	public static final String EX200309 = "200309";
	public static final String EX200315 = "200315";
	public static final String EX200319 = "200319";
	public static final String EX200321 = "200321";
	public static final String EX200322 = "200322";
	public static final String EX200323 = "200323";
	public static final String EX200325 = "200325";
	public static final String EX200331 = "200331";
	public static final String EX200335 = "200335";
	public static final String EX200336 = "200336";
	public static final String EX200337 = "200337";
	public static final String EX200339 = "200339";
	public static final String EX200341 = "200341";
	public static final String EX200342 = "200342";
	public static final String EX200344 = "200344";
	public static final String EX200350 = "200350";
	public static final String EX200352 = "200352";
	public static final String EX200353 = "200353";
	public static final String EX200356 = "200356";
	public static final String EX200357 = "200357";
	public static final String EX200358 = "200358";
	public static final String EX200360 = "200360";
	public static final String EX200363 = "200363";
	public static final String EX200364 = "200364";
	public static final String EX200366 = "200366";
	public static final String EX200367 = "200367";
	public static final String EX200369 = "200369";
	public static final String EX200372 = "200372";
	public static final String EX200373 = "200373";
	public static final String EX200374 = "200374";
	public static final String EX200379 = "200379";
	public static final String EX200381 = "200381";
	public static final String EX200382 = "200382";
	public static final String EX200383 = "200383";
	public static final String EX200384 = "200384";
	public static final String EX200385 = "200385";
	public static final String EX200386 = "200386";
	public static final String EX200389 = "200389";
	public static final String EX200396 = "200396";

	/**
	 * 请求数据不能为空
	 */
	public static final String EX400000 = "400000";
	/**
	 * 当前商户未绑定该卡
	 */
	public static final String EX400100 = "400100";

	/**
	 * 该卡当前无任务
	 */
	public static final String EX400201 = "400201";

	/**
	 * 记录积分时错误提示
	 */
	public static final String EX500000 = "500000";

	/**
	 * 交易金额不得小于{}元
	 */
	public static final String EX000820 = "000820";

	/**
	 * 交易金额不得大于{}元
	 */
	public static final String EX000831 = "000831";

	/**
	 * 提现金额不得小于{}元
	 */
	public static final String EX000830 = "000830";

	/**
	 * 提现金额不得大于{}元
	 */
	public static final String EX000832 = "000832";

	/**
	 * UU卡查询失败
	 */
	public static final String EX120001 = "120001";

	/**
	 * UU卡已绑定或未下拨
	 */
	public static final String EX120002 = "120002";

	/**
	 * UU卡不存在
	 */
	public static final String EX120003 = "120003";

	/**
	 * UU卡已绑定失败
	 */
	public static final String EX120004 = "120004";
	/**
	 * 不能跨终端绑定UU卡
	 */
	public static final String EX120005 = "120005";

	/**
	 * UU卡账户查询失败
	 */
	public static final String EX120006 = "120006";
	/**
	 * UU卡商户未进行实名认证
	 */
	public static final String EX120007 = "120007";
	/**
	 * UU卡个人未进行实名认证
	 */
	public static final String EX120008 = "120008";

	/**
	 * UU卡未绑定
	 */
	public static final String EX120009 = "120009";
	/**
	 * UU卡开户信息查询失败
	 */
	public static final String EX120101 = "120101";

	/**
	 * UU卡商户未开通
	 */
	public static final String EX120102 = "120102";

	/**
	 * UU卡商户信息查询失败
	 */
	public static final String EX120103 = "120103";

	/**
	 * 该商户已开户
	 */
	public static final String EX120104 = "120104";
	/**
	 * 该商户未开户
	 */
	public static final String EX120109 = "120109";
	/**
	 * uu卡开户失败
	 */
	public static final String EX120105 = "120105";
	/**
	 * uu卡商户实名认证信息查询失败
	 */
	public static final String EX120106 = "120106";

	/**
	 * uu卡商户实名认证失败
	 */
	public static final String EX120107 = "120107";
	/**
	 * uu卡商户实名认证图片上传失败
	 */
	public static final String EX120108 = "120108";
	/**
	 * 商户信息修改失败
	 */
	public static final String EX120110 = "120110";

	/**
	 * uu卡商户执照信息查询失败
	 */
	public static final String EX120201 = "120201";

	/**
	 * uu卡商户执照已经存在
	 */
	public static final String EX120202 = "120202";
	/**
	 * uu卡商户执照不存在
	 */
	public static final String EX120203 = "120203";
	/**
	 * uu卡商户执照正在审核
	 */
	public static final String EX120204 = "120204";

	/**
	 * uu卡銀行卡不存在
	 */
	public static final String EX120301 = "120301";


	/**
	 * 实名信息认证不通过
	 */
	public static final String EX700000 = "700000";

	/**
	 * 身份证正面照片认证失败
	 */
	public static final String Ex700001 = "700001";

	/**
	 * 身份证背面照片认证失败
	 */
	public static final String Ex700002 = "700002";

	/**
	 * 个人储蓄卡认证失败
	 */
	public static final String Ex700003 = "700003";

	/**
	 * 绑定卡片类型错误,仅支持借记卡
	 */
	public static final String Ex700004 = "700004";
	/**
	 * 商户银联快捷支付进件失败
	 */
	public static final String Ex700005 = "700005";
	/**
	 * 未检测到商户信息
	 */
	public static final String Ex700006 = "700006";
	/**
	 * 银联支付接口异常，无法完成支付
	 */
	public static final String Ex700007 = "700007";
	/**
	 * 个人信用卡认证失败，无法完成绑定
	 */
	public static final String Ex700008 = "700008";

	/**
	 * 绑定卡片类型错误,仅支持信用卡
	 */
	public static final String Ex700009 = "700009";

	/**
	 * 商户未入驻，开通支付失败
	 */
	public static final String Ex700010 = "700010";


	/**
	 * 提现失败,{}
	 */
	public static final String Ex700011 = "700011";


	/**
	 * 请至少开通微信支付
	 */
	public static final String Ex700012 = "700012";
	/**
	 * 查询银联快捷商户审核状态信息失败
	 */
	public static final String Ex700013 = "700013";
	/**
	 * 请上传本人正面身份证照片
	 */
	public static final String Ex700014 = "700014";
	/**
	 * 请上传本人正面身份证照片
	 */
	public static final String Ex700015 = "700015";

	/**
	 * APP最后主动请求已超过配置时间，该订单已放弃支付
	 */
	public static final String Ex700016 = "700016";

	/**
	 * 该身份证号已被使用,请更换后重试
	 */
	public static final String Ex700017 = "700017";
	/**
	 * 该银行卡已被使用,请更换后重试
	 */
	public static final String Ex700018 = "700018";
	/**
	 * 修改手机号失败,请稍后重试
	 */
	public static final String Ex700019 = "700019";
	/**
	 * 该手机号已被使用,请更换后重试
	 */
	public static final String Ex700020 = "700020";
	/**
	 * 系统升级,暂时关闭返佣提现交易。
	 */
	public static final String Ex700021 = "700021";
	/**
	 * 系统升级,暂时关闭分润提现交易。
	 */
	public static final String Ex700022 = "700022";
	/**
	 * 本次请求无效,已被拦截。
	 */
	public static final String Ex700023 = "700023";
	/**
	 * 未设置默认使用收款账号,请联系管理员设置.
	 */
	public static final String Ex700024 = "700024";
	/**
	 * 线上开通会员暂时关闭,请线下开通.
	 */
	public static final String Ex700025 = "700025";

}
