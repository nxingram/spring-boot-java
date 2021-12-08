function getOne() {
    console.log("getOne-fetch");
    const URL = "/api/studenti"; // mapping-action-rotta
    let statusCode = "";

    // id da inviare al controller
    let getId = document.getElementById("getnumber").value
    if(getId < 1) return; // se non valido, esco
    let newUrl = URL + "/" + getId;

    fetch(newUrl)
        .then(response => {
            statusCode = response.status; // salvo lo status della response
            return response.json(); // restituisco il json convertito
        })
        .then(studente => {
                // visualizzo lo status code
                setStatusCode(statusCode, "status-getone");
                
                //  stampo/visualizzo lo studente
                printStudent(studente, "getone");

        });
}

function addOne() {
    console.log("addOne-fetch");
    const URL = "/api/studenti"; // mapping-action-rotta
    let statusCode = "";

    // creo un nuovo studente json da aggiungere
    // recupero i dati dai tag input
    let nuovoStudente = { 
        "nome": document.getElementById("post-nome").value,
        "cognome": document.getElementById("post-cognome").value,
        "email": document.getElementById("post-email").value
    }


    fetch(URL, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json' // dico al server che gli mando un json
        },
        body: JSON.stringify(nuovoStudente) // converto il nuovo studente in stringa per inviarlo al controller
    })
        .then(response => {
            statusCode = response.status; // salvo lo status della response
            return response.json(); // restituisco il json convertito
        })
        .then(studente => {
            // visualizzo lo status code
            setStatusCode(statusCode, "status-addone");

            //  stampo/visualizzo lo studente
            printStudent(studente, "addone");

        });
}

function delOne() {
    console.log("delOne-fetch");
    const URL = "/api/studenti"; // mapping-action-rotta
    let statusCode = "";

    // id da inviare al controller
    let delId = document.getElementById("delnumber").value    
    if(delId < 1) return; // se non valido, esco
    let newUrl = URL + "/" + delId;

    fetch(newUrl, {
        method: 'DELETE'
    })
        .then(response => {
            statusCode = response.status; // salvo lo status della response
            // visualizzo lo status code
            setStatusCode(statusCode, "status-delone");
        });
}

function putOne() {
    console.log("addOne-fetch");
    const URL = "/api/studenti"; // mapping-action-rotta
    let statusCode = "";
    
    // id da inviare al controller
    let putId = document.getElementById("put-id").value   
    if(putId < 1) return; // se non valido, esco

    // recupero i dati da modificare dello studente
    let editStudente = {
        "id": putId,
        "nome": document.getElementById("put-nome").value,
        "cognome": document.getElementById("put-cognome").value,
        "email": document.getElementById("put-email").value
    }

    fetch(URL, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json' // dico al server che gli mando un json
        },
        body: JSON.stringify(editStudente) // converto il nuovo studente in stringa per inviarlo al controller
    })
        .then(response => {
            statusCode = response.status; // salvo lo status della response
            return response.json(); // restituisco il json convertito
        })
        .then(studente => {
            // visualizzo lo status code
            setStatusCode(statusCode, "status-putone");

            //  stampo/visualizzo lo studente
            printStudent(studente, "putone");

        });
}

function getAll() {
    console.log("getAll-fetch");
    const URL = "/api/studenti"; // mapping-action-rotta
    let statusCode = "";

    fetch(URL)
        .then(response => {
            statusCode = response.status; // salvo lo status della response
            return response.json(); // restituisco il json convertito
        })
        .then(listaStudenti => {
                // visualizzo lo status code
                setStatusCode(statusCode, "status-getall");
                
                //  stampo/visualizzo gli studenti
                printTable(listaStudenti, "getall");

        });
}

function printStudent(studente, elementId) {
    // recupero l'ul dove inserire i dati
    let ul = document.getElementById(elementId);
    ul.innerHTML = "";

    // ciclo le proprietà dell'oggetto studente
    // e le inserisco nell'ul
    for (const key in studente) {
        if (Object.hasOwnProperty.call(studente, key)) {
            const element = studente[key];
            let li = document.createElement("li");
            li.innerHTML = key + ": " + element;
            ul.appendChild(li);
        }
    }
}

function setStatusCode(statusCode, elementId) {
    // recupero l'elemento
    let span = document.getElementById(elementId);
    // aggiungo lo status code
    span.innerHTML = "Status Code: " + statusCode;

    // aggiungo class con colore testo in base allo status code
    if (statusCode == 400 || statusCode == 500)
        span.className = "error";
    else
        span.className = "success";
}

function printTable(listaStudenti, elementId){
        // recupero l'elemento dove inserire i dati
        let tbody = document.getElementById(elementId);
        tbody.innerHTML = "";

        // ciclo la lista
        for (const studente of listaStudenti) {
            // nuova riga
            let tr = document.createElement('tr');
            
            // ciclo le proprietà
            for (const key in studente) {
                if (Object.hasOwnProperty.call(studente, key)) {
                    const element = studente[key];

                    // nuova colonna
                    let td = document.createElement("td");
                    td.innerHTML = element;
                    // aggiungo colonna alla riga
                    tr.appendChild(td);
                }
            } // fine for-in

            // aggiungo la riga al tbody
            tbody.appendChild(tr);

        } // fine for-of


}