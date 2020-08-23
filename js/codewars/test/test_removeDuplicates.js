var expect = require('chai').expect;
var assert = require('chai').assert;

const {removeDuplicates} = require("../src/removeDuplicates.js");

describe("Remove Duplicates", function(done) {

    it('returns an array of numbers unchanged', function() {
        let orig = [1,2,3];
        removeDuplicates(orig);
        expect(orig).to.deep.equal([1,2,3]);
    });

    it('removes single duplicate', function() {
        let orig = [1,2,2,3];
        removeDuplicates(orig);
        expect(orig).to.deep.equal([1,2,3]);
    });

    it('removes duplicate at beginning', function() {
        let orig = [1,1,2,3];
        removeDuplicates(orig);
        expect(orig).to.deep.equal([1,2,3]);
    });

    it('removes duplicates at end', function() {
        let orig = [1,2,3,3,3,3];
        removeDuplicates(orig);
        expect(orig).to.deep.equal([1,2,3]);
    })

    it('removes many duplicates', function() {
        let orig = [1,1,1,1,2,2,3,3,3,3,3,3];
        removeDuplicates(orig);
        expect(orig).to.deep.equal([1,2,3]);
    })
});
