package bm.app.service;

import bm.app.config.Connector;
import bm.app.model.Guess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.function.Supplier;

import static bm.app.config.ConstantValues.*;

@Service
public class GuessService {

    private static final Logger logger = LoggerFactory.getLogger(GuessService.class);


    public Connection getConnection() {
        Connection connection = Connector.createConnection(DEFAULT_DRIVER, DEFAULT_URL, DEFAULT_USERNAME, DEFAULT_PASSWORD);
        if (connection == null) {
            logger.error("Cannot get the connection.");
            return null;
        }
        return connection;
    }

    public boolean insert(Guess guess) {
        String sql = "insert into words (word, number) values (?, ?)";
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
            preparedStatement.setString(1, guess.getWord());
            preparedStatement.setInt(2, guess.getNumber());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Cannot insert the record.");
            e.printStackTrace();
            return false;
        }


        return true;
    }

    public List<Guess> selectAllRecords() {
        List<Guess> list = new ArrayList();
        String sql = "select id, word, number from words";
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Guess guess = new Guess();
                guess.setId(resultSet.getInt("id"));
                guess.setWord(resultSet.getString("word"));
                guess.setNumber(resultSet.getInt("number"));
                list.add(guess);
            }
        } catch (SQLException e) {
            logger.error("Could not get records.");
            e.printStackTrace();
        }
        return list;

    }

    public String selectWordByNumber(){
        List<Guess> resultList = selectAllRecords();
        int randomIndex = new Random().nextInt(resultList.size());
        return resultList.get(randomIndex).getWord();
    }
}
