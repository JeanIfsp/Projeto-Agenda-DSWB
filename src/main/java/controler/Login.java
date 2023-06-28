package controler;

import dao.TaskDao;
import dao.UserDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Task;
import model.User;
import util.PasswordEncryptor;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns={"/login", "/"})
public class Login extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * Default constructor.
     */
    public Login() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/login.jsp");
        dispatcher.forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stu

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            User user = UserDao.userExists(request.getParameter("username"));
            if(user!=null && (PasswordEncryptor.checkPassword(password, user.getSenha()))) {
                System.out.println("id do user:");
                System.out.println(user.getId());
                HttpSession session = request.getSession();
                session.setAttribute("userid", String.valueOf(user.getId()));
                session.setAttribute("login", user.getLogin());
                request.setAttribute("iduser", user.getId());
                request.getRequestDispatcher("listTasks").forward(request, response);
            }else {
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/login.jsp");
                dispatcher.forward(request, response);
            }

        }catch (SQLException e) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/login.jsp");
            dispatcher.forward(request, response);
        }

    }
}
