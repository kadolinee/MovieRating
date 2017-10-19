function checkPassword(input) {
    if (input.value !== document.getElementById("pwd").value) {
        document.getElementById("sbm").disabled = true;
        document.getElementById("error").innerHTML = document.forms["errorForm"]["errorPwd"].value;
    } else {
        input.setCustomValidity("");
        document.getElementById("sbm").disabled = false;
        document.getElementById("error").innerHTML = "";
    }
}