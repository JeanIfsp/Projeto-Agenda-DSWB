package controler;

import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import dao.UserDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;
import util.PasswordEncryptor;
import controler.Login;

/**
 * Servlet implementation class CreateUser
 */
@WebServlet(urlPatterns={"/createUser"})
public class CreateUser extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * Default constructor.
     */
    public CreateUser() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/createUser.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        User user  = new User();
        user.setLogin(request.getParameter("login"));
        user.setNome(request.getParameter("nome"));
        user.setEmail(request.getParameter("email"));
        user.setSenha(PasswordEncryptor.encryptPassword(request.getParameter("password")));
        HttpSession session = request.getSession(false);
        try {
            if(UserDao.insertUser(user) == 0) {
                // TODO Auto-generated catch block
                session.setAttribute("userExists", "true");
                doGet(request,response);
            }

        }catch (Exception e) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/");
            dispatcher.forward(request, response);
        }
        session.setAttribute("userExists", "false");
        Login login = new Login();
        login.doGet(request, response);
    }

}
