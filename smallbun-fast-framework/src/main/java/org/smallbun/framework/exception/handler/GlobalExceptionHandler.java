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

package org.smallbun.framework.exception.handler;

import org.smallbun.framework.constant.SystemConstant;
import org.smallbun.framework.result.AjaxResult;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

import static org.smallbun.framework.constant.ExceptionConstant.EX900001;


/**
 * 全局异常处理
 *
 * @author SanLi
 * Created by 2689170096@qq.com on 2018/10/9
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * URL
     */
    private static final String URL = "url";
    /**
     * 错误原因
     */
    private static final String EXCEPTION = "exception";
    /**
     * 消息信息
     */
    private static final String MESSAGE = "message";
    /**
     * 堆栈信息
     */
    private static final String STACK_TRACE = "stackTrace";
    /**
     * JSON头
     */
    private static final String APPLICATION_JSON = "application/json";
    /**
     * XML http请求
     */
    private static final String XML_HTTP_REQUEST = "XMLHttpRequest";

    /**
     * 默认异常处理
     *
     * @param req {@link HttpServletRequest}
     * @param e   {@link Exception}
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public Object defaultErrorHandler(HttpServletRequest req, Exception e) {
        //返回Ajax错误
        if (isAjaxReq(req)) {
            return AjaxResult.builder().msg(StringUtils.isEmpty(e.getMessage()) ? Arrays.toString(e.getStackTrace()) : e.getMessage()).status(EX900001).build();
        }
        //非Ajax ,返回ModelAndView
        else {
            ModelAndView modelAndView = getModelAndView(req, e);
            modelAndView.setViewName(SystemConstant.DEFAULT_ERROR_VIEW);
            return modelAndView;
        }
    }

    /**
     * 没有访问权限
     *
     * @param req {@link HttpServletRequest}
     * @param e   {@link AccessDeniedException}
     * @return {@link Object}
     */
    @ExceptionHandler(value = AccessDeniedException.class)
    public Object methodAccessDeniedException(HttpServletRequest req, AccessDeniedException e) {
        //403
        if (isAjaxReq(req)) {
            return AjaxResult.builder().msg(e.getMessage()).status(String.valueOf(HttpStatus.FORBIDDEN.value()))
                .build();
        }
        //非Ajax ,返回ModelAndView
        else {
            ModelAndView modelAndView = getModelAndView(req, e);
            modelAndView.setViewName(SystemConstant.NO_ACCESS);
            return modelAndView;
        }
    }


    /**
     * 参数验证异常
     *
     * @param exception {@link MethodArgumentNotValidException}
     * @return {@link Object}
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Object methodArgumentNotValidHandler(MethodArgumentNotValidException exception) {
        return baseBindException(exception.getBindingResult());
    }

    /**
     * 参数验证异常
     *
     * @param exception {@link BindException}
     * @return {@link Object}
     */
    @ExceptionHandler(value = BindException.class)
    public Object BindExceptionValidHandler(BindException exception) {
        return baseBindException(exception.getBindingResult());
    }

    /**
     * 参数绑定异常公共处理方法
     *
     * @param bindingResult
     * @return
     */
    private Object baseBindException(BindingResult bindingResult) {
        StringBuilder buffer = new StringBuilder();
        //解析原错误信息，封装后返回，此处返回非法的字段名称，原始值，错误信息
        for (FieldError error : bindingResult.getFieldErrors()) {
            buffer.append(error.getDefaultMessage()).append(",");
        }
        return AjaxResult.builder().msg(buffer.substring(0, buffer.length() - 1)).status(EX900001).build();
    }

    /**
     * 范围ModelAndView
     *
     * @param req {@link HttpServletRequest}
     * @param e   {@link Exception}
     * @return {@link Object}
     */
    private ModelAndView getModelAndView(HttpServletRequest req, Exception e) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(EXCEPTION, e);
        modelAndView.addObject(URL, req.getRequestURL());
        modelAndView.addObject(MESSAGE, e.getMessage());
        modelAndView.addObject(STACK_TRACE, e.getStackTrace());
        return modelAndView;
    }

    /**
     * 是否是ajax请求
     *
     * @param req {@link HttpServletRequest}
     * @return {@link Boolean}
     */
    private boolean isAjaxReq(HttpServletRequest req) {
        //解析原错误信息，封装后返回，此处返回非法的字段名称，原始值，错误信息
        //使用HttpServletRequest中的header检测请求是否为ajax, 如果是ajax则返回json, 如果为非ajax则返回view(即ModelAndView)
        String contentTypeHeader = req.getHeader("Content-Type");
        String acceptHeader = req.getHeader("Accept");
        String xRequestedWith = req.getHeader("X-Requested-With");
        return (contentTypeHeader != null && contentTypeHeader.contains(APPLICATION_JSON)) || (acceptHeader != null
            && acceptHeader.contains(APPLICATION_JSON)) || XML_HTTP_REQUEST.equalsIgnoreCase(xRequestedWith);
    }


}
