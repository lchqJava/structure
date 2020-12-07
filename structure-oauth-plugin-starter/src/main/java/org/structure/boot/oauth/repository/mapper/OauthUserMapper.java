package org.structure.boot.oauth.repository.mapper;

import org.structure.boot.oauth.pojo.po.OauthUser;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author chuck
 * @since 2020-11-12
 */
public interface OauthUserMapper {

    @Results(id = "userResult", value = {
            @Result(column = "username", property = "username", jdbcType = JdbcType.VARCHAR),
            @Result(column = "is_enabled", property = "enable", jdbcType = JdbcType.TINYINT),
            @Result(column = "is_unlocked", property = "unlocked", jdbcType = JdbcType.TINYINT),
            @Result(column = "is_deleted", property = "deleted", jdbcType = JdbcType.TINYINT),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.DATE),
    })
    @Select("SELECT id,username,password,is_unexpired,is_enabled,is_unlocked,is_deleted,create_time " +
            "FROM oauth_user WHERE username = #{username} AND is_deleted = 0")
    OauthUser findUserByUsername(@Param("username") String username);

}
