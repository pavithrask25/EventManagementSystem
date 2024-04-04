package Controller;

import Model.Registration;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(name = "addtocart", urlPatterns = {"/addtocart"})
public class addtocart extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //response.setContentType("text/html;charset=UTF-8");
       // PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        Registration r = new Registration(session);
        try {
            
            
        if (session.getAttribute("name") != null && request.getParameter("bookevent") != null) {
                String status = r.Booknow(request);
                if (status.equals("success")) {
                    request.setAttribute("status", "Booking successful.<a href='Eventstatus.jsp'>Click here</a> to check status.");
                    RequestDispatcher rd = request.getRequestDispatcher("Eventbooking.jsp?event_id=" + request.getParameter("event_id"));
                    rd.forward(request, response);
                } else if (status.equals("failure")) {
                    request.setAttribute("status", "Booking failed");
                    RequestDispatcher rd = request.getRequestDispatcher("Eventbooking.jsp?event_id=" + request.getParameter("event_id"));
                    rd.forward(request, response);
                } else if (status.equals("existed")) {
                    request.setAttribute("status", "Date not available for event");
                    RequestDispatcher rd = request.getRequestDispatcher("Eventbooking.jsp?event_id=" + request.getParameter("event_id"));
                    rd.forward(request, response);
                }
            } 
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

