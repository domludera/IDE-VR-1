const express = require('express');
var fs = require('fs');
const https = require('https');
var path = require("path");
var bodyParser = require('body-parser');
const helmet = require('helmet');

const app = express();
const router = express.Router();

app.use(helmet());
app.use("/", express.static(__dirname));
app.use('/', router);



https.createServer({
    key: fs.readFileSync('certs/key.pem'),
    cert: fs.readFileSync('certs/cert.pem')
}, app)
    .listen(3000, function () {
        console.log('Example app listening on port 3000! Go to https://localhost:3000/')
    });




router.get('/', function(req, res){
    res.sendFile(path.join(__dirname+'/index.html'));
});


