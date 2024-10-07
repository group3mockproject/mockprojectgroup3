'use strict';

const router = require('express').Router();


const {renderLogin} = require('../controllers/login_controller');

router.get('/', renderLogin);

module.exports = router;