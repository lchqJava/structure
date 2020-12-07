package org.structure.boot.oauth.pojo.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Transient;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author chuck
 * @since 2020-11-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class OauthUser implements UserDetails, Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID （会员信息表的ID相同）
     */
    @TableId("id")
    private String id;

    /**
     * 用户名
     */
    @TableField("username")
    private String username;

    /**
     * 加密后的密码
     */
    @TableField("password")
    private String password;

    /**
     * 手机号用于登录
     */
    @TableField("phone")
    private String phone;
    /**
     * 是否过期 0：过期 1：未过期
     */
    @TableField("is_unexpired")
    private Boolean unexpired;

    /**
     * 是否启用 1:  启用 0:未启用
     */
    @TableField("is_enabled")
    private Boolean enable;

    /**
     * 是否锁定 0:  锁定 1:未锁定
     */
    @TableField("is_unlocked")
    private Boolean unlocked;
    /**
     * 是否删除 0：未删除 1：删除
     */
    @TableField("is_deleted")
    private Boolean deleted;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;

    @TableField("update_time")
    private Date updateTime;


    @Transient
    private List<OauthAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.unexpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.unlocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enable;
    }
}
