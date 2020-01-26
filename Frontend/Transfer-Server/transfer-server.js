const express = require('express');
var serverUtils = require('./server-utils');
const app = express();
var path = require("path");
const router = express.Router();
var fs = require("fs");
var bodyParser = require('body-parser');
const helmet = require('helmet');
const https = require('https');


global.newDirectory = {
    directoryContent: []

}


app.use(bodyParser.json());

//app.use(express.static(__dirname+'/html'));
//app.use(express.static(__dirname+'scripts'));

app.use(helmet());

app.use("/", express.static(__dirname));
app.use("/static", express.static('./static/'));
app.use("/Transfer-Server", express.static('./Transfer-Server/'));

app.use('/', router);



global.newFile = {
    fileId: "",
    language: "",
    fileContent: ""
}




router.get('/', function(req, res){
    serverUtils.intitDirectoryStructure();
    if(typeof newDirectory.directoryContent !== null)
        res.sendFile(path.join(__dirname+'/../html/index.html'))
   
    console.log("Checking directory global variable");
    console.log(newDirectory);
    console.log("Accessing the homepage");
})

router.get('/saveFile', function(req, res){
    serverUtils.sendAndSaveFileContents(newFile);
    res.send('Request Received');

});

router.get('/compile', function(req, res){
   serverUtils.sendCompileRequest(newFile);
   res.send('PUT Request Received');
});

router.get('/newFile', function(req, res){
    //serverUtils.jsonToDictionary(internalProjectDirectory);
    res.sendFile(path.join(__dirname+'/../html/newFile_dummy.html'));
    res.send(serverUtils.jsonToDictionary(internalProjectDirectory));
});

https.createServer({
    key: fs.readFileSync(__dirname+'/../../certs/key.pem'),
    cert: fs.readFileSync(__dirname+'/../../certs/cert.pem')
}, app).listen(3000, function(){
    console.log("Listening to Port 3000");
});