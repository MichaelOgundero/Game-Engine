package Servlets;

import Game.GameController;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "forfeit", urlPatterns = "/forfeit")
public class ForfeitServlet extends HttpServlet{
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        String username = req.getParameter("username");

        GameController.getInstance().forfeit(username);
        String gameState = GameController.getInstance().getState();

        PrintWriter out = resp.getWriter();
        out.print(gameState);
    }
}
