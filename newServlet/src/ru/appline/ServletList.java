package ru.appline;

import com.google.gson.Gson;
import ru.appline.logic.Model;
import ru.appline.logic.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = "/get")

public class ServletList extends HttpServlet {

    Model model = Model.getInstance();
    private static final long serialVersionUID = 1L;
    private final Gson gson = new Gson();

    /*protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
          response.setContentType("text/html;charset=utf-8");
          PrintWriter pw = response.getWriter();

          int id = Integer.parseInt(request.getParameter("id"));

          if (id == 0) {
              pw.print("<html>" +
                      "<h3>Доступные пользователи:</h3></br>" +
                      "ID пользователя: " +
                      "<ul>");

              for (Map.Entry<Integer, User> entry : model.getFromList().entrySet()){
                  pw.print("<li>" + entry.getKey() + "/<li>" +
                          "<ul>" +
                          "<li>Имя: " + entry.getValue().getName() + "</li>" +
                          "<li>Фамилия " + entry.getValue().getSurname() + "</li>" +
                          "<li>Зарплата " + entry.getValue().getSalary() + "</li>" +
                          "</ul>");
              }
              pw.print("</ul>" +
                      "<a href=\"index.jsp\">Домой</a>" +
                      "</html>");
          } else if (id > 0) {
              if (id > model.getFromList().size()){
                  pw.print("<html>" +
                          "<h3>Такого пользователя нет :(</h3>" +
                          "<a href=\"index.jsp\">Домой</a>" +
                          "</html>");
              }else {
                  pw.print("<html>" +
                          "<h3>Запрошенный пользователь</h3>" +
                          "</br>"+
                          "Имя: " + model.getFromList().get(id).getName() + "</br>" +
                          "Фамилия: " + model.getFromList().get(id).getSurname() + "</br>" +
                          "Зарплата: " + model.getFromList().get(id).getSalary() + "</br>" +
                          "<a href=\"index.jsp\">Домой</a>" +
                          "</html>");
              }
          }else {
              pw.print("<html>" +
                      "<h3>ID должен быть больше 0</h3>" +
                      "<a href=\"index.jsp\">Домой</a>" +
                      "</html>");
          }

      }*/
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter pw = response.getWriter();
        int id = Integer.parseInt(request.getParameter("id"));
        Map<String, Object> resultMap = new HashMap<>();
        if (id == 0) {
            Map<Integer, User> userList = model.getFromList();
            resultMap.put("users", userList);
        } else if (id > 0) {
            if (id > model.getFromList().size()) {
                resultMap.put("error", "Такого пользователя нет :(");
            } else {
                User user = model.getFromList().get(id);
                resultMap.put("user", user);
            }
        } else {
            resultMap.put("error", "ID должен быть больше 0");
        }
        pw.print(gson.toJson(resultMap));
    }
}


