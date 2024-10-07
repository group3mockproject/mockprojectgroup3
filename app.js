'use strict';


const path = require('path');
const express = require('express');

const login = require('./src/routes/login_route');

const app = express();


app.set('views', path.join(__dirname, 'views'));
app.engine('html', require('ejs').renderFile);
app.set('view engine', 'html');

app.use(express.static(`${__dirname}/public`))


app.use('/', login);


app.listen(3000, () => {
    console.log('Server is running on http://localhost:3000');
    });