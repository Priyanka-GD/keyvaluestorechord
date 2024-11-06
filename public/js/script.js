document.getElementById("clickButton").addEventListener("click", function() {
    fetch("http://localhost:8081/api/users")
        .then(response => response.text())
        .then(data => {
            document.getElementById("message").textContent = data;
        })
        .catch(error => {
            console.error("Error:", error);
            document.getElementById("message").textContent = "Error fetching data!";
        });
});
