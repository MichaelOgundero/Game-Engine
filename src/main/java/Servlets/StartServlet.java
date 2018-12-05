package Servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

import Game.GameController;
import com.google.gson.Gson;

@WebServlet(name = "start", urlPatterns = "/start")
public class StartServlet extends HttpServlet {

    class Wrapper3{
        public ArrayList<String> usernames = new ArrayList<String>();
        public int seed;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

//        String[] usernames = req.getParameterValues("usernames");
//        String seed = req.getParameter("seed");

//        int seedAsInt = Integer.parseInt(seed);

        BufferedReader rd = new BufferedReader(
                new InputStreamReader(req.getInputStream(), "UTF-8")
        );
        String line = null;
        String message = new String();
        final StringBuffer buffer = new StringBuffer(2048);
        while ((line = rd.readLine()) != null) {
            // buffer.append(line);
            message += line;
        }
        Wrapper3 data = new Gson().fromJson(message, Wrapper3.class);

//        int i=0;
//        String[] usernames = new String[data.usernames.size()];
//        for(String s: data.usernames) {
//            usernames[i]=s;
//            i++;
//        }

        String[] usernames = data.usernames.toArray(new String[0]);
        int seedAsInt = data.seed;

        GameController.getInstance().start(usernames, seedAsInt);
        String gameState = GameController.getInstance().getState(usernames[0]);

        PrintWriter out = resp.getWriter();
        out.print(gameState);
    }
}
