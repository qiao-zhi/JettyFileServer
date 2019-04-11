import org.eclipse.jetty.server.DispatcherType;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class StartServer {
	public static void main(String[] args) throws Exception {
		Server server = new Server(8080);

		ServletContextHandler handler = new ServletContextHandler();
		// 相当于设置项目名称
		handler.setContextPath("/fileserver");
		// 设置资源文件所在目录，工具类中会以这个目录作为文件服务目录存储文件
		handler.setResourceBase("G:/files/");
		// handler.setResourceBase(".");
		System.out.println(handler.getServletContext().getRealPath("/"));

		handler.addFilter(FilenameGuardFilter.class, "/*", DispatcherType.FORWARD.ordinal());

		handler.addFilter(RestFilter.class, "/*", DispatcherType.FORWARD.ordinal());
		ServletHolder defaultServlet = new ServletHolder();
		defaultServlet.setName("DefaultServlet");
		defaultServlet.setClassName("org.eclipse.jetty.servlet.DefaultServlet");

		handler.addServlet(defaultServlet, "/*");

		server.setHandler(handler);
		server.start();
	}
}
