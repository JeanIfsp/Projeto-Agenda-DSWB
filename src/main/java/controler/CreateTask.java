package controler;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Task;
import dao.TaskDao;

/**
 /**
 * Servlet implementation class CreateTask
 */
@WebServlet(urlPatterns={"/createTask"})
public class CreateTask extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * Default constructor.
     */
    public CreateTask() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/createTask.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String conclusionDate = request.getParameter("data_conclusao");
        Date dateFinish = Date.valueOf(conclusionDate);
        HttpSession session=request.getSession(false);

        if(dateFinish.after(Date.valueOf(LocalDate.now()))){

            String userid = (String) session.getAttribute("userid");
            Task task = new Task();
            task.setTitulo(request.getParameter("titulo"));
            task.setDescricao(request.getParameter("descricao"));
            task.setStatus(request.getParameter("status"));
            task.setId_user(Integer.parseInt(userid));
            task.setData_conclusao(dateFinish);

            try {
                int value = TaskDao.insertTask(task);
                session.setAttribute("date", "false");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/listTasks");
                dispatcher.forward(request, response);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }else {
            session.setAttribute("date", "true");
            doGet(request, response);
        }
    }

}
