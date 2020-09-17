var expect = require('chai').expect;
var assert = require('chai').assert;

const {lengthOfLastWord} = require("../src/lengthOfLastWord.js");

describe("Length of Last Word", function(done) {

    it('satisfies example 1', function() {
        expect(lengthOfLastWord("Hello World")).to.deep.equal(5);
    });

    it('satisfies example 2', function() {
        expect(lengthOfLastWord("a ")).to.deep.equal(1);
    });

});
