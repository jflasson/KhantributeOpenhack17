let gettextParser = require('gettext-parser');
let input = require('fs').readFileSync('_other_-sv-SE.po');
let po = gettextParser.po.parse(input);
console.log(po.translations[''][0]); // output translations for the default context