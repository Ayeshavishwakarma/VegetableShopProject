<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Product Entry</title>
    <script>
        let productCount = 0;

        function addProductRow() {
            productCount++;

            const productTable = document.getElementById("product_table");
            const newRow = document.createElement("tr");
            newRow.id = `product_row_${productCount}`;

            newRow.innerHTML = `
                <td><input type="text" id="product_name_${productCount}" name="product_name_${productCount}" placeholder="Product Name" required></td>
                <td><input type="number" id="price_per_kg_${productCount}" name="price_per_kg_${productCount}" placeholder="Price per KG" required></td>
                <td><input type="number" id="weight_${productCount}" name="weight_${productCount}" placeholder="Weight (KG)" required></td>
            `;

            productTable.appendChild(newRow);
        }

        function calculateAll() {
            let totalPriceSum = 0;
            for (let i = 1; i <= productCount; i++) {
                const pricePerKg = parseFloat(document.getElementById(`price_per_kg_${i}`).value);
                const weight = parseFloat(document.getElementById(`weight_${i}`).value);
                if (!isNaN(pricePerKg) && !isNaN(weight)) {
                    const totalPrice = pricePerKg * weight;
                    totalPriceSum += totalPrice;
                } else {
                    alert(`Please fill all fields correctly for Product ${i}`);
                    return;
                }
            }

            const workerCharges = parseFloat(document.getElementById("worker_charges").value);
            if (isNaN(workerCharges) || workerCharges < 0) {
                alert("Please enter a valid worker charge.");
                return;
            }

            document.getElementById("total_price").value = totalPriceSum;
            document.getElementById("owner_profit").value = totalPriceSum * 0.1; // Example: Owner profit is 10% of total
            document.getElementById("worker_charges").value = workerCharges;
        }
    </script>
</head>
<body>
    <h2>Product Entry</h2>
    <form action="SaveProductServlet" method="post">
        <label for="shop_number">Shop Number:</label>
        <input type="text" id="shop_number" name="shop_number" required>
        <label for="shop_name">Shop Name:</label>
        <input type="text" id="shop_name" name="shop_name" required><br>

        <label for="seller_name">Seller Name:</label>
        <input type="text" id="seller_name" name="seller_name" required>
        <label for="seller_number">Seller Number:</label>
        <input type="text" id="seller_number" name="seller_number" required><br>

        <label for="worker_charges">Worker Charges:</label>
        <input type="number" id="worker_charges" name="worker_charges" required><br>

        <h3>Products</h3>
        <table id="product_table">
            <tr>
                <th>Product Name</th>
                <th>Price per KG</th>
                <th>Weight (KG)</th>
            </tr>
        </table>
        <button type="button" onclick="addProductRow()">Add Product</button><br><br>

        <button type="button" onclick="calculateAll()">Calculate Total</button><br><br>

        <label for="total_price">Total Price:</label>
        <input type="text" id="total_price" name="total_price" readonly><br>

        <label for="owner_profit">Owner Profit:</label>
        <input type="text" id="owner_profit" name="owner_profit" readonly><br>

        <input type="hidden" id="product_count" name="product_count" value="0">
        <input type="submit" value="Submit">
    </form>
</body>
</html>