var express = require('express');
var crowdin = require('crowdin');
var router = express.Router();

crowdin = new crowdin({
	apiKey: 'dc6ab0c635bf043c03db12aa5f02abc7',
	endpointUrl: 'https://api.crowdin.net/api/project/tomastest'
});

router.get('/getString', function(req, res, next) {
	const dummyData = {
		original: 'This is an example sentence.',
		sv_SE: 'Det här är en exempel mening.'
	};
  res.send(dummyData);
});

function getRandomString() {
	//TODO
}

module.exports = router;
