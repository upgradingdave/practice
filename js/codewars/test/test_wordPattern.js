var expect = require('chai').expect;
var assert = require('chai').assert;

const {wordPattern} = require("../src/wordPattern.js");

describe("Word Pattern", function (done) {

    it('understands undefined vs null', function() {
        let t1 = {'a': 1};
        expect(t1['b'] === undefined).to.equal(true);
        expect(t1['b'] === null).to.equal(false);
        expect(undefined === undefined).to.equal(true);
    });

    it('satisfies examples', function () {
        expect(wordPattern("abba", "dog cat cat dog")).to.equal(true);
        expect(wordPattern("abba", "dog cat cat fish")).to.equal(false);
        expect(wordPattern("aaaa", "dog cat cat dog")).to.equal(false);
        expect(wordPattern("abba", "dog dog dog dog")).to.equal(false);
    });

});
