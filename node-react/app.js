var HTTPS_PORT = 8888;

var fs = require('fs');
var https = require('https');
var WebSocketServer = require('ws').Server;

// SSL config
// openssl req -x509 -newkey rsa:4096 -keyout key.pem -out cert.pem -days 365 -nodes
var serverConfig = {
    key: fs.readFileSync('key.pem'),
    cert: fs.readFileSync('cert.pem'),
};

var handleRequest = function(request, response) {
	const path = request.url.substr(1);
	console.log("Request : ", path);
	if(!fs.existsSync(path)){
		//mdtmp error
		return;
	}

	if(path.endsWith(".html")) {
		response.writeHead(200, {'Content-Type': 'text/html'});
		response.end(fs.readFileSync(path));
	} else if (path.endsWith(".js")) {
		response.writeHead(200, {'Content-Type': 'application/javascript'});
		response.end(fs.readFileSync(path));
	}
};

console.log("Listening on port : ", HTTPS_PORT);
var httpsServer = https.createServer(serverConfig, handleRequest);
httpsServer.listen(HTTPS_PORT, '0.0.0.0');
