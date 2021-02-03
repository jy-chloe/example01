package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MemoDAO;
import dto.MemoDTO;


@WebServlet("/memo_servlet/*")
public class MemoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProc(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doProc(request, response);
	}

	protected void doProc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String path = request.getContextPath();
		String url = request.getRequestURL().toString();
		String page = "/main/main.jsp";
		String pageNumber_;
		pageNumber_="";
		pageNumber_= request.getParameter("pageNumber");
		if(pageNumber_ == null || pageNumber_.trim().equals("")) {
			pageNumber_="1";
		}
		
		int pageNumber = Integer.parseInt(pageNumber_);
		
		if(url.indexOf("write.do") != -1) {
			MemoDAO dao = new MemoDAO();

			int pageSize = 5;
			int blockSize = 10;
			int totalRecord1 = dao.getTotalRecord();
			int total = dao.getTotalRecord();
			int jj = totalRecord1 - pageSize * (pageNumber - 1);
			int startRecord = pageSize * (pageNumber - 1) + 1;
			int lastRecord = pageSize * pageNumber;
			
			int totalPage = 0;
			int startPage = 1;
			int lastPage = 1;
			if(totalRecord1 > 0) {
				totalPage = totalRecord1 / pageSize + (totalRecord1 % pageSize == 0 ? 0 : 1);
				startPage = (pageNumber / blockSize - (pageNumber % blockSize != 0 ? 0 : 1)) * blockSize + 1;
				lastPage = startPage + blockSize - 1;
				if(lastPage > totalPage) {
					lastPage = totalPage;
				}				
			}

			ArrayList<MemoDTO> list = dao.getList(startRecord, lastRecord);
			request.setAttribute("menu_gubun", "memo_write");
			request.setAttribute("list", list);
			request.setAttribute("pageNumber", pageNumber);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("blockSize", blockSize);
			request.setAttribute("totalRecord", totalRecord1);
			request.setAttribute("jj", jj);
			request.setAttribute("startRecord", startRecord);
			request.setAttribute("lastRecord", lastRecord);
			request.setAttribute("totalPage", totalPage);
			request.setAttribute("startPage", startPage);
			request.setAttribute("lastPage", lastPage);
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
		} else if(url.indexOf("writeProc.do") != -1) {
			String writer = request.getParameter("writer");
			String content = request.getParameter("content");
			
			MemoDTO dto = new MemoDTO();
			dto.setWriter(writer);
			dto.setContent(content);
			
			MemoDAO dao = new MemoDAO();			
			int result = dao.setInsert(dto);
			
			String temp="";
			if (result > 0) {
				temp = path + "/memo_servlet/write.do";
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('등록완료');");
				out.println("location.href='"+ temp +"';");
				out.println("</script>");	
			} else {
				temp = path + "/memo_servlet/write.do";
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('등록실패');");
				out.println("location.href='"+ temp +"';");
				out.println("</script>");	
			}
			

		} 
		
	}	

}
