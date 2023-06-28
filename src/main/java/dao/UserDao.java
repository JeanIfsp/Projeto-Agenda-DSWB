package dao;

import com.sun.tools.javac.Main;
import model.User;
import java.sql.*;
import java.util.logging.*;

import config.ConectionDatabase;

public class UserDao {
    private static final Logger logger = Logger.getLogger(Main.class.getName());
    public static User userExists(String username) throws SQLException {

        ResultSet rs;
        Connection connection = null;
        User user = new User();

        try {

            connection = ConectionDatabase.getConexao();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT id, login, email, nome, password FROM user WHERE login = ?");
            preparedStatement.setString(1, username);
            rs = preparedStatement.executeQuery();
            while(rs.next()) {
                user.setId(rs.getInt("id"));
                user.setLogin(rs.getString("login"));
                user.setEmail(rs.getString("email"));
                user.setNome(rs.getString("nome"));
                user.setSenha(rs.getString("password"));
            }
        }catch(SQLException e) {
            e.printStackTrace();
        }
        finally {
            //connection.close();
        }
        return user;

    }

    public static boolean validateUserExists(User user) throws SQLException {

        String SELECT_USER = "SELECT "
                + "  CASE WHEN EXISTS ("
                + "    SELECT 1"
                + "    FROM user"
                + "    WHERE login = '?' OR email = '?'"
                + "  ) THEN 'Verdadeiro' ELSE 'Falso' END AS status;";

        ResultSet rs;
        Connection connection = null;

        try {

            connection = ConectionDatabase.getConexao();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER);
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getEmail());
            rs = preparedStatement.executeQuery();
            System.out.println(rs);

            while(rs.next()) {
                user.setResultado(rs.getBoolean("Resultado"));
            }

        }catch(SQLException e) {
            e.printStackTrace();
        }
        finally {
            //connection.close();
        }
        return user.getResultado();

    }


    public static int insertUser(User user){

        String INSERT_USER = "INSERT INTO user (login, nome, email, password)"+
                "VALUES (?, ?, ?, ?);";

        Connection connection = null;
        int transection = 0;

        try {

            connection = ConectionDatabase.getConexao();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER);
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getNome());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getSenha());
            return preparedStatement.executeUpdate();
        }catch (SQLIntegrityConstraintViolationException e){
            logger.log(Level.SEVERE, "Violation constraint insert: ", e);
            return transection;
        }catch(SQLException e) {
            System.out.println(e);
            return transection;
        }

    }
}
