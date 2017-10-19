function checkExistMail() {
    var xmlhttp = new XMLHttpRequest();
    var mail = document.forms["registform"]["mail"].value;
    var url = "checkmail?mail=" + mail;
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {
            if ("false" == xmlhttp.responseText) {
                document.getElementById("sbm").disabled = true;
                document.getElementById("error").innerHTML = document.forms["errorForm"]["errorMail"].value;
            }
            else {
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