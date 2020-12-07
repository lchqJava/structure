package org.structure.boot.oauth.logic.service.impl;

import org.structure.boot.oauth.enums.ErrCodeEnum;
import org.structure.boot.oauth.logic.service.IOauthUserService;
import org.structure.boot.oauth.pojo.po.OauthAuthority;
import org.structure.boot.oauth.pojo.po.OauthUser;
import org.structure.boot.oauth.repository.mapper.OauthAuthorityMapper;
import org.structure.boot.oauth.repository.mapper.OauthUserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.structure.boot.common.exception.CommonException;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author chuck
 * @since 2020-11-12
 */
@Slf4j
@Service
public class OauthUserServiceImpl implements IOauthUserService {

    @Resource
    private OauthUserMapper oauthUserMapper;

    @Resource
    private OauthAuthorityMapper oauthAuthorityMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        //查询用户信息
        OauthUser oauthUser = oauthUserMapper.findUserByUsername(s);
        //判断是否存在用户
        if (null == oauthUser) {
            throw new CommonException(ErrCodeEnum.ERR_NOT_USER.getCode(), ErrCodeEnum.ERR_NOT_USER.getMessage());
        }
        //验证用户是否需要查询权限
        if (oauthUser.getUnexpired()) {
            //用户过期
            throw new CommonException(ErrCodeEnum.ERR_UNEXPIRED.getCode(), ErrCodeEnum.ERR_UNEXPIRED.getMessage());
        }
        if (oauthUser.getUnlocked()) {
            //用户被锁定
            throw new CommonException(ErrCodeEnum.ERR_UNLOCKED.getCode(), ErrCodeEnum.ERR_UNLOCKED.getMessage());
        }
        if (!oauthUser.getEnable()) {
            //用户被禁用
            throw new CommonException(ErrCodeEnum.ERR_ENABLED.getCode(), ErrCodeEnum.ERR_ENABLED.getMessage());
        }
        //查询权限
        List<OauthAuthority> userAuthority = oauthAuthorityMapper.findUserAuthorityByUserId(oauthUser.getId());
        log.debug("获取启用权限集合{}", userAuthority);
        oauthUser.setAuthorities(userAuthority);
        return oauthUser;
    }
}
