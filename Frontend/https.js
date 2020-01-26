var fs = require("fs");

module.exports = {
    cert: fs.readFileSync("certs/<filename>.crt"),
    key: fs.readFileSync("certs/<filename>.key"),
};
