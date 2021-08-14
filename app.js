const express = require('express');
const server = express();
const cors = require('cors');
const path = require('path');

//Configuraciones
server.set('port',8080);
server.set('host','localhost');

//Middlewares
server.use(express.json());
server.use(cors()); //antes de las rutas
server.use(express.static(path.join(__dirname, 'build')));
//Rutas
server.use('/estudiantes', require('./routes/estudiantes.js'));
server.use('/profesores', require('./routes/profesores.js'));
//ProfesoresPractico
//server.use('/profesor/practica', require('./routes/profesorPractico.js'));
//ProfesoresTeorico
//server.use('/profesor/teoria', require('./routes/profesorTeoria.js'));

//Rutas
server.use('/android',require('./routes/android.js'));
server.get('/', function (req, res) {
   res.send('<h1> Hola Maverick con Express </h1>')
});
server.get('*', (req, res) => {
    res.status(404).send("<h1>Error 404</h1><h2>Página no encontrada</h2>")
})
server.get('/', function (req, res) {
    res.sendFile(path.join(__dirname, 'build', 'index.html'));
})

module.exports = server;