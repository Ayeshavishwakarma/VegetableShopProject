<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>View Profit</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            padding: 0;
            background-color: #f4f4f9;
        }
        .container {
            max-width: 600px;
            margin: 0 auto;
            background: #ffffff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        h1 {
            text-align: center;
            color: #333333;
        }
        form {
            display: flex;
            flex-direction: column;
        }
        label {
            margin-bottom: 8px;
            font-weight: bold;
        }
        input[type="date"], input[type="month"] {
            padding: 10px;
            margin-bottom: 20px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        button {
            padding: 10px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
        }
        button:hover {
            background-color: #45a049;
        }
        .go-back-btn {
            display: inline-block;
            margin-bottom: 20px;
            padding: 10px 15px;
            background-color: #4CAF50;
            color: white;
            text-decoration: none;
            font-size: 16px;
            border: none;
            border-radius: 4px;
            text-align: center;
            cursor: pointer;
        }
        .go-back-btn:hover {
            background-color: #45a049;
        }
        .results {
            margin-top: 20px;
            padding: 10px;
            border-radius: 4px;
            background-color: #e8f5e9;
        }
        .error {
            margin-top: 20px;
            padding: 10px;
            border-radius: 4px;
            background-color: #ffdddd;
            color: #d8000c;
        }

        /* Print Styles */
        @media print {
            body {
                background-color: white;
                padding: 10px;
            }
            .container {
                width: 100%;
                margin: 0;
                box-shadow: none;
                padding: 0;
            }
            .results, .error {
                background-color: white;
                padding: 5px;
                border: 1px solid #ccc;
            }
            button, .go-back-btn {
                display: none;
            }
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>View Profit</h1>
        <!-- Styled Go Back Button -->
        <a href="OwnerHome.jsp" class="go-back-btn">Home Page</a>

        <!-- Form to select either Daily or Monthly Profit -->
        <form method="POST" action="ViewDailyProfitServlet">
            <label for="date">Select Date (Daily Profit):</label>
            <input type="date" id="date" name="date" value="${selectedDate}" />
            <button type="submit" name="profitType" value="daily">View Daily Profit</button>
        </form>

        <form method="POST" action="ViewDailyProfitServlet">
            <label for="month">Select Month (Monthly Profit):</label>
            <input type="month" id="month" name="month" value="${selectedMonth}" />
            <button type="submit" name="profitType" value="monthly">View Monthly Profit</button>
        </form>

        <!-- Section to display results (dynamically populated from JSP) -->
        <div class="results">
            <h3>Total Profit: ₹<span id="totalProfit">${totalProfit}</span></h3>

            <h4>Bills:</h4>
            <ul id="billsList">
                <c:forEach var="bill" items="${bills}">
                    <li>Bill ID: ${bill.billId}, Shop Name: ${bill.shopName}, Seller Name: ${bill.sellerName}, Total Price: ₹${bill.totalPrice}, Owner Profit: ₹${bill.ownerProfit}</li>
                </c:forEach>
                <c:if test="${empty bills}">
                    <li>No bills available for the selected ${profitType}.</li>
                </c:if>
            </ul>

            <h4>Seller Profits:</h4>
            <ul id="sellerProfitsList">
                <c:forEach var="sp" items="${sellerProfits}">
                    <li>Seller Name: ${sp.sellerName}, Total Profit: ₹${sp.totalProfit}</li>
                </c:forEach>
                <c:if test="${empty sellerProfits}">
                    <li>No seller profits available for the selected ${profitType}.</li>
                </c:if>
            </ul>
        </div>

        <!-- Print Button -->
        <button onclick="window.print()">Print</button>

        <!-- Error section (dynamically shown if there are errors) -->
        <div class="error" style="display: ${error ? 'block' : 'none'};">
            <p>An error occurred while fetching data. Please try again later.</p>
        </div>
    </div>
</body>
</html>