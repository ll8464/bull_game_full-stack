/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bullcows.data;

import com.mycompany.bullcows.models.BC;
import com.mycompany.bullcows.models.BCRounds;
import com.mycompany.bullcows.service.BCDataValidationException;
import com.mycompany.bullcows.service.BCDuplicateIdException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

/**
 *
 * @author leela
 */
@Repository
public class BCDatabaseDao implements BCDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BCDatabaseDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public BC add(BC game) throws BCDuplicateIdException,
            BCDataValidationException, BCPersistenceException {

        final String sql = "INSERT INTO GAME(Answer, Finished) VALUES(?,?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update((Connection conn) -> {

            PreparedStatement statement = conn.prepareStatement(
                    sql,
                    Statement.RETURN_GENERATED_KEYS);

            statement.setInt(1, game.getAnswer());
            statement.setBoolean(2, game.isFinished());
            return statement;

        }, keyHolder);

        game.setGameId(keyHolder.getKey().intValue());

        return game;
    }

    @Override
    public BC begin(int rndDigits, BC game) throws BCDuplicateIdException,
            BCDataValidationException, BCPersistenceException {

        final String sql = "INSERT INTO GAME(Answer, Finished) VALUES(?,?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update((Connection conn) -> {

            PreparedStatement statement = conn.prepareStatement(
                    sql,
                    Statement.RETURN_GENERATED_KEYS);

            statement.setInt(1, rndDigits);
            statement.setBoolean(2, game.isFinished());
            return statement;

        }, keyHolder);

        game.setGameId(keyHolder.getKey().intValue());

        return game;
    }

    @Override
    public BCRounds guessInput(BCRounds round) throws BCDuplicateIdException,
            BCDataValidationException, BCPersistenceException {

        final String sql = "INSERT INTO ROUNDS(PartialWins, ExactWins,"
                + "  GameId, UserGuess, Results) VALUES(?,?,?,?,?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update((Connection conn) -> {

            PreparedStatement statement = conn.prepareStatement(
                    sql,
                    Statement.RETURN_GENERATED_KEYS);

            statement.setInt(1, round.getPartialWins());
            statement.setInt(2, round.getExactWins());
            statement.setInt(3, round.getGameId());
            statement.setInt(4, round.getUserGuess());
            statement.setString(5, round.getResults());
            return statement;

        }, keyHolder);

        round.setGameId(keyHolder.getKey().intValue());

        return round;
    }

    //Add Guess Time in Postman in YYYY/MM/DD format
    //Each Round added must go to a previously submitted Game via gameId
    @Override
    public BCRounds addRound(BCRounds round) throws BCDuplicateIdException,
            BCDataValidationException, BCPersistenceException {

        final String sql = "INSERT INTO ROUNDS(PartialWins, ExactWins,"
                + " GuessTime, GameId) VALUES(?,?,?,?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update((Connection conn) -> {

            PreparedStatement statement = conn.prepareStatement(
                    sql,
                    Statement.RETURN_GENERATED_KEYS);

            statement.setInt(1, round.getPartialWins());
            statement.setInt(2, round.getExactWins());
            statement.setString(3, round.getGuessTime());
            statement.setInt(4, round.getGameId());
            return statement;

        }, keyHolder);

        round.setGameId(keyHolder.getKey().intValue());

        return round;
    }

    @Override
    public List<BC> getAll() throws BCPersistenceException {

        final String sql = "SELECT * "
                + " FROM GAME ORDER BY game.gameId;";
        return jdbcTemplate.query(sql, new BCMapper());
    }

    @Override
    public List<BCRounds> getAllRounds() throws BCPersistenceException {
        final String sql = "SELECT * FROM ROUNDS;";
        return jdbcTemplate.query(sql, new BCMapperRound());
    }

    @Override
    public BC findById(int id) throws BCDuplicateIdException,
            BCDataValidationException {

        final String sql = "SELECT GameId, Answer, Finished "
                + "FROM GAME WHERE GameId = ?";

        return jdbcTemplate.queryForObject(sql, new BCMapper(), id);
    }

    @Override
    public BCRounds findByRoundId(int id) throws BCDuplicateIdException,
            BCDataValidationException {

        final String sql = "SELECT RoundId, PartialWin, ExactWin, UserGuess "
                + "FROM Rounds WHERE RoundId = ?;";

        return jdbcTemplate.queryForObject(sql, new BCMapperRound(), id);
    }

    @Override
    public boolean update(BC game) throws BCDuplicateIdException,
            BCDataValidationException {

        final String sql = "UPDATE GAME SET "
                + "Answer = ?, "
                + "finished = ?, "
                + "WHERE GameId = ?;";

        return jdbcTemplate.update(sql,
                game.getAnswer(),
                game.isFinished(),
                game.getGameId()) > 0;
    }

    @Override
    public boolean updateRound(BCRounds round) throws BCDuplicateIdException,
            BCDataValidationException {

        final String sql = "UPDATE ROUNDS SET "
                + "PartialWins = ?, "
                + "ExactWins = ?, "
                + "guessTime = ?, "
                + "WHERE roundId = ?;";

        return jdbcTemplate.update(sql,
                round.getPartialWins(),
                round.getExactWins(),
                Timestamp.valueOf(round.getGuessTime()),
                round.getRoundId()) > 0;
    }

    @Override
    public boolean deleteById(int id) throws BCDuplicateIdException,
            BCDataValidationException {
        final String sql = "DELETE FROM GAME WHERE GameId = ?;";
        return jdbcTemplate.update(sql, id) > 0;
    }

    @Override
    public boolean deleteByRoundId(int id) throws BCDuplicateIdException,
            BCDataValidationException {
        final String sql = "DELETE FROM ROUNDS WHERE roundId = ?;";
        return jdbcTemplate.update(sql, id) > 0;
    }

    private static final class BCMapper implements RowMapper<BC> {

        @Override
        public BC mapRow(ResultSet rs, int index) throws SQLException {
            BC td = new BC();
            td.setGameId(rs.getInt("gameId"));
            td.setAnswer(rs.getInt("answer"));
            td.setFinished(rs.getBoolean("finished"));
            return td;
        }
    }

    //This mapper is for methods that ONLY want Round manipulation
    private static final class BCMapperRound implements RowMapper<BCRounds> {

        @Override
        public BCRounds mapRow(ResultSet rs, int index) throws SQLException {
            BCRounds td = new BCRounds();
            td.setPartialWins(rs.getInt("partialWins"));
            td.setExactWins(rs.getInt("exactWins"));
            td.setRoundId(rs.getInt("roundId"));
            td.setGameId(rs.getInt("gameId"));
            td.setUserGuess(rs.getInt("userGuess"));
            return td;
        }
    }

}
