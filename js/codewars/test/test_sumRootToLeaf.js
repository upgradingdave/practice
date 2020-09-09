var expect = require('chai').expect;
var assert = require('chai').assert;

const {sumRootToLeaf, TreeNode} = require("../src/sumRootToLeaf.js");

describe("Sum Root To Leaf", function (done) {

    it('binary to decimal', function () {
        expect(parseInt("100", 2)).to.equal(4);
        expect(parseInt("111", 2)).to.equal(7);
    });

    it('sum root to leafe', function () {
        expect(sumRootToLeaf(new TreeNode(1,
            new TreeNode(0, new TreeNode(0, null, null), new TreeNode(1, null, null)),
            new TreeNode(1, new TreeNode(0, null, null), new TreeNode(1, null, null))))).to.equal(22);
    });

});
