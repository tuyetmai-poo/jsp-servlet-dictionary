```java
package com.codegym;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "DictionaryServlet", urlPatterns = {"/translate"})
public class DictionaryServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        Map<String, String> dictionary = new HashMap<>();
        dictionary.put("hello", "Xin chào");
        dictionary.put("how", "Thế nào");
        dictionary.put("book", "Quyển sách");
        dictionary.put("computer", "Máy tính");
        dictionary.put("student", "Sinh viên");

        String searchWord = request.getParameter("word");

        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html lang='vi'>");
            out.println("<head>");
            out.println("<meta charset='UTF-8'>");
            out.println("<title>Kết quả tra cứu</title>");
            out.println("</head>");

            out.println("<body style='font-family: Arial; text-align: center; margin-top: 100px;'>");

            if (searchWord != null && !searchWord.trim().isEmpty()) {
                String word = searchWord.trim().toLowerCase();
                String result = dictionary.get(word);

                if (result != null) {
                    out.println("<h2>Từ khóa: " + escapeHtml(searchWord.trim()) + "</h2>");
                    out.println("<h3 style='color: green;'>Nghĩa tiếng Việt: "
                            + escapeHtml(result) + "</h3>");
                } else {
                    out.println("<h2 style='color: red;'>Không tìm thấy từ: "
                            + escapeHtml(searchWord.trim()) + "</h2>");
                }
            } else {
                out.println("<h2 style='color: orange;'>Vui lòng nhập từ khóa hợp lệ!</h2>");
            }

            out.println("<br>");
            out.println("<a href='index.jsp'>Quay lại</a>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    private String escapeHtml(String value) {
        return value.replace("&", "&amp;")
                    .replace("<", "&lt;")
                    .replace(">", "&gt;")
                    .replace("\"", "&quot;")
                    .replace("'", "&#39;");
    }
}
```
