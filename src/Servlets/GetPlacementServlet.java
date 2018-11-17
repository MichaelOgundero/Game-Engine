package Servlets;

import Game.GameController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "getPlacement", urlPatterns = "/get_placement")
public class GetPlacementServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        String baseID = req.getParameter("baseID");
        int baseIdAsInt = Integer.parseInt(baseID);

        String positions = GameController.getInstance().getPlacement(baseIdAsInt);

        PrintWriter out = resp.getWriter();
        out.print(positions);
    }
}
