'use strict';

/**
 * Render login page
 * @param {object} req - request object 
 * @param {object} res - response object
 */
const renderLogin = (req, res) => {
    res.render('./pages/login.html');
}


module.exports = {renderLogin}