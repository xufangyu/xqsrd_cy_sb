package com.yemh.xqsrd.account.pojo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author yemh
 * @date 2018/04/14
 */
public class XUser implements UserDetails {
    
    private static final long serialVersionUID = 1L;
    
    private Long xId;
    private String username;
    private String password;
    private String loginName;
    private String mobile;
    private String email;
    private String loginTime;
    private String lastLoginTime;
    
    private List<XRoles> roles;

    /** 
     * 从角色中获得所有权限,
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
        List<XRoles> roles = this.getRoles();
        for (XRoles xRoles : roles) {
            auths.add(new SimpleGrantedAuthority(xRoles.getName()));
        }
        return auths;
    }


    /* (non-Javadoc)
     * @see org.springframework.security.core.userdetails.UserDetails#getPassword()
     */
    @Override
    public String getPassword() {
        return this.password;
    }

    /* (non-Javadoc)
     * @see org.springframework.security.core.userdetails.UserDetails#getUsername()
     */
    @Override
    public String getUsername() {
        return this.username;
    }

    /* (non-Javadoc)
     * @see org.springframework.security.core.userdetails.UserDetails#isAccountNonExpired()
     */
    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    /* (non-Javadoc)
     * @see org.springframework.security.core.userdetails.UserDetails#isAccountNonLocked()
     */
    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }

    /* (non-Javadoc)
     * @see org.springframework.security.core.userdetails.UserDetails#isCredentialsNonExpired()
     */
    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    /* (non-Javadoc)
     * @see org.springframework.security.core.userdetails.UserDetails#isEnabled()
     */
    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return true;
    }


    public Long getxId() {
        return xId;
    }


    public void setxId(Long xId) {
        this.xId = xId;
    }


    public List<XRoles> getRoles() {
        //手工设置一个role
        XRoles xr = new XRoles();
        xr.setName("USER");
        roles = new ArrayList<>();
        roles.add(xr);
        return roles;
    }


    public void setRoles(List<XRoles> roles) {
        this.roles = roles;
    }


    public void setUsername(String username) {
        this.username = username;
    }


    public void setPassword(String password) {
        this.password = password;
    }


    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    @Override
    public String toString() {
        return "XUser [xId=" + xId + ", username=" + username + ", loginName=" + loginName + ", password=" + password
            + ", roles=" + roles + "]";
    }


    public String getMobile() {
        return mobile;
    }


    public void setMobile(String mobile) {
        this.mobile = mobile;
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public String getLoginTime() {
        return loginTime;
    }


    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }


    public String getLastLoginTime() {
        return lastLoginTime;
    }


    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }
}
