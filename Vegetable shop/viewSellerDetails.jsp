<%@ page contentType="text/html; charset=UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Seller Details</title>
    <style>
        /* Add your CSS here */
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
            background-color: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1);
        }
        h1 {
            color: #4CAF50;
            text-align: center;
            margin-bottom: 20px;
        }
        .bill {
            background-color: #f9f9f9;
            padding: 20px;
            border-radius: 8px;
            margin-bottom: 20px;
            box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.1);
        }
        .bill h3 {
            color: #333;
            margin-bottom: 10px;
        }
        .bill p {
            font-size: 14px;
            margin: 5px 0;
        }
        .product-list {
            margin-top: 20px;
            border-top: 2px solid #f1f1f1;
            padding-top: 10px;
        }
        .product {
            background-color: #fff;
            padding: 15px;
            margin-bottom: 10px;
            border: 1px solid #e0e0e0;
            border-radius: 5px;
        }
        .no-details {
            text-align: center;
            font-size: 18px;
            color: #888;
        }
        .btn {
            display: inline-block;
            padding: 10px 20px;
            margin-top: 20px;
            background-color: #4CAF50;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            transition: background-color 0.3s ease;
        }
        .btn:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Seller Details</h1>
                        <a href="OwnerHome.jsp" class="btn">Home Page</a>
        
        <c:if test="${empty billList}">
            <p class="no-details">No seller details available.</p>
        </c:if>

        <c:forEach var="bill" items="${billList}">
        
            <div class="bill">
                <h3>Bill ID: ${bill.billId}</h3>
                <p><strong>Seller Name:</strong> ${bill.sellerName}</p>
                <p><strong>Seller Number:</strong> ${bill.sellerNumber}</p>
                <p><strong>Total Price:</strong> ₹${bill.totalPrice}</p>
                <p><strong>Owner Profit:</strong> ₹${bill.ownerProfit}</p>
                <p><strong>Worker Charges:</strong> ₹${bill.workerCharges}</p>

                <!-- Products within the same Bill -->
                <div class="product-list">
                    <h4>Products:</h4>
                    <c:forEach var="product" items="${bill.productList}">
    <div class="product">
        <p><strong>Product Name:</strong> ${product.productName}</p>
        <p><strong>Price per KG:</strong> ₹${product.pricePerKg}</p>
        <p><strong>Weight:</strong> ${product.weight} KG</p>
        <p><strong>Total Price:</strong> ₹${product.totalPrice}</p>  <!-- Removed Owner Profit and Worker Charges -->
    </div>
</c:forEach>
                </div>
            </div>
        </c:forEach>

    </div>
</body>
</html>