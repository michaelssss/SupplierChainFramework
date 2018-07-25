package com.jzqh.account.accessmanagement;

import com.jzqh.account.User;
import com.jzqh.account.UserImpl;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;

import java.util.Collection;

public class URLVoter implements AccessDecisionVoter<Object> {
    @Override
    public boolean supports(ConfigAttribute attribute) {
        return null != attribute;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

    private boolean isHttpRequest(Object o) {
        return o instanceof FilterInvocation;
    }

    /**
     * 用于校验操作用户是否具有操作当前URL的权限
     * 验证规则，如果前面URL包含了Authority.getAuthority()则验证通过
     *
     * @param authentication 操作者
     * @param object         操作对象
     * @param attributes     相关配置
     * @return
     */
    @Override
    public int vote(Authentication authentication, Object object, Collection<ConfigAttribute> attributes) {
        if (null == authentication || !this.isHttpRequest(object)) {
            return ACCESS_DENIED;
        }
        FilterInvocation filterInvocation = (FilterInvocation) object;
        int result;
        if (authentication.getPrincipal() instanceof UserImpl) {
            User user = (UserImpl) authentication.getPrincipal();
            String requestString = filterInvocation.getRequestUrl();
            result = user.hasAuthority(requestString) ? ACCESS_GRANTED : ACCESS_DENIED;
            return result;
        }
        return ACCESS_ABSTAIN;
    }
}
