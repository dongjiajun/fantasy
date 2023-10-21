package com.fantasy.core.executable.test;

import com.fantasy.core.organism.HumanPlayer;

/**
 * 角色测试类
 *
 * @author DongJiaJun
 */
public class RoleTest {

    public static void main(String[] args) {
        HumanPlayer humanPlayer = new HumanPlayer();
        new HumanPlayer().connectTo("localhost", 7001);
        System.out.println(humanPlayer.getName());
    }
}
