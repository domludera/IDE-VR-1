var isShowing = false;
var sceneEl = document.querySelector('a-scene');
var fileArea = document.querySelector('#file-area');
var currentDirectory = [ "main.java" ,"test.java" ,"main1.java" ]
sessionStorage("description", currentDirectory);

 hotkeys('ctrl+m', function(event,handler){
     event.preventDefault();
      var fileDirectory = sceneEl.querySelector('#menubar');
      isShowing = !isShowing;
     fileDirectory.setAttribute('visible', isShowing);
    
    getData(currentDirectory);
 });

 const getData = function(myJson){
    console.log(currentDirectory);
    for(let i=0; i<myJson.length;i++)
    var el = document.createElement("a-plane");
    var child = document.createElement("a-text");
    el.setAttribute('position','-1.6 0.3 0.2');
    el.setAttribute('width',"0.5");
    el.setAttribute('height',"0.5");
    el.setAttribute('color',"blue");
    child.setAttribute('value', myJson[i].name);
    el.appendChild(child);
    fileArea.appendChild(el);
    console.log(myJson);
 }