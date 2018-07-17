import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/QueueServlet")
public class QueueServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String type = (String) request.getParameter("type");
        if(type.equals("LoginRequest")){
            HttpSession session = request.getSession();
            QueueManager manager = (QueueManager) request.getServletContext().getAttribute("QueueManager");
            int queueNumber = manager.getQueueNumber(session);
            if(queueNumber==0){
                response.getWriter().write("true");
            }else if(queueNumber!=-1){
                response.getWriter().write(Integer.toString(queueNumber));
            }else{
                response.getWriter().write("error");
            }
        }
    }
}
