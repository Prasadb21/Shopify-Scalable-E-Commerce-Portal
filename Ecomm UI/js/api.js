const BASE_URL = "http://localhost:8080";

async function loadProducts() {
  try {
    const response = await fetch(`${BASE_URL}/products/getAll`);
    const products = await response.json();
    console.log(products);

    let trendingList = document.getElementById("trending-products");
    let clothingList = document.getElementById("clothing-products");
    let electronicsList = document.getElementById("electronics-products");

    // Check if elements exist (they only exist on index.html)
    if (!trendingList || !clothingList || !electronicsList) {
      console.log("Product containers not found - probably not on index page");
      return;
    }

    trendingList.innerHTML = "";
    clothingList.innerHTML = "";
    electronicsList.innerHTML = "";

    products.forEach((product) => {
      let productCard = `
  <div class="col-sm-12 col-md-6 col-lg-4 mb-4">
    <div class="card h-100">
      <img src="${product.productImageUrl}" class="card-img-top" alt="${product.name}">
      <div class="card-body d-flex flex-column">
        <h5 class="card-title">${product.name}</h5>
        <p class="card-text">${product.description}</p>
        <p class="price"><strong>₹${product.productPrice}</strong></p>
        <button class="btn btn-primary mt-auto"
          onclick="addToCart(${product.id}, '${product.name}', ${product.productPrice}, '${product.productImageUrl}')">
          <i class="fas fa-cart-plus"></i> Add to Cart
        </button>
      </div>
    </div>
  </div>
`;

      if (product.category === "Clothing") {
        clothingList.innerHTML += productCard;
      } else if (product.category === "Electronics") {
        electronicsList.innerHTML += productCard;
      } else {
        trendingList.innerHTML += productCard;
      }
    });
  } catch (error) {
    console.log("Error fetching products:", error);
  }
}

// Initialize products when DOM is loaded (only on index.html)
document.addEventListener("DOMContentLoaded", function () {
  // Only load products if we're on the index page (check for product containers)
  if (document.getElementById("trending-products")) {
    loadProducts();
  }
});
