package Controller;

import Model.Registration;
import Model.User;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet(name = "register", urlPatterns = {"/register"})
public class register extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       // response.setContentType("text/html;charset=UTF-8");
        // type of the response sent to the client or browser
        //PrintWriter out = response.getWriter();
      
    	HttpSession session = request.getSession();
        Registration reg = new Registration(session);
        try {
            if (request.getParameter("register") != null) {

                String name = request.getParameter("name");
                String phone = request.getParameter("phone");
                String email = request.getParameter("email");
                String pw = request.getParameter("pw");
                String cp = request.getParameter("cp");

                if (pw.equals(cp)) {
                    String status = reg.Registration(name, phone, email, pw);
                    
                    
                    if (status.equals("existed")) {

                        request.setAttribute("status", "Existed record");
                        RequestDispatcher rd1 = request.getRequestDispatcher("Registration.jsp");
                        rd1.forward(request, response);

                    } else if (status.equals("success")) {
                        request.setAttribute("status", "Successfully Registered");
                        RequestDispatcher rd1 = request.getRequestDispatcher("Login.jsp");
                        rd1.forward(request, response);

                    } else if (status.equals("failure")) {
                        request.setAttribute("status", "Registration failed");
                        RequestDispatcher rd1 = request.getRequestDispatcher("Registration.jsp");
                        rd1.forward(request, response);

                    }
                }
            } else if (request.getParameter("login") != null) {
                String email = request.getParameter("email");
                String pass = request.getParameter("pw");
                String status = reg.login(email, pass, session);
                if (status.equals("success")) {

                    RequestDispatcher rd1 = request.getRequestDispatcher("Index.jsp");

                    rd1.forward(request, response);

                } else if (status.equals("failure")) {
                    request.setAttribute("status", "Login failed");
                    RequestDispatcher rd1 = request.getRequestDispatcher("Login.jsp");
                    rd1.forward(request, response);
                }
            } else if (request.getParameter("logout") != null) {
                session.invalidate();
                RequestDispatcher rd1 = request.getRequestDispatcher("Index.jsp");
                rd1.forward(request, response);
            } 
        
       
        }
catch (Exception e) {
            e.printStackTrace();
        }

    }

   
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    
   
    
    
    
   
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

