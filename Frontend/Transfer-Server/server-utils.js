var HTTP = require('http');
var querystring = require('query-string');

module.exports.jsonToDictionary = function(directory_JSON){
    //change into JSON.parse(directory_JSON) afterwards when requests are up
    let directory_Object = eval(directory_JSON);
    var newDictionary;
  
    console.log(directory_Object);
    return(directory_Object);
}

module.exports.dictionaryToJSON = function(){

}


module.exports.intitDirectoryStructure = function(){
    HTTP.get('http://localhost:8888/', function(res){
        const {statusCode} = res;
    
        let error;
        if(statusCode != 200)
            error = new Error('Request Failed \n'+ `StatusCode: ${statusCode}`);
        

        //Parsing the response and getting the string body
        res.setEncoding('utf8');
        let rawData = '';
        res.on('data', (chunk) => {rawData += chunk;});
        res.on('end', ()=>{
            try{
                const parsedData = JSON.parse(rawData);
                console.log(parsedData);
                return parsedData;
            }
            catch(e){
                console.log(e.message);


            }
        });
        
    }).on('error', (e)=>{
        console.log(e.message);
    })

}

module.exports.sendAndSaveFileContents = function(json){
    //get the json content and make a post request using HTTP
    //We will send the dummy value of a file, its a global variable that is hard coded for now
    var post_data = json.fileContent;
    var fileId = json.fileId;

    console.log(post_data);
    console.log(Buffer.byteLength(post_data));

    var post_options = {
        host: 'localhost',
        port: '8888',
        path: '/'+`${fileId}`,
        method: 'POST',
        headers: {
            'Content-Length': Buffer.byteLength(post_data)
        }
    };

    // Set up the request
    var post_req = HTTP.request(post_options, function(res) {
        res.setEncoding('utf8');
        res.on('data', function (chunk) {
            console.log('Response: ' + chunk);
        });
    });

    // post the data
    post_req.write(post_data)
    post_req.end();



}



module.exports.sendCompileRequest = function(json){
    
    var put_data = json.fileContent;
    var file_Language = json.language;
    var fileId = json.fileId;

    console.log(put_data);
    console.log(file_Language);
    console.log(fileId);
    //Make a put HTTP request
    var put_options = {
        host: 'localhost',
        port: '8888',
        path: '/'+`${fileId}`,
        method: 'PUT',
        headers: {
            'Language': file_Language,
        }
    };

    var put_req = HTTP.request(put_options, function(res) {
        res.setEncoding('utf8');
        res.on('data', function (chunk) {
            console.log('Response: ' + chunk);
        });
    });

    // post the data
    put_req.write(put_data)
    put_req.end();

}





  

