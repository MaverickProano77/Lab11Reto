const db = require('./database.js')
const androidCtrl = {};

androidCtrl.getEstudiantes = (req,res)=>{
    db.query("Select * FROM estudiantes", (err,result,fields)=>{
        if (err) {
            throw err;
        }
        if (result.length > 0){
            res.send("Acceso exitoso");
        }
        else{
            res.send("Tabla estudiante vacia");
        }
    });
};

androidCtrl.query = (req,res)=>{
    db.query(req.params.sql, (err,result,fields)=>{
        if (err) {
            res.send(err);
            return;
        }
        let respuesta = `Cantidad de filas: ${result.length}\r\n`;
        respuesta += `Cantidad de columnas: ${fields.length}\r\n\n`;
        if (result.length > 0){
            fields.forEach(columna => {
                respuesta += `\t\t\t${columna.name}\t\t\t|`
            });
            respuesta += `\r\n`
            result.forEach(fila => {
                for (let index = 0; index < fields.length; index++) {
                    respuesta += `${fila[fields[index].name]} \t\t\t| `
                }
                respuesta += `\r\n`
            });
            res.send(respuesta);
        }
        else{
            res.send("Tabla vacía");
        }
    });
};

module.exports = androidCtrl;