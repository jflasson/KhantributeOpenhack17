var express = require('express');
var crowdin = require('crowdin');
var router = express.Router();

crowdin = new crowdin({
	apiKey: 'dc6ab0c635bf043c03db12aa5f02abc7',
	endpointUrl: 'https://api.crowdin.net/api/project/tomastest'
});

router.get('/string', function(req, res, next) {
	const dummyData = {
		fileName: 'other.po',
		original: 'This is an example sentence.',
		sv_SE: 'Det här är en exempel mening.'
	};
	res.setHeader("Content-Type",'application/json');
	res.setHeader('Access-Control-Allow-Origin', '*');
	res.send(dummyData);
});

router.post('/string', function(req, res, next) {
	//TODO: send all + score (-1,0,1)
	req.acceptsEncodings('application/json');
});

router.get('/score', function(req, res, next) {
	const dummyData = {
		score: 200,
		pending: 5,
		place: 3
	};
	res.setHeader("Content-Type",'application/json');
	res.setHeader('Access-Control-Allow-Origin', '*');
	res.send(dummyData);
});

router.get('/topList', function(req, res, next) {
	const dummyData = [
		{
			name: 'First person',
			score: 300
		},
		{
			name: 'Second person',
			score: 250
		},
		{
			name: 'Another person',
			score: 200
		},
		{
			name: 'Another person',
			score: 150
		},
		{
			name: 'Another person',
			score: 100
		},
		{
			name: 'Another person',
			score: 90
		},
		{
			name: 'Another person',
			score: 80
		},
		{
			name: 'Another person',
			score: 70
		},
		{
			name: 'Another person',
			score: 60
		},
		{
			name: 'Another person',
			score: 50
		}
	];
	res.setHeader("Content-Type",'application/json');
	res.setHeader('Access-Control-Allow-Origin', '*');
	res.send(dummyData);
});

function getRandomString() {
	//TODO
}

module.exports = router;
