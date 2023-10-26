package com.fantasy.brace.applicable.organism;

/**
 * 人类
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

    public AbstractHuman(int humanId) {
        this.humanId = humanId;
    }

}
