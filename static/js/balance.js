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
        document.getElementById("balance").innerHTML = "User: " + username + " with balance: £" + req.responseText;
    }
}