const express = require('express');
var serverUtils = require('./server-utils');
const app = express();
var path = require("path");
const router = express.Router();
var fs = require("fs");
var bodyParser = require('body-parser');
const helmet = require('helmet');
const https = require('https');

app.use(bodyParser.json());


global.newFile = {
    fileId: "",
    language: "",
    fileContent: ""
}

global.newDirectory = {
    directoryContent: []
}

router.get('/', function(req, res){
    serverUtils.intitDirectoryStructure();
    res.sendFile(path.join(__dirname+'/../html/index.html'));
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



app.use(express.static(__dirname+'/html'));
//app.use(express.static(__dirname+'scripts'));
app.use('/', router);
app.use(helmet());
app.use("/", express.static(__dirname));

app.listen(8080, function(){
    console.log("Listening to Port 8080");
});
