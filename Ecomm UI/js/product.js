const BASE_URL = "http://localhost:8080";

let allProducts = [];

function renderProducts(products) {
  const list = document.getElementById("products-list");
  list.innerHTML = "";
  if (products.length === 0) {
    list.innerHTML =
      '<div class="col-12 text-center text-muted">No products found.</div>';
    return;
  }
  products.forEach((product) => {
    list.innerHTML += `
      <div class="col-md-4 col-lg-3">
        <div class="card h-100 shadow-sm">
          <img src="${
            product.productImageUrl || "img/default.png"
          }" class="card-img-top" alt="${product.name}">
          <div class="card-body d-flex flex-column">
            <h5 class="card-title">${product.name}</h5>
            <p class="card-text">${product.description || ""}</p>
            <div class="mt-auto">
              <span class="fw-bold text-success">₹${product.productPrice}</span>
              <button class="btn btn-primary btn-sm float-end"
                onclick="addToCart(${product.id}, '${product.name}', ${
      product.productPrice
    }, '${product.productImageUrl}')">
                <i class="fas fa-cart-plus"></i> Add to Cart
              </button>
            </div>
          </div>
        </div>
      </div>
    `;
  });
}

function filterProducts() {
  const search = document.getElementById("searchInput").value.toLowerCase();
  const category = document.getElementById("categoryFilter").value;
  let filtered = allProducts.filter(
    (p) =>
      (!category || p.category === category) &&
      (p.name.toLowerCase().includes(search) ||
        (p.description && p.description.toLowerCase().includes(search)))
  );
  renderProducts(filtered);
}

// Add to Cart logic (copied from cart.js)
function addToCart(id, name, price, imageUrl) {
  let cart = JSON.parse(localStorage.getItem("cart")) || [];
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
  alert(`${name} added to cart!`);
}

// Update cart badge
function updateCartCounter() {
  let cart = JSON.parse(localStorage.getItem("cart")) || [];
  let cartBadge = document.querySelector(".cart-badge");
  if (cartBadge) {
    let totalItems = cart.reduce((total, item) => total + item.quantity, 0);
    cartBadge.innerText = totalItems;
  }
}

document.addEventListener("DOMContentLoaded", () => {
  fetch(`${BASE_URL}/products/getAll`)
    .then((res) => res.json())
    .then((products) => {
      allProducts = products;
      renderProducts(products);
      updateCartCounter();
    });

  document
    .getElementById("searchInput")
    .addEventListener("input", filterProducts);
  document
    .getElementById("categoryFilter")
    .addEventListener("change", filterProducts);
});
