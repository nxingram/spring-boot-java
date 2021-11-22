# Profili Spring Boot applications
### Profilo attivo: 
per esempio `win o mac` 
```
spring.profiles.active=
```

posso aggiungere delle proprietà personalizzate,
per esempio il nome dell'applicazione, che uso come variabile in `welcome.message`
```
app.name=Profili Spring Boot
```

messaggi che posso usare nel controller,
sotto trovo i valori di default, negli altri file metto i valori del profilo corrispondente
```
welcome.message=Welcome to ${app.name}
basic.message=Basic
```

stessa cosa è fattibile per le impostazioni database,
aggiungere ai vari profili le rispettive proprietà valorizzate
```
spring.datasource.url=jdbc:mysql://localhost:3306/projectwork
spring.datasource.username=app_generation
spring.datasource.password=generation_2021!?
```
vedere esempi nella cartella => [resources](src/main/resources/)
