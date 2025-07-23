document.addEventListener("DOMContentLoaded", () => {
  const toggleButton = document.getElementById("theme-toggle");
  const toggleIcon = document.getElementById("theme-toggle-icon");

  function applyTheme(theme) {
    document.documentElement.classList.toggle("dark", theme === "dark");
    localStorage.setItem("theme", theme);
    if (toggleIcon) {
      toggleIcon.classList.toggle("fa-moon", theme === "light");
      toggleIcon.classList.toggle("fa-sun", theme === "dark");
    }
  }

  const currentTheme = localStorage.getItem("theme") || "light";
  applyTheme(currentTheme);

  if (toggleButton) {
    toggleButton.addEventListener("click", () => {
      const newTheme = document.documentElement.classList.contains("dark")
        ? "light"
        : "dark";
      applyTheme(newTheme);
    });
  }
});
