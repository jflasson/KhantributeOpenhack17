const xliff12ToJs = require('xliff/xliff12ToJs');
const fs = require('fs');
const input = fs.readFileSync('learn.math.arithmetic-home.exercises-sv-SE.xliff', "utf8");

xliff12ToJs(input, (err, res) => {
	console.log("JSON: " + input);
	if(err) {
		return console.log(err);
	}
	fs.writeFile("test.json", JSON.stringify(res));
	console.log("JSON: " + res);
	map(res, (err, result) => {

	});
});