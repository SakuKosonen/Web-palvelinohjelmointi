var url = localhost:8080/tasks

var http = new XMLHttpRequest()



http.onreadystatechange = function() {

    if (this.readyState != 4 || this.status != 200) {
        return
        
    }
    console.log("kalakala")
    response = JSON.parse(this.responseText)
    var lista = response
    console.log(response)
    
    for (var i = 0; i < lista.length; i++) {
        var elementti = document.createElement("p")
        teksti.appendChild(document.createTextNode(tasks[i].name))
    
        document.getElementById("osio").appendChild(elementti)
        console.log("kalakala")
    }    
}


function listaa() {
    console.log("kalaaaaa")
    http.open("GET", url)
    http.send()    
}


