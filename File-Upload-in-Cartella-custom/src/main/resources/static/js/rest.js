document.getElementById("post-image").addEventListener("click", postVeicolo);
document.getElementById("get-veicoli").addEventListener("click", getVeicoli);


// lista veicoli
function getVeicoli() {
	const table = document.getElementById("lista-veicoli");
	table.innerHTML = "";

	let thead = document.createElement("tr");
	thead.innerHTML = "<thead><tr><th scope='col'>id</th><th scope='col'>name</th><th scope='col'>file name</th><th scope='col'>url (click)</th><th scope='col'>delete (click)</th></tr></thead>";
	table.appendChild(thead);

	let tbody = document.createElement("tbody");
	table.appendChild(tbody);

	fetch("/api/veicolo/all")
		.then(response => {
			console.log(response.status);
			return response.json()
		}).then(listaVeicoli => {

			// ciclo lista veicoli
			for (const veicolo of listaVeicoli) {
				let tr = document.createElement("tr");

				// ciclo proprietà veicolo
				for (const key in veicolo) {
					if (Object.hasOwnProperty.call(veicolo, key)) {
						const element = veicolo[key];

						let td = document.createElement("td");
						td.innerHTML = element;

						if (key == "url") {
							td.addEventListener("click", showImage);
							td.setAttribute("id", veicolo["id"]);
							// aggiungo dello stile
							td.style.color = "blue";
							td.style.textDecoration = "underline";
							td.style.cursor = "pointer";

						}
						tr.appendChild(td);
					}
				}

				// link cancella veicolo
				let delTd = document.createElement('td');
				delTd.innerHTML = "delete " + veicolo.id;
				delTd.addEventListener("click", deleteVeicolo);
				delTd.setAttribute("id", veicolo["id"]);
				// aggiungo dello stile
				delTd.style.color = "blue";
				delTd.style.textDecoration = "underline";
				delTd.style.cursor = "pointer";
				tr.appendChild(delTd)

				// aggiungo tr alla tabella
				tbody.appendChild(tr);
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
		.then(veicolo => {
			console.log("nuovo veicolo id: "+ veicolo.id);
			document.getElementById("name").value = "";
			
			// svuoto elementi input veicolo
			let blob = document.getElementById("image");
			blob.value = "";
		});

}

// visualizza un immagine veicolo
function showImage() {

	const div = document.getElementById("div-immagine");
	div.innerHTML = "";

	// prendo url immagine dal campo url nella tabella
	const UrlImmagine = this.childNodes[0].textContent;

	let img = document.createElement("img");

	let src = UrlImmagine;
	img.setAttribute('src', src);

	img.setAttribute('width', '500px');
	// img.setAttribute('height', '200px');

	// visualizzo l'immagine
	div.appendChild(img);


}

// cancella un veicolo
function deleteVeicolo() {
	console.log("id veicolo da cancellare: " + this.id);

	const URL = "/api/veicolo/" + this.id

	fetch(URL, {
		method: 'DELETE'
	}).then(() => {
		// ricarico lista veicoli
		getVeicoli();
		
		// rimuovo immagini
		const div = document.getElementById("div-immagine");
		div.innerHTML = "";

	})

}