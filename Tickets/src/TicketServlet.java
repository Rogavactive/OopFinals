package Tickets.src;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

@WebServlet
public class TicketServlet extends javax.servlet.http.HttpServlet {
    private ConcurrentHashMap<Integer,String> colors = new ConcurrentHashMap<>();
    public TicketServlet(){
        for(int i = 0; i<50; i++){
            colors.put(i,"green");
        }
    }
    @Override
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String type = (String) request.getParameter("type");
        if(type.equals("getState")){
            String res = getState("-1");
            response.getWriter().write(res);
        }else if(type.equals("takeSeat")){
            String seatID = (String) request.getParameter("seatID");
            if(!colors.get(seatID).equals("green")){
                response.getWriter().write(getState("-1"));
            }else{
                colors.put(Integer.parseInt(seatID),"grey");
                response.getWriter().write(getState(seatID));
            }
        }else if(type.equals("bookTicket")){
            String seatsString = request.getParameter("seats");
            String[] seats = seatsString.split(" ");
        }
    }

    private String getState(String seatID){
        String res = "";
        for (int i = 0; i < colors.size(); i++) {
            res+=colors.get(i) + '\n';
        }
        res += seatID;
        return res;
    }

}
