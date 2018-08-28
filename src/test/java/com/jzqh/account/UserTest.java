package com.jzqh.account;

import com.jzqh.SpringBootTestBasic;
import com.jzqh.scanner.RouteScanner;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;

import java.util.*;

@Slf4j
public class UserTest extends SpringBootTestBasic {
    Authority page;
    Authority pageload;
    @Autowired
    private UserCatalog catalog;
    @Autowired
    private AuthorityCatalog authorityCatalog;
    
    @Autowired
    private UserController userController;
    
    @Before
    public void before() {
        page = new Authority();
        page.setPath("/Pages");
        pageload = new Authority();
        pageload.setPath("/Pages/load");
        page = authorityCatalog.saveAndFlush(page);
        pageload = authorityCatalog.saveAndFlush(pageload);
    }
    
    @Test
    public void testStartAddRootUser() {
        UserImpl sample = UserImpl.builder().username("8888").build();
        Assert.assertNotNull(catalog.findOne(Example.of(sample)));
    }
    
    @Test
    public void testAuthority() {
        Authority authority = new Authority();
        authority.setPath("/Pages/load");
        UserImpl user = UserImpl.builder().username("8888").build();
        user = catalog.findOne(Example.of(user));
        user.authority(authority);
        Assert.assertTrue(user.hasAuthority(authority));
    }
    
    @Test
    public void testAuthorityOther() {
        User mockUser = UserImpl.builder().username("9999").password("1").authorities(new TreeSet<>()).authoritiesSets(new HashSet<>()).build();
        Authority authority = new Authority();
        authority.setPath("/Pages/load");
        UserImpl user = UserImpl.builder().username("8888").build();
        user = catalog.findOne(Example.of(user));
        user.authority(authority);
        user.authority(mockUser, authority);
        Assert.assertTrue(mockUser.hasAuthority(authority));
    }
    
    @Test
    public void testGetMenus() {
        UserImpl user = UserImpl.builder().username("8888").build();
        user = catalog.findOne(Example.of(user));
        user.authority(page);
        user.authority(pageload);
        Assert.assertTrue(user.getMenus().contains(page));
    }
    
    @Test
    public void testGetActions() {
        UserImpl user = UserImpl.builder().username("8888").build();
        user = catalog.findOne(Example.of(user));
        user.authority(page);
        user.authority(pageload);
        Assert.assertTrue(user.getActions().contains(pageload));
    }
    
    /**
     * 获取菜单测试；入参为11个url
     * 预设出参为：一个虚拟根节点，一个1级节点，8个2级节点，其中3个二级节点无3级节点，5个二级节点有3级节点，
     * 且3个二级节点有2个3节点
     * 具体如下：
     * root：
     * company：
     * Address：
     * delete
     * queryAll
     * BankAccount
     * add
     * delete
     * addAddress
     * delete
     * ShareHolder
     * add
     * update
     * Contacts
     * delete
     * queryALL
     * query
     * id
     */
    @Test
    public void testMenuTree() {
        List<String> urlList = new ArrayList<>();
        urlList.add("/Company/Address/delete");
        urlList.add("/Company/BankAccount/add");
        urlList.add("/Company/addAddress");
        urlList.add("/Company/delete");
        urlList.add("/Company/ShareHolder/add");
        urlList.add("/Company/BankAccount/delete");
        urlList.add("/Company/Contacts/delete");
        urlList.add("/Company/queryALL");
        urlList.add("/Company/Address/queryAll");
        urlList.add("/Company/query/id");
        urlList.add("/Company/ShareHolder/update");
        MenuBo test = userController.urlToMenu(urlList);
        test.getPath();
    }
    
}
