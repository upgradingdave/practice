var expect = require('chai').expect;
var assert = require('chai').assert;

const {<{fn-name}>} = require("../src/<{file-name}>.js");

describe("<{challenge-name}>", function(done) {

    it('satisfies examples', function() {
        expect(<{fn-name}>([1,2,3])).to.deep.equal([1,2,3]);
    });

});
