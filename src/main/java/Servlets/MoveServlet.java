package Servlets;

import Game.GameController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "move", urlPatterns = "/move")
public class MoveServlet extends HttpServlet {
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        String xCoord = req.getParameter("xCoord");
        String yCoord = req.getParameter("yCoord");
        String unitID = req.getParameter("unitID");

        int xCoordAsInt = Integer.parseInt(xCoord);
        int yCoodAsInt = Integer.parseInt(yCoord);
        int unitIdAsInt = Integer.parseInt(unitID);

        GameController.getInstance().move(xCoordAsInt, yCoodAsInt, unitIdAsInt);
        String gameState = GameController.getInstance().getState();

        PrintWriter out = resp.getWriter();
        out.print(gameState);
    }
}
