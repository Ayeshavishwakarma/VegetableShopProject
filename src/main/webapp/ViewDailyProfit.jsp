<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, pack1.Bill, pack1.SellerProfit" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>View Profit</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            padding: 0;
            background-color: #f4f4f9;
        }

        .container {
            max-width: 1200px;
            margin: 0 auto;
            background: #ffffff;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
        }

        h1, h2, h3 {
            text-align: center;
            color: #333;
        }

        .go-back-btn, button {
            display: inline-block;
            padding: 10px 15px;
            background-color: #4CAF50;
            color: white;
            text-decoration: none;
            font-size: 15px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            margin: 5px 0;
        }

        .go-back-btn:hover, button:hover {
            background-color: #45a049;
        }

        form {
            margin-bottom: 20px;
            padding: 15px;
            background: #f9f9f9;
            border-radius: 8px;
        }

        label {
            font-weight: bold;
            display: block;
            margin-bottom: 8px;
        }

        input[type="date"], input[type="month"] {
            padding: 10px;
            width: 250px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        .results {
            margin-top: 20px;
        }

        .section {
            margin-top: 30px;
            padding: 15px;
            background: #fafafa;
            border-radius: 8px;
            border: 1px solid #ddd;
        }

        .summary-box {
            background: #e8f5e9;
            padding: 15px;
            border-radius: 8px;
            font-size: 20px;
            font-weight: bold;
            color: #2e7d32;
            text-align: center;
            margin-top: 20px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 15px;
            background: white;
        }

        table th, table td {
            border: 1px solid #ccc;
            padding: 10px;
            text-align: center;
            font-size: 14px;
        }

        table th {
            background-color: #4CAF50;
            color: white;
        }

        .no-data {
            text-align: center;
            color: #888;
            font-weight: bold;
            margin-top: 10px;
        }

        .error {
            margin-top: 20px;
            padding: 10px;
            border-radius: 4px;
            background-color: #ffdddd;
            color: #d8000c;
            text-align: center;
        }

        @media print {
            body {
                background: white;
                margin: 0;
                padding: 10px;
            }

            .container {
                box-shadow: none;
                max-width: 100%;
                padding: 0;
            }

            .go-back-btn, button, form {
                display: none;
            }

            table th {
                background-color: #ddd !important;
                color: black !important;
            }
        }
    </style>
</head>
<body>
<div class="container">
    <h1>View Profit Report</h1>

    <a href="OwnerHome.jsp" class="go-back-btn">Home Page</a>

    <!-- Daily Profit Form -->
    <form method="POST" action="ViewDailyProfitServlet">
        <label for="date">Select Date (Daily Profit):</label>
        <input type="date" id="date" name="date" value="${selectedDate}" />
        <br>
        <button type="submit" name="profitType" value="daily">View Daily Profit</button>
    </form>

    <!-- Monthly Profit Form -->
    <form method="POST" action="ViewDailyProfitServlet">
        <label for="month">Select Month (Monthly Profit):</label>
        <input type="month" id="month" name="month" value="${selectedMonth}" />
        <br>
        <button type="submit" name="profitType" value="monthly">View Monthly Profit</button>
    </form>

    <c:if test="${not empty profitType}">
        <div class="results">

            <div class="summary-box">
                Total Owner Profit: ₹${totalProfit}
            </div>

            <!-- DAILY BILL LIST -->
            <c:if test="${profitType == 'daily'}">
                <div class="section">
                    <h3>Daily Bills Summary</h3>

                    <c:if test="${not empty bills}">
                        <table>
                            <tr>
                                <th>Bill ID</th>
                                <th>Shop Name</th>
                                <th>Seller Name</th>
                                <th>Seller Number</th>
                                <th>Total Price</th>
                                <th>Owner Profit</th>
                                <th>Worker Charges</th>
                                <th>Bill Date</th>
                            </tr>
                            <c:forEach var="bill" items="${bills}">
                                <tr>
                                    <td>${bill.billId}</td>
                                    <td>${bill.shopName}</td>
                                    <td>${bill.sellerName}</td>
                                    <td>${bill.sellerNumber}</td>
                                    <td>₹${bill.totalPrice}</td>
                                    <td>₹${bill.ownerProfit}</td>
                                    <td>₹${bill.workerCharges}</td>
                                    <td>${bill.billDate}</td>
                                </tr>
                            </c:forEach>
                        </table>
                    </c:if>

                    <c:if test="${empty bills}">
                        <div class="no-data">No bills found for selected date.</div>
                    </c:if>
                </div>
            </c:if>

            <!-- SELLER SUMMARY -->
            <div class="section">
                <h3>Seller Wise Profit Summary</h3>

                <c:if test="${not empty sellerProfits}">
                    <table>
                        <tr>
                            <th>Seller Name</th>
                            <th>Total Owner Profit</th>
                        </tr>
                        <c:forEach var="sp" items="${sellerProfits}">
                            <tr>
                                <td>${sp.sellerName}</td>
                                <td>₹${sp.totalPrice}</td>
                            </tr>
                        </c:forEach>
                    </table>
                </c:if>

                <c:if test="${empty sellerProfits}">
                    <div class="no-data">No seller profit data found.</div>
                </c:if>
            </div>

            <!-- SELLER PRODUCT DETAILS -->
            <div class="section">
                <h3>Seller Product Profit Details</h3>

                <c:if test="${not empty sellerProductProfits}">
                    <table>
                        <tr>
                            <th>Seller Name</th>
                            <th>Seller Number</th>
                            <th>Bill Date</th>
                            <th>Product Name</th>
                            <th>Weight</th>
                            <th>Price/KG</th>
                            <th>Total Price</th>
                            <th>Owner Profit</th>
                            <th>Worker Charges</th>
                            <th>Seller Share</th>
                        </tr>
                        <c:forEach var="spp" items="${sellerProductProfits}">
                            <tr>
                                <td>${spp.sellerName}</td>
                                <td>${spp.sellerNumber}</td>
                                <td>${spp.billDate}</td>
                                <td>${spp.productName}</td>
                                <td>${spp.weight}</td>
                                <td>₹${spp.pricePerKg}</td>
                                <td>₹${spp.totalPrice}</td>
                                <td>₹${spp.ownerProfit}</td>
                                <td>₹${spp.workerCharges}</td>
                                <td>₹${spp.sellerShare}</td>
                            </tr>
                        </c:forEach>
                    </table>
                </c:if>

                <c:if test="${empty sellerProductProfits}">
                    <div class="no-data">No seller product details found.</div>
                </c:if>
            </div>

            <button onclick="window.print()">Print</button>
        </div>
    </c:if>

    <c:if test="${not empty error}">
        <div class="error">
            ${error}
        </div>
    </c:if>
</div>
</body>
</html>