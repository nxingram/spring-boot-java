document.getElementById("post-image").addEventListener("click", postVeicolo);
document.getElementById("get-veicoli").addEventListener("click", getVeicoli);


// get all veicoli
function getVeicoli() {
    const table = document.getElementById("lista-veicoli");
    table.innerHTML = "";

    let thead = document.createElement("tr");
    thead.innerHTML = "<thead><tr><th scope='col'>id</th><th scope='col'>name</th><th scope='col'>file_name</th><th scope='col'>type</th><th scope='col'>url (click)</th><th scope='col'>size</th></tr></thead>";
    table.appendChild(thead);

    let tbody = document.createElement("tbody");
    table.appendChild(tbody);

    fetch("/api/veicolo/all")
        .then(response => {
            console.log(response.status);
            return response.json()
        }).then(listaVeicoli => {
            for (const veicolo of listaVeicoli) {
                let tr = document.createElement("tr");
                tbody.appendChild(tr);
                for (const key in veicolo) {
                    if (Object.hasOwnProperty.call(veicolo, key)) {
                        const element = veicolo[key];
                        let td = document.createElement("td");
                        td.innerHTML = element;
                        if (key == "url") {
                            td.addEventListener("click", getVeicoloById);
                            td.setAttribute("id", veicolo["id"]);
                            td.style.color = "blue";
                            td.style.textDecoration = "underline";
                            td.style.cursor = "pointer";

                        }
                        tr.appendChild(td);
                    }
                }

                table.appendChild(tr);
            }
        });
}


// aggiungi veicolo
function postVeicolo() {
    const name = document.getElementById("name").value;

    // dati immagine
    const image = document.getElementById("image").files[0];

    // oggetto per invio multipart file e altri parametri	
    const formData = new FormData();

    formData.append("name", name);
    formData.append("image", image);


    const URL = "api/veicolo/upload";

    fetch(URL, {
        method: 'POST',
        body: formData
    })
        .then(response => {
            console.log(response.status);
            return response.json()
        })
        .then(x => {
            console.log(x.messaggio)
        });

}

// get un veicolo
function getVeicoloById() {
    console.log(this.getAttribute("id"));
    const div = document.getElementById("div-immagine");
    div.innerHTML = "";

    const URL = this.childNodes[0].textContent;


    fetch(URL)
            .then(response => {
                console.log(response.status);
                return response.json();
            }).then(veicolo => {
                console.log(veicolo);
                
                let img = document.createElement("img");

                let src = "data:" + veicolo.type + ";base64," + veicolo.data;
                console.log(src);
                
                img.setAttribute('src', src);

                img.setAttribute('width', '500px');
                // img.setAttribute('height', '200px');
                div.appendChild(img);
            });

}