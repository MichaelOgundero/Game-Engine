package Servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import Game.GameController;

@WebServlet(name = "start", urlPatterns = "/start")
public class StartServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        String[] usernames = req.getParameterValues("usernames");
        String seed = req.getParameter("seed");

        int seedAsInt = Integer.parseInt(seed);

        GameController.getInstance().start(usernames, seedAsInt);
        String gameState = GameController.getInstance().getState(usernames[0]);

        PrintWriter out = resp.getWriter();
        out.print(gameState);
    }
}
