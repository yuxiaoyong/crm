package com.xiaoyong.hrm.system.service.impl;/**
 * Created by atlantisholic on 2018/9/10.
 */

import com.xiaoyong.hrm.support.service.BaseServiceImpl;
import com.xiaoyong.hrm.system.domain.SysMenu;
import com.xiaoyong.hrm.system.service.SysMenuService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName SysMenuServiceImpl
 * @Description TODO
 * @Author 郁晓勇
 * @Date 2018/9/10 21:38
 * @Version 1.0.0
 **/
@Service
public class SysMenuServiceImpl extends BaseServiceImpl<SysMenu, Integer> implements SysMenuService, InitializingBean {

    @Transactional
    @Override
    public void afterPropertiesSet() throws Exception {
        SysMenu sysMenu = super.findById(SysMenu.ROOT_MENU_ID);
        if(sysMenu != null){
            return;
        }
        sysMenu = new SysMenu();
        sysMenu.setId(SysMenu.ROOT_MENU_ID);
        sysMenu.setName("MENU_ROOT");
        sysMenu.setCode("MENU_ROOT");
        sysMenu.setVisible(false);
        super.save(sysMenu);
    }

}
