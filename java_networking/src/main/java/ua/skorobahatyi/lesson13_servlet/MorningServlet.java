package ua.skorobahatyi.lesson13_servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import lombok.SneakyThrows;

import java.io.IOException;
import java.util.Optional;
import java.util.stream.Stream;

@WebServlet("/morning")
public class MorningServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        printClientMood(req);
        //printClientCookies(req);
        printMorning2(req, resp);
        //resp.addCookie(new Cookie("SUPER_ID","54321"));
    }

    private void printClientCookies(HttpServletRequest req) {
        Stream.of(req.getCookies())
                .forEach(c -> System.out.println(c.getName() + " - " + c.getValue()));
    }

    @SneakyThrows
    private void printMorning(HttpServletRequest req, HttpServletResponse resp) {

        var requestName = Optional.ofNullable(req.getParameter("name"));

        var writer = resp.getWriter();
        var name = requestName
                .orElse("My friend");
        writer.println("Good morning, " + name);
    }

    // With Session
    @SneakyThrows
    private void printMorning2(HttpServletRequest req, HttpServletResponse resp) {
        var session = req.getSession();
        var sessionName = Optional.ofNullable((String) session.getAttribute("name"));
        var requestName = Optional.ofNullable(req.getParameter("name"));

        requestName
                .filter(name -> sessionName.isEmpty())
                .ifPresent(name -> session.setAttribute("name", name));

        var writer = resp.getWriter();
        var name = requestName
                .or(() -> sessionName)
                .orElse("My friend");
        writer.println("Good morning, " + name);
    }

    // improve method
    private void printClientMood(HttpServletRequest req) {
        var name = Optional.ofNullable(req.getParameter("name"))
                .orElse(req.getRemoteAddr());
        Optional.ofNullable(req.getHeader("X-Mood"))
                .ifPresent(mood -> System.out.println(name + " is feeling " + mood));
    }
}
