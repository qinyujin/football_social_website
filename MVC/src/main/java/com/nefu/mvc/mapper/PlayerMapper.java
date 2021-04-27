package com.nefu.mvc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nefu.mvc.entity.Player;
import org.apache.ibatis.annotations.Select;

/**
 * @author :覃玉锦
 * @create :2021-04-09 10:59:00
 */
public interface PlayerMapper extends BaseMapper<Player> {
    @Select("select player_num from player where player_num = #{num}")
    Player getPlayerByNum(int num);
}
