package controler;

import java.awt.desktop.UserSessionEvent;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

import dao.TaskDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Task;

/**
 * Servlet implementation class EditTask
 */
@WebServlet("/editTask")
public class EditTask extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * Default constructor.
     */
    public EditTask() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/editTask.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub

        Task task = new Task();
        task.setDescricao(request.getParameter("descricao"));
        task.setStatus(request.getParameter("status"));
        task.setId(Integer.parseInt(request.getParameter("idtask")));
        String conclusionDate = request.getParameter("data_conclusao");
        Date dateFinish = Date.valueOf(conclusionDate);
        task.setData_conclusao(dateFinish);

        try {
            int value = TaskDao.updateTask(task);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/listTasks");
        dispatcher.forward(request, response);
    }

}
