```java
package com.codegym;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "DiscountServlet", urlPatterns = {"/display-discount"})
public class DiscountServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String description = request.getParameter("description");
        String priceInput = request.getParameter("price");
        String percentInput = request.getParameter("discountPercent");

        try {
            if (description == null || description.trim().isEmpty()
                    || priceInput == null || percentInput == null) {
                throw new IllegalArgumentException("Vui lòng nhập đầy đủ thông tin.");
            }

            double price = Double.parseDouble(priceInput);
            double discountPercent = Double.parseDouble(percentInput);

            if (!Double.isFinite(price) || !Double.isFinite(discountPercent)
                    || price < 0 || discountPercent < 0 || discountPercent > 100) {
                throw new IllegalArgumentException("Giá hoặc tỷ lệ chiết khấu không hợp lệ.");
            }

            double discountAmount = price * discountPercent * 0.01;
            double discountPrice = price - discountAmount;

            try (PrintWriter out = response.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html lang='en'>");
                out.println("<head><meta charset='UTF-8'><title>Discount Result</title></head>");
                out.println("<body>");
                out.println("<h1>Discount Result</h1>");

                out.println("<p>Product Description: "
                        + escapeHtml(description.trim()) + "</p>");
                out.println("<p>List Price: "
                        + String.format(Locale.US, "%.2f", price) + "</p>");
                out.println("<p>Discount Percent: "
                        + String.format(Locale.US, "%.2f", discountPercent) + "%</p>");
                out.println("<p>Discount Amount: "
                        + String.format(Locale.US, "%.2f", discountAmount) + "</p>");
                out.println("<p>Discount Price: "
                        + String.format(Locale.US, "%.2f", discountPrice) + "</p>");

                out.println("<a href='" + request.getContextPath()
                        + "/index.jsp'>Back</a>");
                out.println("</body>");
                out.println("</html>");
            }

        } catch (NumberFormatException | IllegalArgumentException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().println(
                    "Invalid input: " + escapeHtml(e.getMessage())
            );
        }
    }

    private String escapeHtml(String value) {
        if (value == null) {
            return "";
        }

        return value.replace("&", "&amp;")
                    .replace("<", "&lt;")
                    .replace(">", "&gt;")
                    .replace("\"", "&quot;")
                    .replace("'", "&#39;");
    }
}
```
