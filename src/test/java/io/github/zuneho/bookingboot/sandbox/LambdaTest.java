
package io.github.zuneho.bookingboot.sandbox;


import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Comparator;
import java.util.List;

public class LambdaTest {

    public static class BaseBallPlayer implements Comparable<BaseBallPlayer> {
        private String teamName;
        private String playerName;
        private String position;
        private int ranking;

        public void setTeamName(String teamName) {
            this.teamName = teamName;
        }

        public void setPlayerName(String playerName) {
            this.playerName = playerName;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public void setRanking(int ranking) {
            this.ranking = ranking;
        }

        public String getTeamName() {
            return teamName;
        }

        public String getPlayerName() {
            return playerName;
        }

        public String getPosition() {
            return position;
        }

        public int getRanking() {
            return ranking;
        }

        @Override
        public int compareTo(BaseBallPlayer baseBallPlayer) {
            return playerName.compareTo(baseBallPlayer.getPlayerName());
        }
    }

    public static void main(String[] args) {

        DataSource dataSource = DataSourceBuilder.create().type(HikariDataSource.class)
                .driverClassName("org.h2.Driver")
                .url("jdbc:h2:mem:testdb")
                .username("sa")
                .password("")
                .build();

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.setDataSource(dataSource);
        jdbcTemplate.update("CREATE TABLE baseBallPlayers (" +
                "    idx int(11) NOT NULL AUTO_INCREMENT, " +
                "    teamName varchar(50) NOT NULL, " +
                "    playerName varchar(50) NOT NULL, " +
                "    position varchar(50) NOT NULL, " +
                "    ranking int(11) NOT NULL, " +
                "    PRIMARY KEY (idx)" +
                ");");

        jdbcTemplate.update("INSERT INTO baseBallPlayers (teamName,playerName,position,ranking) values " +
                "('test','testTeam','우익수','1')," +
                "('test2','testTeam','좌익수','2')," +
                "('test3','testTeam','포수','3');");

        List<BaseBallPlayer> baseBallPlayers = jdbcTemplate.query("select * from baseBallPlayers", (rs, rowNum) -> {
            BaseBallPlayer baseBallPlayer = new BaseBallPlayer();
            baseBallPlayer.setTeamName(rs.getString("teamName"));
            baseBallPlayer.setPlayerName(rs.getString("playerName"));
            baseBallPlayer.setPosition(rs.getString("position"));
            baseBallPlayer.setRanking(rs.getInt("ranking"));
            return baseBallPlayer;
        });

        baseBallPlayers.sort(Comparator.comparing(BaseBallPlayer::getPlayerName));
        System.out.println(baseBallPlayers.size());

    }
}
