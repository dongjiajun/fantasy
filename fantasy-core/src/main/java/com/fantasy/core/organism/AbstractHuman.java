package com.fantasy.core.organism;

/**
 * 抽象的人类类
 *
 * @author DongJiaJun
 */
public abstract class AbstractHuman extends Role {

    private int humanId;


    public int getHumanId() {
        return humanId;
    }

    public void setHumanId(int humanId) {
        this.humanId = humanId;
    }


}
