document.getElementById('processForm').addEventListener('submit', async function(event) {
    event.preventDefault();

    // Get the values from the form
    const startRange = document.getElementById('startRange').value;
    const endRange = document.getElementById('endRange').value;

    try {
        // Make a POST request to the backend server to add a process
        const response = await fetch('/addProcess', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ startRange: parseInt(startRange), endRange: parseInt(endRange) })
        });

        const result = await response.json();
        // Handle the response
        if (response.ok) {
            document.getElementById('responseMessage').innerText = `Process created successfully: ${JSON.stringify(result)}`;
        } else {
            document.getElementById('responseMessage').innerText = `Error: ${result.message}`;
        }
    } catch (error) {
        document.getElementById('responseMessage').innerText = `Error: ${error.message}`;
    }
});
