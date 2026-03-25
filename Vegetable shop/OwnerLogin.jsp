<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Owner Home</title>
    <style>
        /* Body Styling with Background Image */
        body {
            background-image: url('image/vege.jpg'); /* Your background image */
            background-size: cover;
            background-position: center;
            background-attachment: fixed;
            font-family: Arial, sans-serif;
            color: #333;
            margin: 0;
            padding: 0;
            height: 100vh;
        }

        /* Container for form */
        .container {
            max-width: 500px;
          margin :50px  auto;
            background: rgba(255, 255, 255, 0.8); /* Transparent background */
            padding: 30px;
            border-radius: 12px;
            box-shadow: 0 8px 16px rgba(0, 0, 0, 0.3);
            text-align: center;
        }

        h1 {
            color: #4CAF50;
            font-size: 2.2em;
            margin-bottom: 20px;
            font-weight: bold;
        }

        /* Styling the input fields */
        form input[type="text"],
        form input[type="password"],
        form input[type="submit"] {
            width: 100%;
            padding: 12px;
            margin: 10px 0;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-sizing: border-box;
            font-size: 16px;
        }

        /* Styling the submit button */
        form input[type="submit"] {
            background-color: #4CAF50;
            color: white;
            border: none;
            cursor: pointer;
            font-weight: bold;
        }

        form input[type="submit"]:hover {
            background-color: #45a049;
        }

        /* Styling error message */
        .error-message {
            color: #e74c3c;
            font-size: 16px;
            margin-top: 10px;
        }

        .error-message a {
            color: #2980b9;
            text-decoration: none;
            font-weight: bold;
        }

        .error-message a:hover {
            text-decoration: underline;
        }

    </style>
</head>
<body>
  <div class="container">
        <h1>Owner Login</h1>
        <form action="OwnerLoginServlet" method="post">
            <input type="text" name="uname" placeholder="Username" required /><br>
            <input type="password" name="pword" placeholder="Password" required /><br>
            <input type="submit" value="Login" />
        </form>

        <!-- Error message display -->
        <c:if test="${not empty errorMessage}">
            <div class="error-message">
                ${errorMessage} <a href="OwnerRegistration.html">Register here</a>.
            </div>
        </c:if>
    </div>
</body>
</html>