<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Owner Login</title>
    <style>
        body {
            background-image: url('image/vege.jpg');
            background-size: cover;
            background-position: center;
            background-repeat: no-repeat;
            margin: 0;
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            font-family: 'Arial', sans-serif;
            text-align: center;
        }
        .container {
            background: rgba(34, 139, 34, 0.85); /* Improved background color */
            padding: 40px;
            border-radius: 15px;
            box-shadow: 0 10px 25px rgba(0, 0, 0, 0.4);
            width: 90%;
            max-width: 400px;
            color: white; /* Ensures text visibility */
        }
        h1 {
            margin-bottom: 20px;
            font-size: 28px;
            font-weight: bold;
            text-shadow: 2px 2px 5px rgba(0, 0, 0, 0.5); /* Adds text shadow */
        }
        p {
            margin-bottom: 30px;
            font-size: 16px;
            line-height: 1.5;
        }
        a {
            display: block;
            margin: 15px 0;
            padding: 15px 20px;
            font-size: 16px;
            font-weight: bold;
            color: white;
            text-decoration: none;
            background: linear-gradient(135deg, #32CD32, #228B22); /* Gradient effect */
            border-radius: 10px;
            transition: transform 0.3s, background-color 0.3s; /* Smooth hover effect */
        }
        a:hover {
            background: linear-gradient(135deg, #7CFC00, #32CD32); /* Lighter gradient on hover */
            transform: scale(1.05); /* Slightly enlarge buttons */
        }
        @media screen and (max-width: 600px) {
            .container {
                width: 95%;
                padding: 20px;
            }
            a {
                font-size: 14px;
                padding: 12px 15px;
            }
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Welcome, ${obean.uName}!</h1>
        <p>Please select an option below:</p>
        <a href="ProductEntry.html">New Bill Entry</a>
        <a href="ViewSellerDetailsServlet">View Seller Details</a>
        <a href="ViewDailyProfit.jsp">View Profit</a>
        <a href="LogoutServlet">Logout</a>
        
    </div>
</body>
</html>