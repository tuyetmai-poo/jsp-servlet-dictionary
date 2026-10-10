```jsp
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Product Discount Calculator</title>
</head>
<body>
    <h1>Product Discount Calculator</h1>

    <form action="${pageContext.request.contextPath}/display-discount" method="POST">
        <label for="description">Product Description:</label>
        <input type="text" id="description" name="description" required>
        <br><br>

        <label for="price">List Price:</label>
        <input type="number" id="price" name="price"
               min="0" step="any" required>
        <br><br>

        <label for="discountPercent">Discount Percent:</label>
        <input type="number" id="discountPercent" name="discountPercent"
               min="0" max="100" step="any" required>
        <br><br>

        <button type="submit">Calculate Discount</button>
    </form>
</body>
</html>
```
