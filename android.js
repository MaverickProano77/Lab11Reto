const {Router} = require('express');
const {getEstudiantes,query} = require('../controllers/androidController'); //funciones constructor
const router = Router();
//Rutas
router.route('/').get(getEstudiantes);
router.route('/:sql').get(query);
//exportamos
module.exports = router;