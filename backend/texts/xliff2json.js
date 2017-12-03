const xliff12ToJs = require('xliff/xliff12ToJs');
const fs = require('fs');
const input = fs.readFileSync('learn.math.arithmetic-home.exercises-sv-SE.xliff', "utf8");

xliff12ToJs(input, (err, res) => {
	console.log("JSON: " + input);
	if(err) {
		return console.log(err);
	}
	// console.log("JSON: " + res);
	let strings = res['resources']['/2_high_priority_content/learn.math.arithmetic-home.exercises.pot'];
	console.log("new JSON: " + strings);
	let newres = Object.keys(strings).map(function(key, index) {
		return {
			'key': key,
			source: strings[key].source,
			target: strings[key].target
		}
	});
	// console.log("new JSON: " + JSON.stringify(newres));
	fs.writeFile("test.json", JSON.stringify(newres));
});