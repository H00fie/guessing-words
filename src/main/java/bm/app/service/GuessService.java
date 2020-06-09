package bm.app.service;

import bm.app.config.Connector;
import bm.app.model.Guess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static bm.app.config.ConstantValues.*;

public class GuessService {

    private static final Logger logger = LoggerFactory.getLogger(GuessService.class);


    public Connection getConnection(){
        Connection connection = Connector.createConnection(DEFAULT_DRIVER, DEFAULT_URL, DEFAULT_USERNAME, DEFAULT_PASSWORD);
        if (connection == null){
            logger.error("Cannot get the connection.");
            return null;
        }
        return connection;
    }

    public boolean insert(Guess guess){
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
}
