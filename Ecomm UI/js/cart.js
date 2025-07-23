let cart = JSON.parse(localStorage.getItem("cart")) || [];

function loadCart() {
  let cart = JSON.parse(localStorage.getItem("cart")) || [];
  let cartItems = document.getElementById("cart-items");

  // Check if cart-items element exists (only on cart.html)
  if (!cartItems) {
    console.log("Cart items container not found - probably not on cart page");
    return;
  }

  let totalAmount = 0;
  cartItems.innerHTML = "";

  cart.forEach((item, index) => {
    let itemTotal = item.price * item.quantity;
    totalAmount += itemTotal;

    cartItems.innerHTML += `
            <tr>
                <td><img src="${item.imageUrl}" width="50" alt="${item.name}"></td>
                <td>${item.name}</td>
                <td>₹${item.price}</td>
                <td>
                    <button class="btn btn-sm btn-secondary" onclick="changeQuantity(${index}, -1)">-</button>
                    <span class="mx-2">${item.quantity}</span>
                    <button class="btn btn-sm btn-secondary" onclick="changeQuantity(${index}, 1)">+</button>
                </td>
                <td>₹${itemTotal}</td>
                <td><button class="btn btn-danger btn-sm" onclick="removeFromCart(${index})">X</button></td>
            </tr>
        `;
  });

  let totalAmountElement = document.getElementById("total-amount");
  if (totalAmountElement) {
    totalAmountElement.innerText = totalAmount;
  }
}

function addToCart(id, name, price, imageUrl) {
  console.log("Adding product to cart:", id, name, price, imageUrl);

  price = parseFloat(price);
  let itemIndex = cart.findIndex((item) => item.id === id);

  if (itemIndex !== -1) {
    cart[itemIndex].quantity += 1;
  } else {
    cart.push({
      id: id,
      name: name,
      price: price,
      imageUrl: imageUrl,
      quantity: 1,
    });
  }

  localStorage.setItem("cart", JSON.stringify(cart));
  updateCartCounter();

  // Show success message
  alert(`${name} added to cart!`);
}

function updateCartCounter() {
  let cart = JSON.parse(localStorage.getItem("cart")) || [];
  let cartBadge = document.querySelector(".cart-badge");

  if (cartBadge) {
    // Count total items (not just unique products)
    let totalItems = cart.reduce((total, item) => total + item.quantity, 0);
    cartBadge.innerText = totalItems;
  }
}

function changeQuantity(index, change) {
  let cart = JSON.parse(localStorage.getItem("cart")) || [];

  if (change === undefined) {
    // Remove item completely
    cart.splice(index, 1);
  } else {
    cart[index].quantity += change;
    if (cart[index].quantity <= 0) {
      cart.splice(index, 1);
    }
  }

  localStorage.setItem("cart", JSON.stringify(cart));
  loadCart();
  updateCartCounter();
}

function removeFromCart(index) {
  let cart = JSON.parse(localStorage.getItem("cart")) || [];
  cart.splice(index, 1);
  localStorage.setItem("cart", JSON.stringify(cart));
  loadCart();
  updateCartCounter();
}

function checkout() {
  let cart = JSON.parse(localStorage.getItem("cart")) || [];

  if (cart.length === 0) {
    alert("Your cart is empty!");
    return;
  }

  let totalAmount = cart.reduce(
    (total, item) => total + item.price * item.quantity,
    0
  );
  alert(`Total Amount: ₹${totalAmount}\nRedirecting to payment gateway...`);

  // Clear cart after checkout
  localStorage.removeItem("cart");
  loadCart();
  updateCartCounter();
}

// Initialize when DOM is loaded
document.addEventListener("DOMContentLoaded", function () {
  // Load cart only if on cart page
  if (document.getElementById("cart-items")) {
    loadCart();
  }

  // Always update cart counter
  updateCartCounter();
});
