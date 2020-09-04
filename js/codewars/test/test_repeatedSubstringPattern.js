var expect = require('chai').expect;
var assert = require('chai').assert;

const {repeatedSubstringPattern} = require("../src/repeatedSubstringPattern.js");

describe("Repeated Substring Pattern", function (done) {

    it('uses slice correctly', function () {
        let s1 = "abab"
        expect(s1.slice(0,2)).to.equal("ab");
        expect(s1.slice(2,4)).to.equal("ab");
        expect(s1.slice(3,6)).to.equal("b");
    });
    it('finds patterns in strings', function () {
        expect(repeatedSubstringPattern("abab")).to.equal(true);
        expect(repeatedSubstringPattern("aba")).to.equal(false);
        expect(repeatedSubstringPattern("ababc")).to.equal(false);
    });

});
