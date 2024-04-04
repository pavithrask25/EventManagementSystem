package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class Registration {
	HttpSession session;
	private Connection con;
	
	public Registration(HttpSession se) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // load the drivers
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/eventmanagement", "root", "tiger");
            session=se;
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	 public String Registration(String name, String phone, String email, String pw) {
	        PreparedStatement ps;
	        String status = "";
	        try {
	            Statement st = null;
	            ResultSet rs = null;
	            st = con.createStatement();
	            rs = st.executeQuery("select * from userdata where phone='" + phone + "' or email='" + email + "';");
	            boolean b = rs.next();
	            if (b) {
	                status = "existed";
	            } else {
	                ps = (PreparedStatement) con.prepareStatement("insert into userdata(name,phone,email,password,date)values(?,?,?,?,now())");
	                ps.setString(1, name);
	                ps.setString(2, phone);
	                ps.setString(3, email);
	                ps.setString(4, pw);
	                int a = ps.executeUpdate();
	                if (a > 0) {
	                    status = "success";
	                } else {
	                    status = "failure";
	                }
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return status;
	    }
	    
public String login(String email, String pw, HttpSession se) {
    String status = "";
    try {
        session = se;
        String name = "", email1 = "", phone = "";
        String id = "";
        Statement st = null;
        ResultSet rs = null;
        st = con.createStatement();
        rs = st.executeQuery("select * from userdata where email='" + email + "' and password='" + pw + "';");
        boolean b = rs.next();
        if (b == true) {
            name = rs.getString("name");
            phone = rs.getString("phone");
            email1 = rs.getString("email");
            id = rs.getString("slno");
            session.setAttribute("name", name);
            session.setAttribute("id", id);
            session.setAttribute("email", email1);
            session.setAttribute("phone", phone);
            status = "success";

        } else {
            status = "failure";
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return status;
}




public int deleteevent(int event_id) {
    int status = 0;
    try {
        Statement st = null;
        st = con.createStatement();
        String qry = "update bookevent set status='cancelled' where event_id=' " + event_id + "' ";
        status = st.executeUpdate(qry);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return status;
}

public ArrayList<Dproduct> geteventstatus() {
    ArrayList<Dproduct> al = new ArrayList<Dproduct>();
   Statement st=null;
    ResultSet rs = null;
    try {
        st = con.createStatement();
        String qry = "select *,date_format(date,'%d %b,%Y') as date1,date_format(event_date,'%d %b,%Y') as date2 from bookevent where uid='"+ session.getAttribute("id")+"';";
        rs = st.executeQuery(qry);
        while (rs.next()) {
            Dproduct d = new Dproduct(); // 1..r---db 2.. pass the data or value to Dproduct class set method
            d.setP_id(rs.getString("event_id"));
            d.setP_image(rs.getString("event_image"));
            d.setP_name(rs.getString("event_name"));
            d.setP_cost(rs.getString("event_cost"));
            d.setPhone(rs.getString("phone"));
            d.setEmail(rs.getString("email"));
            d.setDate(rs.getString("date1"));
            d.setEventdate(rs.getString("date2"));
            d.setStatus(rs.getString("status"));
            al.add(d);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return al;

}

//public ArrayList<Dproduct> get_eventinfo(String event_category) {
//        ArrayList<Dproduct> al = new ArrayList<Dproduct>();
//        Statement st = null;
//        ResultSet rs = null;
//        try {
//            st = con.createStatement();
//            String qry = "select * from events where event_category='" + event_category + " ';";
//            rs = st.executeQuery(qry);
//            while (rs.next()) {
//                Dproduct d = new Dproduct();
//                d.setP_id(rs.getString("event_id"));
//                d.setP_image(rs.getString("event_image"));
//                d.setP_name(rs.getString("event_name"));
//                d.setP_cost(rs.getString("event_cost"));
//                d.setP_details(rs.getString("event_details"));
//                al.add(d);
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return al;
//
//    }
public ArrayList<Dproduct> get_eventinfo(String event_category) {
    ArrayList<Dproduct> al = new ArrayList<Dproduct>();
    Statement st=null;
    ResultSet rs = null;
    try {
        st = con.createStatement();
        String qry = "SELECT * FROM eventmanagement.events where event_category='" + event_category + "';";
        rs = st.executeQuery(qry);
        while (rs.next()) {
            Dproduct d = new Dproduct();
            d.setP_id(rs.getString("event_id")); // 1..r---db 2.. pass the data or value to Dproduct class set method
            d.setP_image(rs.getString("event_image"));
            d.setP_name(rs.getString("event_name"));
            d.setP_cost(rs.getString("event_cost"));
            d.setP_details(rs.getString("event_details"));
            al.add(d);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return al;

}



public Dproduct getEventFormInfo(String event_id) {
        Statement st = null;
        ResultSet rs = null;
        Dproduct s = null;
        try {
            st = con.createStatement();
            rs = st.executeQuery("select * from events where event_id='" + event_id +"'");
            boolean b = rs.next();
            if (b == true) {
                s = new Dproduct();
                s.setP_name(rs.getString("event_name"));
                s.setP_cost(rs.getString("event_cost"));
            } else {
                s = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return s;
    }

public String Booknow(HttpServletRequest request) {
        String status = "";
        ResultSet rs = null;
        try {
            Statement st = con.createStatement();
            rs = st.executeQuery("select event_id from bookevent where event_date='" + request.getParameter("edate")+"'");
            boolean b = rs.next();
            if (b == true) {
                status = "existed";
            } else {
            	 String qry = "insert into bookevent "
                         + "select 0,event_image,event_name,'" 
                         + request.getParameter("pno") + "','" 
                         + request.getParameter("email") + "','" 
                         + request.getParameter("address") +
                         "',event_cost,'" + session.getAttribute("name") 
                         + "','" + session.getAttribute("id")
                         + "','pending',now(),'" + request.getParameter("edate") +
                         "' from events where event_id='"+ 
                         request.getParameter("event_id") +"';";
                int a = st.executeUpdate(qry);
                if (a > 0) {
                    status = "success";
                } else {
                    status = "failure";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

}


/*
 *  String qry = "insert into bookevent "
                         + "select 0,event_image,event_name,'" 
                         + request.getParameter("pno") + "','" 
                         + request.getParameter("email") + "','" 
                         + request.getParameter("address") +
                         "',event_cost,'" + session.getAttribute("name") 
                         + "','" + session.getAttribute("id")
                         + " ,'pending',now(),'" 
                         + request.getParameter("edate") +
                         "'from events where event_id=" + 
                         request.getParameter("event_id") +";";
 */ 
