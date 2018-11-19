package Servlets;

import Game.GameController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "getMoves", urlPatterns = "/get_moves")
public class GetMovesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        String unitID = req.getParameter("unitID");
        int unitIdAsInt = Integer.parseInt(unitID);

        String positions = GameController.getInstance().getMoves(unitIdAsInt);

        PrintWriter out = resp.getWriter();
        out.print(positions);
    }
}
