package com.tenco;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// HttpServlet 클래스를 상속 받아서 내가 정의한 클래스가 선언이 된다.
// 왜? Http 프로토콜을 통한 request, response 처리를 할 수 있기 때문

// URL 맵핑에 대한 개념을 알자.
// 클라이언트가 특정 URL을 요청했을 때 해당 URL에 대응하는 서블릿을
// 실행하도록 설정하는 것을 의미한다.
// URL 맵핑 - 2가지 방법

@WebServlet(urlPatterns = "/example", loadOnStartup = 1)
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// 생성자
    public HelloServlet() {
        super();
        System.out.println("생성자가 호출 됨");
   
    }

    // 해당 서블릿 클래스가 인스턴스화 될 때 단 한번 실행 되는 메서드
	public void init(ServletConfig config) throws ServletException {
		System.out.println("init() 메서드가 호출 됨");
	}
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("service 메서드 호출");
		response.setContentType("text/html; charset = UTF-8");
		super.service(request, response);
	}

	// 메모리에서 내려가기 직전에 호출되는 메서드
	public void destroy() {
		System.out.println("destroy 호출");
	}

	// GET 요청으로 들어올 때 동작 된다.
	// 주소 설계 - http://localhost:8080/hello/hello-servlet
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Mime type - 인터넷 세상에서 데이터의 유형을 나타내는 표준 방식
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		// 스트림을 어디에서 뽑아야 될까?
		response.getWriter().write("<html><body><h1>Hello</h1></body></html>");
	}

	// POST 요청으로 들어올 때 동작 된다.
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 문제 - post 요청이 들어오는 것을 확인하고
		// 응용해서 데이터 또는 html 형식으로 응답처리 하세요.
		System.out.println("동작");
		response.setContentType("text/html"); // text html 형태로 출력 하겠다고 지정함
		PrintWriter out = response.getWriter(); // response로 부터 출력 장치를 확보
		
		// html 내용을 출력
		out.println("<html>");
		out.println("hello, world<br/>");
		out.println("method : " + request.getMethod());
		out.println("</html>");
	}

}
