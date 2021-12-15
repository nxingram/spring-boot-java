# ATTENZIONE!! 
## Eseguire dump (backup) del database prima di cancellare i record!
#### verificare la tabella collegata all'entità
##### vedi Studente Entity
@Table(name = "studente")

Attenti ai valori "null" nella tabella, danno errore nel parsing del json.