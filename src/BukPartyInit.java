import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//Import required java libraries


public class BukPartyInit extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	
	 private String message;

	  public void init() throws ServletException
	  {
	      // Do required initialization
	      message = "Hello World";
	  }

	  public void doGet(HttpServletRequest request,
	                    HttpServletResponse response)
	            throws ServletException, IOException
	  {
	      // Set response content type
	      response.setContentType("text/html");

	      // Actual logic goes here.
	      PrintWriter out = response.getWriter();
//	      out.println("<h1>" + message + "</h1>");
	      try {
	    	  out.write(rsToXML.getXML());
//	    	  out.write(readXML());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }
	  
//	  private static String readXML() throws IOException {
//		  StringBuilder res= new StringBuilder();;
//		  FileInputStream fIns = new FileInputStream(new File("C:\\Users\\sgupt040\\TAT_workspace\\bukParty.com\\src\\a.xml"));
//		  BufferedReader fBr = new BufferedReader(new InputStreamReader(fIns));
//		  while(fBr.readLine()!=null)
//			  {
//			  res.append(fBr.readLine());
//			  }
//		  fBr.close();
//		  return res.toString();
//	}

	public void destroy()
	  {
	      // do 
	  }
	  
//	private static final long serialVersionUID = 1L;
//	public static ServletContext _servletContext;
//    public BukPartyInit() {
//	}
//    
//    public static synchronized void init(ServletContext context) throws ServletException 
//    {
//    	_servletContext = context;
//    	System.out.println("Siddharth");
//    }
    
}
