package com.hxr.springbootdemo.elastics;

import com.hxr.springbootdemo.entity.PlayerBean;
/*import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;*/

import java.util.List;

public interface PlayerEsRepository /*extends ElasticsearchRepository<PlayerBean,Integer>*/ {


    List<PlayerBean> findPlayerBeanByPlayerNameContaining(String playerName);

}
