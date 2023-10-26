package com.fantasy.brace.applicable.organism;

import com.fantasy.brace.network.client.SimpleClient;
import com.fantasy.entity.constant.MapMaxBounds;

/**
 * 人类玩家类
 *
 * @author DongJiaJun
 */
public class HumanPlayer extends AbstractHuman {

    public HumanPlayer(int humanId, String name, byte state, int species, String site){
        super(humanId);

        setSite(site);
        setName(name);
        setState(state);
        setSpecies(species);

    }


    @Override
    public void setName(String name) {

        // TODO: 2022/4/6 合法性检测的逻辑

        super.setName(name);
    }

    @Override
    public void setSpecies(Object obj, int species) {
        if (isExistSpecies(getSpecies())) {
            return;
        }

        if (obj == null) {

            // TODO: 2021/12/12  根据种族，创建实际的查找生成器对象的逻辑

        }

        // TODO: 2021/12/12  根据查找生成器完成对应的要做的一些逻辑

        super.setSpecies(null, species);
    }

    @Override
    public String toString() {

        // TODO: 2021/12/16 实际的逻辑

        return "HumanPlayer{}";
    }


    /**
     * 内部可能要使用到的一些非公有的方法
     *
     * @param speciesId 种族id
     * @return 执行状态
     */
    private boolean isExistSpecies(int speciesId) {

        // TODO: 2021/12/12  查找逻辑并返回实际的判断结果

        //暂时用于测试的区间
        if (speciesId > 0) {
            return true;
        }
        return false;

    }
}
