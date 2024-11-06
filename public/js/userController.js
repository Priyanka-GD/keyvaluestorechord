document.getElementById('userForm').addEventListener('submit', async function(event) {
    event.preventDefault();

    // Get the values from the form
    const name = document.getElementById('name').value;
    const address = document.getElementById('address').value;
    const email = document.getElementById('email').value;

    try {
        // Make a POST request to the backend server to add a user
        const response = await fetch('/addUser', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ name, address, email })
        });

        const result = await response.json();
        // Handle the response
        if (response.ok) {
            document.getElementById('responseMessage').innerText = `User assigned successfully: ${JSON.stringify(result)}`;
        } else {
            document.getElementById('responseMessage').innerText = `Error: ${result.message}`;
        }
    } catch (error) {
        document.getElementById('responseMessage').innerText = `Error: ${error.message}`;
    }
});
