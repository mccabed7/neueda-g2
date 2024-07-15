function displayUserDetails()
{
    const url = window.location.search;
    params = new URLSearchParams(url);
    username = params.get('username')
    document.getElementById("balance").innerHTML = "User: " + username;

    const req = new XMLHttpRequest();
    const attemptURL = "http://localhost:8080/api/users?username=" + username;
    req.open("GET", attemptURL, false)
    req.send();
    if (req.status == 200)
    {
        document.getElementById("Welcome").innerHTML = "Welcome " + username + "!";
        document.getElementById("balance").innerHTML = "Your balance is £" + (parseFloat(req.responseText)).toFixed(2);
    }
}

function redirectSendMoney() 
{
    const url = window.location.search;
    params = new URLSearchParams(url);
    username = params.get('username')
    document.getElementById("balance").innerHTML = "User: " + username;

    // Redirect to sendmoney page
    window.location = "./sendMoney.html?username=" + username;
}
