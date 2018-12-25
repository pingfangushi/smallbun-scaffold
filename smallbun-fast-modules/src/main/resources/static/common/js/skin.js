/**
 * 布局皮肤
 */
$(function () {
    'use strict';

    /**
     * 访问插件
     */

    $('[data-toggle="control-sidebar"]').controlSidebar()
    $('[data-toggle="push-menu"]').pushMenu()
    var $pushMenu = $('[data-toggle="push-menu"]').data('lte.pushmenu')
    var $controlSidebar = $('[data-toggle="control-sidebar"]').data('lte.controlsidebar')
    var $layout = $('body').data('lte.layout')
    $(window).on('load', function () {
        // 重新初始化加载的变量
        $pushMenu = $('[data-toggle="push-menu"]').data('lte.pushmenu')
        $controlSidebar = $('[data-toggle="control-sidebar"]').data('lte.controlsidebar')
        $layout = $('body').data('lte.layout')
    });

    /**
     * 所有可用皮肤的列表
     *
     * @type Array
     */
    var mySkins = [
        'skin-blue',
        'skin-black',
        'skin-red',
        'skin-yellow',
        'skin-purple',
        'skin-green',
        'skin-blue-light',
        'skin-black-light',
        'skin-red-light',
        'skin-yellow-light',
        'skin-purple-light',
        'skin-green-light'
    ];

    /**
     * 获取预先存储的设置
     *
     * @param String 名称设置的名称
     * @returns string | null | null
     */
    function get(name) {
        if (typeof (Storage) !== 'undefined') {
            return localStorage.getItem(name)
        } else {
            window.alert('请使用现代浏览器正确查看此模板!')
        }
    }

    /**
     * 在浏览器中存储新的设置
     *
     * @param String 名称设置的名称
     * @param String val设置的值
     * @returns void
     */
    function store(name, val) {
        if (typeof (Storage) !== 'undefined') {
            localStorage.setItem(name, val)
        } else {
            window.alert('请使用现代浏览器正确查看此模板!')
        }
    }

    /**
     * 切换布局类
     *
     * @param String cls布局类切换
     * @returns void
     */
    function changeLayout(cls) {
        if (!$('body').hasClass(cls)) {
            $('body').removeClass('fixed');
            $('body').addClass('layout-boxed');
        } else {
            $('body').removeClass('layout-boxed');
            $('body').addClass('fixed');
        }
        //修复布局
        $controlSidebar.fix();
    }

    /**
     * 用新皮肤代替旧皮肤
     * @param String cls新的皮肤类
     * @returns Boolean 为了防止链接的默认操作为false
     */
    function changeSkin(cls) {
        $.each(mySkins, function (i) {
            $('body').removeClass(mySkins[i])
        });

        $('body').addClass(cls);
        store('skin', cls);
        return false
    }

    /**
     * 检索默认设置并将其应用于模板
     *
     * @returns void
     */
    function setup() {
        var tmp = get('skin');
        if ($.inArray(tmp, mySkins) !== -1) {
            changeSkin(tmp);
        }
        // 添加更改外观侦听器
        $('[data-skin]').on('click', function (e) {
            if ($(this).hasClass('knob'))
                return;
            e.preventDefault();
            changeSkin($(this).data('skin'))
        });

        // 添加布局管理器
        $('[data-layout]').on('click', function () {
            changeLayout($(this).data('layout'))
        });

        $('[data-controlsidebar]').on('click', function () {
            changeLayout($(this).data('controlsidebar'));
            var slide = !$controlSidebar.options.slide;

            $controlSidebar.options.slide = slide;
            if (!slide)
                $('.control-sidebar').removeClass('control-sidebar-open')
        });
        $('[data-sidebarskin="toggle"]').on('click', function () {
            var $sidebar = $('.control-sidebar');
            if ($sidebar.hasClass('control-sidebar-dark')) {
                $sidebar.removeClass('control-sidebar-dark');
                $sidebar.addClass('control-sidebar-light')
            } else {
                $sidebar.removeClass('control-sidebar-light');
                $sidebar.addClass('control-sidebar-dark')
            }
        });
        $('[data-enable="expandOnHover"]').on('click', function () {
            $(this).attr('disabled', true);
            $pushMenu.expandOnHover();
            if (!$('body').hasClass('sidebar-collapse'))
                $('[data-layout="sidebar-collapse"]').click()
        });
        //  重置选项
        if ($('body').hasClass('fixed')) {
            $('[data-layout="fixed"]').attr('checked', 'checked')
        }
        if ($('body').hasClass('layout-boxed')) {
            $('[data-layout="layout-boxed"]').attr('checked', 'checked')
        }
        if ($('body').hasClass('sidebar-collapse')) {
            $('[data-layout="sidebar-collapse"]').attr('checked', 'checked')
        }
    }

    /**
     * 检索默认设置并将其应用于模板
     */
    setup();
    $('[data-toggle="tooltip"]').tooltip()
});