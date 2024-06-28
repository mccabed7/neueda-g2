function login()
{
    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;

    const req = new XMLHttpRequest();
    const attemptURL = "http://localhost:8080/api/verify";
    reqJson = JSON.stringify({'username': username, 'password': password});
    req.open("POST", attemptURL, false)
    req.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    req.send(reqJson);
    // Admin credentials leads to search page, else normal page
    if (req.status == 200 && username == "admin")
    {
        window.location = "./pages/search.html";
    } else if (req.status  == 200) {
        window.location = "./pages/balance.html?username=" + username;
    }
    else
    {
        document.getElementById("fail").innerHTML = "Login failed. Try again."
    }
}