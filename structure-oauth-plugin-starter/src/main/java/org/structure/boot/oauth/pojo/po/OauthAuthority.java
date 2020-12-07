package org.structure.boot.oauth.pojo.po;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Transient;
import org.springframework.security.core.GrantedAuthority;

/**
 * <p>
 * 权限表（管理员）
 * </p>
 *
 * @author chuck
 * @since 2020-11-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class OauthAuthority implements Serializable, GrantedAuthority, Comparable<OauthAuthority> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId("id")
    private String id;

    /**
     * 权限名
     */
    @TableField("name")
    private String name;

    /**
     * 权限值
     */
    @TableField("value")
    private String value;

    /**
     * 父ID
     */
    @TableField("pid")
    private String pid;

    /**
     * 是否菜单 1:  是  0 :否
     */
    @TableField("is_menu")
    private Boolean menu;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 修改时间
     */
    @TableField(value = "update_time")
    private Date updateTime;

    @Transient
    private List<OauthAuthority> authorities;

    @Override
    public String getAuthority() {
        return this.value;
    }

    @Override
    public int compareTo(OauthAuthority o) {
        if (this.id.equals(o.getId())) {
            return 0;
        }
        return -1;
    }
}
