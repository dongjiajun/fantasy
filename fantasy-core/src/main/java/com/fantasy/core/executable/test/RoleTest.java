package com.fantasy.core.executable.test;

import com.fantasy.brace.applicable.organism.HumanPlayer;

/**
 * 角色测试类
 *
 * @author DongJiaJun
 */
public class RoleTest {

    public static void main(String[] args) {
        HumanPlayer humanPlayer = new HumanPlayer(10001,"HaHa", (byte) 0x01, 0x00000000, "undefined plat");
        humanPlayer.connectTo("localhost", 7001);
        System.out.println(humanPlayer.getName());
    }
}
