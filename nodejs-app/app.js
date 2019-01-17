var mysql = require('mysql');

var con = mysql.createConnection({
	host: "172.17.0.2",
	user: "root",
	password: "my-secret-pw" //TODO change to env var
});

con.connect(function(err) {
	  if (err) throw err;
	  console.log("Connected!");
});
