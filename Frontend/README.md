# ide-vr

## How to run

* Run `npm install` to install the necessary dependencies.

#### For basic server

* (Recommended) Running npm install -g live-server && live-server in a terminal in the same directory as your HTML file.
> `live-server` is not a dependency of this app, it used for *dev application only*, so no need to save it to `package.json`
* Downloading the Mongoose application and opening it from the same directory as your HTML file.
* Running python -m SimpleHTTPServer (or python -m http.server for Python 3) in a terminal in the same directory as your HTML file.


## HTTPS

It is highly advised to create SSL certificates (`.crt` and `.key`) to have the server run over HTTPS.
> (If you run the app from Google Chrome on your mobile and you feel like the gyroscope isn't working, thats probably why.) 

Once the certificates are created, copy them into `certs/` and make sure to change the `<filename>` variable in `https.js` to the corresponding filename.

Then run the server by typing `npm start`
> Note: `npm start` is preconfigured to run live-server, if you chose to go with another otion make sure you modify/add to the script accordingly.


## Dev notes

* When adding new dependencies make sure to use the `--save` flag, so they are registered in `package.json`
* The `scripts/` folder is intended for random scripts one might need for dev, if you feel like contributing a script that might benefit the team, please store it in that folder
* *feel free to add your notes*


## *To Do*

### Backend Features:
1. NodeJS or Python or Java writing to files using IO (Straightfoward and no backtracking to handle errors)
2. Be able to take backtrack input and properly modify the resulting IO file
3. Send file to a handle function that will handle the compiler (or send to another server)
4. Return the detailed response that should be able to be displayed int he front end (compilation errors)
5. Handler in the frontend WebVR that signifies a file being compiled (3D loading screen)
6. Check out event listener for events coming FROM the a-frame tag and how angular can handle those. 
7. Hiding and showing entities (a-frame or angular?)

### IDE-VR Features:
1. Ctrl+N new window
2. Save feature (Ctrl+S)
3. Close button
4. Resizable window
5. Change position of window (Ctrl+look around)
6. Ctrl++ and Ctrl+- for font size
7. File explorer
8. Raycasting to choose frame
