package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;
import model.User;
import utility.ConnectionManager;

@WebServlet(urlPatterns= {"/signup"})
public class SignUpController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public SignUpController() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher rd=this.getServletContext().getRequestDispatcher("/WEB-INF/views/signupView.jsp");
		rd.forward(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String email = request.getParameter("email"); //  get the email value from the jsp/html page
		String password = request.getParameter("password"); //  get the password value from the jsp/html page
		String confirmPassword = request.getParameter("confirmPassword"); //  get the confirm password value from the jsp/html page
		LocalDate date= LocalDate.now(); // Java 8 Time API used to get system date and time at a particular instance
		
		
		User user = new User();
		UserDAO userDao = new UserDAO();
		user.setEmail(email);
		user.setPassword(confirmPassword);
		user.setDate(date);
		
		try {
			if (userDao.signUp(user)!= 0) {
				System.out.println(user.getEmail());
				System.out.println(user.getPassword());
				System.out.println(user.getDate());	
				request.setAttribute("message", "Registration Successful");
				RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/WEB-INF/views/signupView.jsp");
				rd.forward(request, response);
			} 
			else {
				request.setAttribute("message", "Check your email and password");
				RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/WEB-INF/views/signupView.jsp");
				rd.forward(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		
		
		
	}

}
