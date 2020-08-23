var expect = require('chai').expect;
var assert = require('chai').assert;

const {<{fn-name}>} = require("../src/<{file-name}>.js");

describe("<{challenge-name}>", function(done) {

  it('returns an array of numbers', function() {
    expect(<{fn-name}>([1,2,3])).to.equal([1,2,3]);
  });

});
