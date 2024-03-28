/*
package ua.skorobahatyi.case3.servlet;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.client.RestTemplate;
import ua.skorobahatyi.case1.client.NasaPicturesClient;
import ua.skorobahatyi.case3.client.NasaPicturesClient3;
import ua.skorobahatyi.case3.config.DemoAppConfig3;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/nasa/pictures")
public class NasaPicturesServlet extends HttpServlet {
    private static final String ROOT_CONTEXT = "ROOT_CONTEXT";
    //private NasaPicturesClient3 nasaPicturesClient3;

    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("  === 1 ");
        var springContext = new AnnotationConfigApplicationContext(DemoAppConfig3.class);
        System.out.println("  === 2 ");
        var servletContext = config.getServletContext();
        System.out.println("  === 3 ");
        servletContext.setAttribute(ROOT_CONTEXT, springContext);
        System.out.println("  === 4 ");
        // nasaPicturesClient3 = springContext.getBean(NasaPicturesClient3.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("  === 555 ");
        var springContext = req.getServletContext().getAttribute(ROOT_CONTEXT);
        System.out.println("  === 6 ");
        var nasaPicturesClient3 = ((ApplicationContext)springContext).getBean(NasaPicturesClient3.class);
        System.out.println("  === 7 ");
        var writer = resp.getWriter();
        nasaPicturesClient3.getAllPictures()
                .forEach(writer::println);
    }
}
*/
