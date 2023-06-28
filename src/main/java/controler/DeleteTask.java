package controler;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import dao.TaskDao;
/**
 * Servlet implementation class Delete
 */
@WebServlet("/deleteTask")
public class DeleteTask extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * Default constructor.
     */
    public DeleteTask() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/deleteTask.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub

        String idtask = request.getParameter("idtask");
        try {
            int value = TaskDao.deleteTaks(idtask);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/listTasks");
        dispatcher.forward(request, response);
    }

}
