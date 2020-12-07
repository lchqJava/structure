package org.structure.boot.oauth.repository.mapper;

import org.structure.boot.oauth.pojo.po.OauthAuthority;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;


/**
 * <p>
 * 权限表（管理员） Mapper 接口
 * </p>
 *
 * @author chuck
 * @since 2020-11-12
 */
public interface OauthAuthorityMapper {

    /**
     * 查询用户的权限
     * @param userId
     * @return
     */
    @Results(id = "authorityResult",value = {
            @Result(property = "isMenu",column = "is_menu",jdbcType = JdbcType.TINYINT),
            @Result(property = "createTime",column = "create_time",jdbcType = JdbcType.DATE),
            @Result(property = "updateTime",column = "update_time",jdbcType = JdbcType.DATE),
    })
    @ResultMap("authorityResult")
    @Select("SELECT oaa.name ,oaa.`value` FROM oauth_user_role_mapping ourm \n" +
            "JOIN oauth_role oar ON oar.id = ourm.role_id AND oar.is_deleted = 0 AND is_enable = 1\n" +
            "JOIN oauth_user_role_authority_mapping oram ON oram.role_id = ourm.id \n" +
            "JOIN oauth_authority oaa ON oaa.id = oram.authority_id \n" +
            "WHERE ourm.user_id = #{userId}")
    List<OauthAuthority> findUserAuthorityByUserId(@Param("userId") String userId);



}
