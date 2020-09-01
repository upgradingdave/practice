var expect = require('chai').expect;
var assert = require('chai').assert;

const {TreeNode, deleteNode, treeNodeToArray} = require("../src/deleteNode.js");

describe("Delete Node", function (done) {

    it('convert tree to array', function () {
        expect(treeNodeToArray(new TreeNode(0, new TreeNode(1, null, null), new TreeNode(2, null, null))))
            .to.deep.equal([0,1,2]);

        expect(treeNodeToArray(new TreeNode(0,
            new TreeNode(1, new TreeNode(4, null, null), new TreeNode(5, null, null)),
            new TreeNode(2, new TreeNode(6, null, null), new TreeNode(7, null, null)))))
            .to.deep.equal([0,1,2,4,5,6,7]);

        expect(treeNodeToArray(new TreeNode(0,
            new TreeNode(1, new TreeNode(4, null, null), null),
            new TreeNode(2, new TreeNode(6, null, null), new TreeNode(7, null, null)))))
            .to.deep.equal([0,1,2,4,null,6,7]);

    });

    //TODO INCOMPLETE
    it('removes node', function () {
        expect(deleteNode(new TreeNode(5,
            new TreeNode(3, new TreeNode(2, null, null), new TreeNode(4, null, null)),
            new TreeNode(6, new TreeNode(7, null, null), null)),
            3))
            .to.deep.equal(new TreeNode(5,
                new TreeNode(4, new TreeNode(2, null, null), null),
                new TreeNode(6, new TreeNode(7, null, null), null)));
    });

});
