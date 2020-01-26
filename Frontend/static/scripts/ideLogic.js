var sceneEl = document.querySelector('a-scene');
//make frame dynamic, using Angular?
var frame = sceneEl.querySelector('#window1');

var target;
var prevTarget;
var fileData = {};

var save = sceneEl.querySelector('#saveWindow');
var load = sceneEl.querySelector('#loadWindow');


var save = sceneEl.querySelector('#save');
var load = sceneEl.querySelector('#load');


var menuItems = sceneEl.querySelectorAll('.file-directory_item');
var isShowing = false;


frame.addEventListener('mouseenter', function(e){
    prevTarget = target;
    prevTarget.setAttribute("textarea", "disabled: true");
    target = e.currentTarget;
    target.setAttribute("textarea", "disabled: false");
});

frame.addEventListener('mouseleave', function(e){
    target = e.currentTarget;
    target.setAttribute("textarea", "disabled: true");
});


save.addEventListener("mouseenter",function(evt){
    target = evt.currentTarget;

save.addEventListener("mouseenter",function(e){
    target = e.currentTarget;

    target.setAttribute('material', 'color', "blue")
});

save.addEventListener("mouseleave",function(){
    target.setAttribute('material', 'color',"gray");
});

load.addEventListener("mouseenter",function(evt){
    target = evt.currentTarget;
    target.setAttribute('material', 'color', "blue")
});

load.addEventListener("mouseleave",function(){
    target.setAttribute('material', 'color',"gray");
});




hotkeys('q', function(event, handler){
    event.preventDefault();
    console.log('hello');
})



hotkeys('ctrl+s', function(event, handler){
    event.preventDefault();
    fileData['file_name'] = target.id;
    fileData['content'] = target.components.textarea.textarea.value;
    console.log(fileData)
});

hotkeys('ctrl+v', function(event, handler) {
    event.preventDefault();
    var newEl = document.createElement("a-entity");


    var screenPos = [
        {
            pos: "-2 1.5 -1",
            rotation: "0 90 0"
        },
    ];

    console.log(screenPos);
    newEl.setAttribute("position", screenPos[0].pos);
    newEl.setAttribute("rotation", screenPos[0].rotation);

    var screenPos = {
       screenOne: {
            pos: "0 1.5 -1",
            rotation: "0 0 0"
        },
        screenTwo: {
            pos: '0 2 -1',
            rotation: "0 90 0"
        },
        screenThree: {
            pos: '-1 2 -1',
            rotation: "0 -90 0"
        }
    }

    newEl.setAttribute("position", screenPos["screenTwo"].pos);
    newEl.setAttribute("rotation", screenPos["screenTwo"].rotation);


    newEl.setAttribute(
        "textarea",
        "cols: 20; rows: 4; text: this is a multiline textarea; backgroundColor: #f8203B; color: white; disabledBackgroundColor: green; disabled: false;"
    );
    sceneEl.appendChild(newEl);
});



hotkeys('ctrl+m', function(event,handler){
    event.preventDefault();
     var fileDirectory = sceneEl.querySelector('#file-directory');
     isShowing = !isShowing;
    fileDirectory.setAttribute('visible', isShowing);
    
});





/*
Example for custom key input
Can also handle key combination ('Ctrl+n') etc.
//Note: implement 'tab' (hotkeys)

var str;

hotkeys('q', function (event, handler){
        var btn = document.getElementsByName("a-scene").createElement("a-entity");

    }
});

 */