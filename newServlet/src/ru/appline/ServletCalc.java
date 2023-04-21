package ru.appline;
import com.google.gson.Gson;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = "/calc")
public class ServletCalc extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final Gson gson = new Gson();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        BufferedReader reader = request.getReader();
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }
        String body = stringBuilder.toString();
        HashMap requestMap = gson.fromJson(body, HashMap.class);

        double a = Double.parseDouble((String) requestMap.get("a"));
        double b = Double.parseDouble((String) requestMap.get("b"));
        String math = (String) requestMap.get("math");

        Map<String, Double> resultMap = new HashMap<>();

        switch (math) {
            case "+":
                resultMap.put("result", a + b);
                break;
            case "-":
                resultMap.put("result", a - b);
                break;
            case "*":
                resultMap.put("result", a * b);
                break;
            case "/":
                resultMap.put("result", a / b);
                break;
            case "%":
                resultMap.put("result", a % b);
                break;
//            default:
//                resultMap.put("error", );
//                break;
        }

        PrintWriter pw = response.getWriter();
        pw.print(gson.toJson(resultMap));
    }
}