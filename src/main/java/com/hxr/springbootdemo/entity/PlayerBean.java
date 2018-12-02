package com.hxr.springbootdemo.entity;

/*import org.springframework.data.elasticsearch.annotations.Document;*/

import java.util.List;

/*@Document(indexName = "nbaindex",type = "player")*/
public class PlayerBean {


    private int id;
    private String playerName;
    private int playerAge;
    private List<Integer> playerScores;
    private List<Integer> playerRebounds;
    private List<Integer> playerAssists;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getPlayerAge() {
        return playerAge;
    }

    public void setPlayerAge(int playerAge) {
        this.playerAge = playerAge;
    }

    public List<Integer> getPlayerScores() {
        return playerScores;
    }

    public void setPlayerScores(List<Integer> playerScores) {
        this.playerScores = playerScores;
    }

    public List<Integer> getPlayerRebounds() {
        return playerRebounds;
    }

    public void setPlayerRebounds(List<Integer> playerRebounds) {
        this.playerRebounds = playerRebounds;
    }

    public List<Integer> getPlayerAssists() {
        return playerAssists;
    }

    public void setPlayerAssists(List<Integer> playerAssists) {
        this.playerAssists = playerAssists;
    }

    @Override
    public String toString() {
        return "PlayerBean{" +
                "playerId=" + id +
                ", playerName='" + playerName + '\'' +
                ", playerAge=" + playerAge +
                ", playerScores=" + playerScores +
                ", playerRebounds=" + playerRebounds +
                ", playerAssists=" + playerAssists +
                '}';
    }
}
