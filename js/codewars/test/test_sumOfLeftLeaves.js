var expect = require('chai').expect;
var assert = require('chai').assert;

const {TreeNode, sumOfLeftLeaves} = require("../src/sumOfLeftLeaves.js");

describe("Sum of Left Leaves", function (done) {

    it('returns an array of numbers', function () {
        let tree1 = new TreeNode(3, new TreeNode(9), new TreeNode(20, new TreeNode(15), new TreeNode(7)))
        expect(sumOfLeftLeaves(tree1)).to.equal(24);
    });

});
