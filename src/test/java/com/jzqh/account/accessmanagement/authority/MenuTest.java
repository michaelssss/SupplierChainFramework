package com.jzqh.account.accessmanagement.authority;

import com.jzqh.rzzl2.SpringBootTestBasic;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.SortedSet;
import java.util.TreeSet;

public class MenuTest extends SpringBootTestBasic {
    @Autowired
    private MenuCatalog jpaRepository;

    @Test
//    @Transactional
    public void testBuildMenuTree() {
        Menu level1 = new Menu();
        level1.setUrl("/baseLevel");
        level1.setO(0);
        Menu level11 = new Menu();
        level11.setUrl("/nextLevel");
        level11.setO(1);
        Menu level12 = new Menu();
        level12.setUrl("/nextLevel2");
        level12.setO(2);
        SortedSet<Menu> menus = new TreeSet<>();
        menus.add(level11);
        menus.add(level12);
        level1.setSubMenus(menus);
        level1 = jpaRepository.saveAndFlush(level1);
        jpaRepository.delete(level1);
    }
}
