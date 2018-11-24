package Servlets;

import Game.GameController;
import Game.UnitTypeEnum;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "createUnit", urlPatterns = "/create_unit")
public class CreateUnitServlet extends HttpServlet {
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        String xCoord = req.getParameter("xCoord");
        String yCoord = req.getParameter("yCoord");
        String type = req.getParameter("type");
        String baseID = req.getParameter("baseID");
        String username = req.getParameter("username");

        int xCoordAsInt = Integer.parseInt(xCoord);
        int yCoodAsInt = Integer.parseInt(yCoord);
        int baseIdAsInt = Integer.parseInt(baseID);
        UnitTypeEnum typeAsEnum = UnitTypeEnum.valueOf(type);

        GameController.getInstance().createUnit(xCoordAsInt, yCoodAsInt, typeAsEnum, baseIdAsInt, username);
        String gameState = GameController.getInstance().getState();

        PrintWriter out = resp.getWriter();
        out.print(gameState);
    }
}
