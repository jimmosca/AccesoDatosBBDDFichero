const Express = require('express');
const BodyParser = require('body-parser');
const Http = require('http');
var mysql = require('mysql');

var con = mysql.createConnection({
    host: "localhost",
    user: "root",
    password: "",
    database: "adat_ud4_a03_animalicos"
});

const app = Express();
app.use(BodyParser.json());
app.use(BodyParser.urlencoded({ extended: false }));



app.get('/', (req, res) =>


    con.query("SELECT * FROM mascotas", function (err, result, fields) {
        if (err) throw err;
        res.status(200).json(result)
    })

);

app.post('/:id', (req, res) =>


    con.query("INSERT INTO mascotas VALUES(" + req.params.id + ",'" + req.body.nombre + "','" + req.body.especie + "')", function (err, result, fields) {
        if (err) throw err;
        res.status(200).json({ 'resultado': 'correcto' })
    })
);

app.post('/', (req, res) => {

    con.query("DELETE FROM mascotas", function (err) { if (err) throw err });

    req.body.mascotas.forEach(mascota => {
        con.query("INSERT INTO mascotas VALUES(" + mascota.id + ",'" + mascota.nombre + "','" + mascota.especie + "')", function (err) {
            if (err) throw err;
        });
        
    });
    res.status(200).json({ 'resultado': 'correcto' })
});

app.put('/', (req, res) =>

    con.query("UPDATE mascotas SET Nombre = '" + req.body.nombre + "', Especie = '" + req.body.especie + "' WHERE ID =" + req.body.id, function (err, result, fields) {
        if (err) throw err;
        res.status(200).json({ 'resultado': 'correcto' })
    })

);

app.delete('/:id', (req, res) =>

    con.query("DELETE FROM mascotas WHERE ID =" + req.params.id, function (err, result, fields) {
        if (err) throw err;
        res.status(200).json({ 'resultado': 'correcto' })
    })

);



const port = 9000;
app.set('port', port);
const server = Http.createServer(app);
server.listen(port);
module.exports = app;