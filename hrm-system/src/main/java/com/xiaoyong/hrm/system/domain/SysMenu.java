package com.xiaoyong.hrm.system.domain;/**
 * Created by atlantisholic on 2018/9/6.
 */

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.xiaoyong.hrm.support.domain.BaseEntity;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

/**
 * @ClassName SysMenu
 * @Description TODO
 * @Author 郁晓勇
 * @Date 2018/9/6 21:57
 * @Version 1.0.0
 **/
@Entity
@Table(name = "sys_menu")
public class SysMenu extends BaseEntity {

    public static final Integer ROOT_MENU_ID = 1;

    //菜单名称
    private String name;
    //菜单代码
    private String code;
    //菜单路径
    private String path;
    //菜单目标
    private String target;
    //是否是外部菜单
    private Boolean external = Boolean.FALSE;
    //菜单图标
    private String iconClass;
    //权限标识
    private String permission;
    //是否可见
    private Boolean visible = Boolean.TRUE;
    //父级菜单
    private SysMenu parent;
    //子级菜单
    private List<SysMenu> children;

    @Column(name = "name", length = 50, nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "code", length = 50, nullable = false)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Column(name = "path", length = 200)
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Column(name = "target", length = 50)
    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    @Column(name = "icon_class", length = 100, nullable = true)
    public String getIconClass() {
        return iconClass;
    }

    public void setIconClass(String iconClass) {
        this.iconClass = iconClass;
    }

    @Column(name = "is_visible", nullable = false)
    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    @Column(name = "permission", length = 100)
    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    @Column(name = "is_external", nullable = false)
    public Boolean getExternal() {
        return external;
    }

    public void setExternal(Boolean external) {
        this.external = external;
    }

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "parent_id")
    public SysMenu getParent() {
        return parent;
    }

    public void setParent(SysMenu parent) {
        this.parent = parent;
    }

    @OrderBy("order asc")
    @Where(clause = "is_deleted = false")
    @OneToMany(mappedBy = "parent")
    public List<SysMenu> getChildren() {
        return children;
    }

    public void setChildren(List<SysMenu> children) {
        this.children = children;
    }

}
