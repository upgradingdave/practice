var expect = require('chai').expect;
var assert = require('chai').assert;

const {partitionLabels} = require("../src/partitionLabels.js");

describe("Partition Labels", function (done) {

    it('satisfies examples', function () {
        expect(partitionLabels("ababcbacadefegdehijhklij")).to.deep.equal([9,7,8]);
        expect(partitionLabels("caedbdedda")).to.deep.equal([1,9]);

    });
    it('recurse to find partition', function () {
        expect(partitionLabels("aebbedaddc")).to.deep.equal([9,1]);
    });

});
