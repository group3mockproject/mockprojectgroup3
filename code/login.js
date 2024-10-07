document.getElementById('loginForm').addEventListener('submit', function(event) {
    event.preventDefault();
    
    let email = document.getElementById('email').value;
    let password = document.getElementById('password').value;
    let errorMessages = [];

    if (!email) {
        errorMessages.push('Email is required.');
    }

    if (!password) {
        errorMessages.push('Password is required.');
    }

    let errorMessagesDiv = document.getElementById('errorMessages');
    errorMessagesDiv.innerHTML = '';

    if (errorMessages.length > 0) {
        errorMessages.forEach(function(message) {
            let p = document.createElement('p');
            p.textContent = message;
            errorMessagesDiv.appendChild(p);
        });
    } else {
        alert('Form submitted successfully!');
    }
});