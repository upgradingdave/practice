var expect = require('chai').expect;
var assert = require('chai').assert;

const {addTwoNumbers,ListNode} = require("../src/addTwoNumbers.js");

describe("Add Two Numbers", function(done) {

  it('adds 2 numbers', function() {
    expect(addTwoNumbers(new ListNode(2, new ListNode (4, new ListNode (3, null))),
        new ListNode(5, new ListNode(6, new ListNode(4, null)))))
        .to.deep.equal(new ListNode(7, new ListNode(0, new ListNode(8, null))));

    expect(addTwoNumbers(new ListNode(5, null), new ListNode(5, null)))
      .to.deep.equal(new ListNode(0, new ListNode(1, null)));

    expect(addTwoNumbers(new ListNode(9, null), new ListNode(9, null)))
      .to.deep.equal(new ListNode(8, new ListNode(1, null)));
  });

    it('adds 9 and 9', function() {
        expect(addTwoNumbers(new ListNode(9, null), new ListNode(9, null)))
            .to.deep.equal(new ListNode(8, new ListNode(1, null)));
    });

    it('adds 18 and 0', function() {
        expect(addTwoNumbers(new ListNode(1, new ListNode(8,null)), new ListNode(0, null)))
            .to.deep.equal(new ListNode(1, new ListNode(8, null)));
    });

});
