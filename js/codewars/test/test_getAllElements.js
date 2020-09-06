var expect = require('chai').expect;
var assert = require('chai').assert;

const {getAllElements, quicksort, TreeNode} = require("../src/getAllElements.js");

describe("Get All Elements", function (done) {

    it('sort array', function () {
        let t1 = [3, 2, 2, 1];
        quicksort(t1, 0, t1.length - 1);
        expect(t1).to.deep.equal([1, 2, 2, 3]);
        let t2 = [0, 1, 1, 2, 3, 4];
        quicksort(t2, 0, t2.length - 1);
        expect(t2).to.deep.equal([0, 1, 1, 2, 3, 4]);
    });

    // it('traverses a tree', function () {
    //     let t1 = new TreeNode(2,)
    //     quicksort(t1, 0, t1.length - 1);
    //     expect(t1).to.deep.equal([1, 2, 2, 3]);

    it('satisfies examples', function () {
        expect(getAllElements(
            new TreeNode(2, new TreeNode(1, null, null), new TreeNode(4, null, null)),
            new TreeNode(1, new TreeNode(0, null, null), new TreeNode(3, null, null))
        ))
            .to.deep.equal([0, 1, 1, 2, 3, 4]);
    });

});
