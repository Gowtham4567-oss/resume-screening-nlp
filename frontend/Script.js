const API = "http://localhost:8080";

// 🔐 LOGIN
function login() {
    const username = document.getElementById("user").value;
    const password = document.getElementById("pass").value;

    fetch(API + "/login", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            username: username,
            password: password
        })
    })
    .then(res => res.text())
    .then(data => {
        alert(data);
    })
    .catch(err => {
        alert("Error: " + err);
    });
}

// 📄 RESUME UPLOAD
function upload() {
    const fileInput = document.getElementById("file");
    const file = fileInput.files[0];

    if (!file) {
        alert("Please select a file");
        return;
    }

    const formData = new FormData();
    formData.append("file", file);

    fetch(API + "/analyze", {
        method: "POST",
        body: formData
    })
    .then(res => res.text())
    .then(data => {
        document.getElementById("output").innerText = data;
    })
    .catch(err => {
        document.getElementById("output").innerText = "Error: " + err;
    });
}

// 💬 CHATBOT
function sendChat() {
    const message = document.getElementById("chat").value;

    if (!message) {
        alert("Enter a message");
        return;
    }

    fetch(API + "/chat?message=" + encodeURIComponent(message))
    .then(res => res.text())
    .then(data => {
        document.getElementById("chatOut").innerText = data;
    })
    .catch(err => {
        document.getElementById("chatOut").innerText = "Error: " + err;
    });
}

// 📊 ADMIN DASHBOARD
function loadUsers() {
    fetch(API + "/users")
    .then(res => res.json())
    .then(data => {
        document.getElementById("users").innerText =
            JSON.stringify(data, null, 2);
    })
    .catch(err => {
        document.getElementById("users").innerText = "Error: " + err;
    });
}
