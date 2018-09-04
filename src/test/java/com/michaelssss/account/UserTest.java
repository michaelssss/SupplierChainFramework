package com.michaelssss.account;

import com.michaelssss.SpringBootTestBasic;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;

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
     * company：--1级节点
     * <p>
     * Address：--2级节点
     * delete --3级节点
     * queryAll --3级节点
     * <p>
     * BankAccount:--2级节点
     * add --3级节点
     * delete --3级节点
     * <p>
     * addAddress--2级节点
     * <p>
     * delete--2级节点
     * <p>
     * ShareHolder:--2级节点
     * add --3级节点
     * update --3级节点
     * <p>
     * Contacts:--2级节点
     * delete --3级节点
     * <p>
     * queryALL--2级节点
     * <p>
     * query:--2级节点
     * id --3级节点
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
        
        urlList.add("/user/ShareHolder/update");
        urlList.add("/Company/user/update");
        urlList.add("/Company/ShareHolder/user");
        urlList.add("/Company/User/update");
        urlList.add("/USEr/ShareHolder/update");
    
        for (int i = 0; i < urlList.size(); i++) {
            if (urlList.get(i).toLowerCase().contains("user")) {//去除所有路径带有user的url
                urlList.remove(i);
            }
        }
        for(String url :urlList){
            System.out.println(url);
        }
        MenuUtil.urlToMenu(urlList,null);
    }

}
