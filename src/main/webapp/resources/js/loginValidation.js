function checkExistLogin() {
    var xmlhttp = new XMLHttpRequest();
    var username = document.forms["registform"]["username"].value;
    var url = "checkname?username=" + username;
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {
            if ("false" === xmlhttp.responseText) {
                document.getElementById("sbm").disabled = true;
                document.getElementById("error").innerHTML = document.forms["errorForm"]["errorLogin"].value;

            } else {
                document.getElementById("sbm").disabled = false;
                document.getElementById("error").innerHTML = "";
            }
        }
    };
    try {
        xmlhttp.open("GET", url, true);
        xmlhttp.send();
    } catch (e) {
        alert("unable to connect to server");
    }
}