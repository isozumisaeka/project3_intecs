package inc.HIKARI.servlet;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import inc.HIKARI.DAO.TaxBeanDAO;
import inc.HIKARI.beans.TaxBean;

public class AddTaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String nextPage = "TaxServlet";
		try {
			int tax = Integer.parseInt(request.getParameter("tax"));

			String year = request.getParameter("year");
			String month = request.getParameter("month");
			String day = request.getParameter("day");
			String startTaxStr = year + "-" + month + "-" + day;
			SimpleDateFormat sfd = new SimpleDateFormat("yyyy-MM-dd");

			java.util.Date startTaxDate = sfd.parse(startTaxStr);
			long timeInMilliSeconds = startTaxDate.getTime();
	        Date startTax = new Date(timeInMilliSeconds);

			TaxBean newTax = new TaxBean(tax,startTax);

			if(TaxBeanDAO.addTax(newTax)) {
				request.setAttribute("message","追加できました" );
			}else {
				request.setAttribute("message","追加できませんでした");
			}
		}catch(NumberFormatException e) {
			request.setAttribute("message", "半角数字で入力してください");
		} catch (java.text.ParseException e) {
			request.setAttribute("message", "正しく入力してください");
		}finally {
			RequestDispatcher rd = request.getRequestDispatcher(nextPage);
			rd.forward(request,response);
		}
	}
}
