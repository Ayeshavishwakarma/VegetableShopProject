<%@page import="pack1.Product"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="java.sql.Timestamp"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>

<html>
<head>
    <title>बिल विवरण</title>
    <style>
    body {
    font-family: Arial, sans-serif;
    background-color: #f4f4f9;
    color: #333;
    margin: 0;
    padding: 0;
}

.container {
    width: 80%;
    margin: 20px auto;
    background-color: #ffffff;
    padding: 20px;
    border-radius: 8px;
    box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
}

/* Header styling */
.header {
    text-align: center;
    margin-bottom: 20px;
}

.main-heading {
    font-size: 30px;
    font-weight: bold;
    color: #333;
    margin: 0;
}

.sub-heading {
    font-size: 16px;
    color: #555;
}

/* Table styling */
.table-container {
    margin-bottom: 30px;
}

table {
    width: 100%;
    border-collapse: collapse;
    margin-top: 10px;
}

th, td {
    padding: 12px;
    text-align: left;
    border-bottom: 1px solid #ddd;
}

th {
    background-color: #f1f1f1;
    font-size: 18px;
}

td {
    font-size: 16px;
}

tr:hover {
    background-color: #f9f9f9;
}

/* Buttons styling */
.button-container {
    display: flex;
    justify-content: center;
    margin-top: 30px;
}

.button {
    background-color: #4CAF50;
    color: white;
    border: none;
    padding: 10px 20px;
    font-size: 16px;
    cursor: pointer;
    border-radius: 4px;
    margin: 0 10px;
    text-decoration: none;
}

.button:hover {
    background-color: #45a049;
}

.button:active {
    background-color: #388e3c;
}

/* Footer styling */
.footer {
    text-align: center;
    font-size: 14px;
    color: #777;
    margin-top: 20px;
}

/* Responsive styling */
@media (max-width: 768px) {
    .container {
        width: 95%;
    }

    .main-heading {
        font-size: 24px;
    }

    .sub-heading {
        font-size: 14px;
    }

    table th, table td {
        font-size: 14px;
        padding: 8px;
    }

    .button {
        padding: 8px 16px;
    }
}
    </style>
    <script>
        function printBill() {
            window.print();
        }
    </script>
</head>
<body>
    <div class="container">
        <!-- Header Section -->
        <div class="header">
            <p class="main-heading">भानु प्रताप (छोटू) परमेश्वर दास</p>
  <p class="sub-heading">
    मो.: 9131245430 (भानु प्रताप) | छोटू: 9977414266 (पन्नू भैया)<br>
    आलू, प्याज, टमाटर, लहसुन, फल, एवं हरी सब्जी की थोक विक्रेता<br>
    दुकान नं.: 09 जवाहर कृषि उपज मंडी गाडरवारा
</p>
        </div>

        <!-- Bill Details Table -->
        <div class="table-container">
            <h2>बिल की जानकारी</h2>
            <table>
                <tr>
                    <th>फील्ड</th>
                    <th>मूल्य</th>
                </tr>
                <tr>
                    <td>दुकान नंबर</td>
                    <td><%= request.getAttribute("shopNumber") != null ? request.getAttribute("shopNumber") : "नहीं मिला" %></td>
                </tr>
                <tr>
                    <td>दुकान का नाम</td>
                    <td><%= request.getAttribute("shopName") != null ? request.getAttribute("shopName") : "नहीं मिला" %></td>
                </tr>
                <tr>
                    <td>विक्रेता का नाम</td>
                    <td><%= request.getAttribute("sellerName") != null ? request.getAttribute("sellerName") : "नहीं मिला" %></td>
                </tr>
                <tr>
                    <td>विक्रेता का नंबर</td>
                    <td><%= request.getAttribute("sellerNumber") != null ? request.getAttribute("sellerNumber") : "नहीं मिला" %></td>
                </tr>
                <tr>
                    <td>कार्मचारी</td>
                    <td><%= request.getAttribute("workerCharges") != null ? request.getAttribute("workerCharges") : "नहीं मिला" %></td>
                </tr>
                <tr>
                    <td>मालिक की कमाई</td>
                    <td><%= request.getAttribute("ownerProfit") != null ? request.getAttribute("ownerProfit") : "नहीं मिला" %></td>
                </tr>
               
                <tr>
                    <td>बिल आईडी</td>
                    <td><%= request.getAttribute("billId") != null ? request.getAttribute("billId") : "नहीं मिला" %></td>
                </tr>
                <tr>
                    <td>तारीख</td>
                    <td>
                        <%
                            Timestamp currentDate = (Timestamp) request.getAttribute("currentDate");
                            if (currentDate != null) {
                                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                                String formattedDate = sdf.format(new Date(currentDate.getTime()));
                                out.print(formattedDate);
                            } else {
                                out.print("नहीं मिला");
                            }
                        %>
                    </td>
                </tr>
            </table>
        </div>

        <!-- Product Details Table -->
<div class="table-container">
    <h2>उत्पाद जानकारी</h2>
    <table>
        <tr>
            <th>उत्पाद का नाम</th>
            <th>दाम प्रति किलो</th>
            <th>वजन (KG)</th>
            <th>बैग्स की संख्या</th>
        </tr>
        <% 
            // Mapping of product names to Hindi equivalents
            Map<String, String> productNameMap = new HashMap<>();
            productNameMap.put("potato", "आलू");
            productNameMap.put("onion", "प्याज");
            productNameMap.put("tomato", "टमाटर");
            productNameMap.put("garlic", "लहसुन");
            productNameMap.put("cabbage", "पत्ता गोभी");
            productNameMap.put("carrot", "गाजर");
            productNameMap.put("apple", "सेब");
            productNameMap.put("banana", "केला");

            List<Product> products = (List<Product>) request.getAttribute("products");
            if (products != null && !products.isEmpty()) {
                for (Product product : products) {
                    String hindiProductName = productNameMap.getOrDefault(product.getProductName().toLowerCase(), product.getProductName());
        %>
        <tr>
            <td><%= hindiProductName %></td>
            <td><%= product.getPricePerKg() %></td>
            <td><%= product.getWeight() %></td>
            <td><%= product.getNumberOfPacks() %></td>
        </tr>
        <% 
                }
            } else {
        %>
        <tr>
            <td colspan="4">कोई जानकारी नहीं मिली।</td>
        </tr>
        <% } %>
    </table>
</div>

<!-- Move the Total Price to the last position -->
<div class="table-container">
    <table>
        <tr>
            <td>कुल की कीमत</td>
            <td><%= request.getAttribute("totalPrice") != null ? request.getAttribute("totalPrice") : "नहीं मिला" %></td>
        </tr>
    </table>
</div>

<!-- Add Seal/Sign Section -->
<div class="seal-container" style="text-align: center; margin-top: 20px;">
    <p>दस्तखत/सील</p>
    <hr style="width: 200px; margin: 0 auto;">
</div>

<!-- Buttons -->
<div class="button-container">
    <button class="button" onclick="printBill()">प्रिंट करें</button>
    <a href="ProductEntry.html" class="button">वापस जाएं</a>
</div>

        <!-- Footer -->
        <div class="footer">
            <p>ध्यान दें: किसी भी प्रकार की गलती होने पर तुरंत सूचित करें। धन्यवाद!</p>
        </div>
    </div>
</body>
</html>