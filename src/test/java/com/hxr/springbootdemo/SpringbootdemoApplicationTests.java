package com.hxr.springbootdemo;

import com.hxr.springbootdemo.elastics.PlayerEsRepository;
import com.hxr.springbootdemo.entity.NewsBean;
import com.hxr.springbootdemo.entity.PlayerBean;
import io.searchbox.client.JestClient;
import io.searchbox.core.DocumentResult;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.ScriptQueryBuilder;
import org.elasticsearch.script.Script;
import org.elasticsearch.search.SearchHits;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.ResultsExtractor;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootdemoApplicationTests {

    @Autowired
    JestClient jestClient;

    @Autowired
    PlayerEsRepository playerEsRepository;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Test
    public void esRepoTest(){
        PlayerBean playerBean = new PlayerBean();
        playerBean.setId(1);
        playerBean.setPlayerName("James Paul");
        playerBean.setPlayerAge(28);
        playerBean.setPlayerScores(Stream.of(12,15,11).collect(Collectors.toList()));
        playerBean.setPlayerAssists(Stream.of(3,6,1).collect(Collectors.toList()));
        playerBean.setPlayerRebounds(Stream.of(5,10,7).collect(Collectors.toList()));

        playerEsRepository.index(playerBean);

    }

    @Test
    public void esRepoPainlessTest(){
        List<PlayerBean> playerBeans = playerEsRepository.findPlayerBeanByPlayerNameContaining("James");
        System.out.println(playerBeans.size());
        for (PlayerBean playerBean:playerBeans) {
            System.out.println(playerBean);
        }

    }


    @Test
    public void testPut() {
        NewsBean newsBean = new NewsBean();
        newsBean.setNewsAuthor("chris");
        newsBean.setNewsContent("chris is very sad");
        newsBean.setNewsTitle("today journey");
        newsBean.setNewsId(2);
        Index index = new Index.Builder(newsBean).index("hxrindex").type("news").build();

        try {
            DocumentResult result = jestClient.execute(index);
            assertEquals(result.isSucceeded(),true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSearch(){
        String searchQuery = "{\n" +
                "  \"query\": {\n" +
                "    \"bool\": {\n" +
                "      \"must\": [\n" +
                "        {\"match\": {\"newsContent\": \"chris\"}}\n" +
                "      ]\n" +
                "    }\n" +
                "  }\n" +
                "}";
        Search search = new Search.Builder(searchQuery).addIndex("hxrindex").build();
        try {
            SearchResult searchResult = jestClient.execute(search);
            if(!searchResult.isSucceeded()) {
                System.out.println(searchResult.getErrorMessage());
            }
            assertEquals(searchResult.isSucceeded(),true);
            NewsBean newsBean = searchResult.getSourceAsObject(NewsBean.class,false);
            System.out.println(searchResult.getJsonString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @Test
    public void testSearchList(){
        String searchQuery = "{\n" +
                "  \"query\": {\n" +
                "    \"match_all\": {}\n" +
                "  }\n" +
                "}";
        Search search = new Search.Builder(searchQuery).addIndex("nbaindex").build();
        try {
            SearchResult searchResult = jestClient.execute(search);
            if(!searchResult.isSucceeded()) {
                System.out.println(searchResult.getErrorMessage());
            }
            assertEquals(searchResult.isSucceeded(),true);
            List<PlayerBean> sourceAsObjectList = searchResult.getSourceAsObjectList(PlayerBean.class, false);
            for (PlayerBean playerBean:sourceAsObjectList) {
                System.out.println(playerBean);
            }
            System.out.println(searchResult.getJsonString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testSearchPl(){
        MatchAllQueryBuilder qb = QueryBuilders.matchAllQuery();
        ScriptQueryBuilder scb =QueryBuilders.scriptQuery(new Script("long total = 0; " +
                "for (int i = 0; i < doc['playerScores'].length; ++i) { total += doc['playerScores'][i]; } " +
                "return total/doc['playerScores'].length;"));
/*        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder().withIndices("nbaindex")
                .withQuery(qb)
                .withQuery(scb)
                .build();
        SearchHits objects = elasticsearchTemplate.query(searchQuery, new ResultsExtractor<SearchHits>() {
            @Override
            public SearchHits extract(SearchResponse searchResponse) {
                return searchResponse.getHits();
            }
        });
        objects.getHits();*/
    }

}
