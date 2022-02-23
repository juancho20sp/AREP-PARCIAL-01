# ECI Clima
This is a small web application that gives the user the opportunity to fetch some data using **sockets** and a 
custom **HTTP Server**.


## Architecture

---
The custom server pro

This is a simple _Client-Server_ architecture using an **APIRest** approach. The **backend** is a Spark application that exposes two endpoints that the **frontend** consumes. The communication is based on the REST protocol using **JSON** as standard for sending and receiving messages.

The REST method used form communication is the **GET** method and the values required for doing the calculations are sent as _query params_ using the **value** key.

### API

---

**Base URL**

```url
https://parcial-01-arep-murillo.herokuapp.com/
```

### ENDPOINTS

---
The project exposes two **endpoints**, the first one is **/clima** and gives the user a friendly interface to call 
the backend service of fetching the weather of a specific zone.

```url
/clima
```

For directly calling the backend service that fetchs the weather data.

```url
/consulta?ciudad={CITY}
```
Where **CITY** is the name of the city which we want to get the weather.

### REQUESTS

---

All the requests are sent as **GET** requests to the backend. 


### RESPONSES

---

If a request is **successfully** sent to the server, it will respond with a **text/html** file if the endpoint 
called is _/clima_and a **application/json** file if the endpoint called is _/consulta_.


### LINKS

---

**Application**

```url
https://parcial-01-arep-murillo.herokuapp.com/
https://parcial-01-arep-murillo.herokuapp.com/clima
https://parcial-01-arep-murillo.herokuapp.com/consulta?ciudad=paris
```


## Run the project locally!

---

### Backend

Download or clone the project, go to the _Main_ directory and run the class **HttpServer**.




## Built With

- [Java](https://www.java.com/es/) - As the main language.
- HTML and CSS - As the main technologies for developing the UI.
- [JavaScript](https://developer.mozilla.org/es/docs/Web/JavaScript) - As the language used in the client for creating experiences and handling user events.

## Author

- **Juan David Murillo** - [Github](https://github.com/juancho20sp) | [Twitter](https://twitter.com/juancho20sp)<br/>
  Student at: [Escuela Colombiana de Ingeniería Julio Garavito](https://www.escuelaing.edu.co/es/) <br/>
  2022 - I

## License

This is an _open source_ project.

### Thanks for checking out!