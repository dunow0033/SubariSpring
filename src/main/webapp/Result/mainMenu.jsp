<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Subari Main Menu Page</title>
<style>
    .checkbox-label {
        display: inline-block;
        width: 20px; /* Adjust as needed */
    }
    
    ul {
    	list-style-type: none;
	}
	
	#order {
            display: flex;
            flex-direction: column;
            align-items: flex-start;
    }
    
     .order-item {
            display: flex;
            align-items: center;
            margin-bottom: 10px;
        }

        .order-item > * {
            margin-right: 10px;
        }

        .price-column {
            width: 100px; /* Adjust width as needed */
        }
</style>
<script>
	function handleCheckboxClick(checkbox, itemName, itemPrice)
	{
		var orderItems = document.querySelectorAll(".order-item");
		var alreadyExists = false;
		
		/*for(var i = 0; i < orderItems.length; i++) {
			var itemDesc = orderItems[i].querySelector("span");
			if(itemDesc.textContent === itemName + " -- " + itemPrice) {
				alreadyExists = true;
				break;
			}
		}*/
		
		if(!checkbox.checked) {
			for(var i = 0; i < orderItems.length; i++) {
				var itemDesc = orderItems[i].querySelector("span");
				if(itemDesc.textContent === itemName + " -- " + itemPrice) {
					orderItems[i].remove();
					updateTotalQuantity();
					updateTotalPrice();
					break;
				}
			}
		}
		
		if(checkbox.checked) {
			
			var newItem = document.createElement("div");
			newItem.classList.add("order-item");
			
			var newItemDesc = document.createElement("span");
			newItemDesc.textContent = itemName + " -- " + itemPrice;
			
			var minusButton = document.createElement("button");
			minusButton.textContent = "-";
			minusButton.onclick = function() {
				updateQuantity(newItem, -1);
				updateTotalQuantity();
				updateTotalPrice();
			};
			minusButton.style.marginRight = "5px";
			
			var quantitySpan = document.createElement("span");
			quantitySpan.textContent = 1;
			quantitySpan.classList.add("quantity");
			
			var plusButton = document.createElement("button");
			plusButton.textContent = "+";
			plusButton.onclick = function() {
				updateQuantity(newItem, 1);
				updateTotalQuantity();
				updateTotalPrice();
			};
			plusButton.style.marginLeft = "5px";
			
			var totalPrice = document.createElement("span");
			totalPrice.textContent = itemPrice.toFixed(2);
			//totalPrice.textContent = (itemPrice * parseInt(quantitySpan.textContent));
			totalPrice.classList.add("total_price");
			totalPrice.dataset.price = itemPrice;
			
			newItem.appendChild(newItemDesc);
			newItem.appendChild(minusButton);
			newItem.appendChild(quantitySpan);
			newItem.appendChild(plusButton);
			newItem.appendChild(totalPrice);
			
			document.getElementById("order").appendChild(newItem);
			
			updateTotalQuantity();
			
			updateTotalPrice();
		}
	}
	
	function updateTotalQuantity() {
		var totalQuantitySpan = document.getElementById("total_quantity");
	    var totalQuantity = calculateTotalQuantity();
	    totalQuantitySpan.textContent = "Total Quantity: " + totalQuantity;
	}
	
	function updateTotalPrice() {
		var totalPriceSpan = document.getElementById("total_price");
	    var totalPrice = calculateTotalPrice();
	    totalPriceSpan.textContent = "Total Price: " + totalPrice;
	}
	
	function calculateTotalQuantity() {
		var quantitySpans = document.querySelectorAll(".quantity");
		var totalQuantity = 0;
		for(var i = 0; i < quantitySpans.length; i++) {
			totalQuantity += parseInt(quantitySpans[i].textContent);
		}
		
		return totalQuantity;
	}
	
	function calculateTotalPrice() {
		var priceSpans = document.querySelectorAll(".total_price");
		var totalPrice = 0.00;
		for(var i = 0; i < priceSpans.length; i++) {
			totalPrice += parseFloat(priceSpans[i].textContent);
		}
		
		return totalPrice.toFixed(2);
	}
	
	function updateQuantity(newItem, change) {
		var quantitySpan = newItem.querySelector(".quantity");
		var totalPrice = newItem.querySelector(".total_price");
		var currentQuantity = parseInt(quantitySpan.textContent);
		var itemPrice = parseFloat(totalPrice.dataset.price); // Get item price from data attribute

	    // Update quantity
	    var newQuantity = currentQuantity + change;
	    if(newQuantity >= 1) {
			quantitySpan.textContent = newQuantity;
		} else {
			//newItem.parentNode.removeChild(newItem);
			newItem.remove();
		}

	    // Update total price
	    totalPrice.textContent = (itemPrice * newQuantity).toFixed(2);
	}
</script>
</head>
<body>

<a href="mainUserPage?name=${name}">Back to User Options</a>

<p>Here are your food options, ${name}</p>

<ul>
	<c:forEach items="${foodList}" var="item">
		<li>
            <label class="checkbox-label">
                <input type="checkbox" class="order_${item}" onclick="handleCheckboxClick(this, '${item.name}', ${item.price})" />
            </label>
            ${item.name} -- ${item.price}
        </li>
	</c:forEach>
</ul>

<div id="order">

</div>
<div id="total_quantity">
</div>
<div id="total_price">
</div>

</body>
</html>