var expect = require('chai').expect;
var assert = require('chai').assert;

const {ListNode,
       listsEqual,
       listNodesEqual,
       getNth,
       arrToList,
       reorderList} = require("../src/linkedlist.js");

describe("Linked List", function(done) {

  let node1 = new ListNode(1, null);
  let node2 = new ListNode(1, null);
  let node3 = new ListNode(3, null);

  let list1 = new ListNode(1, new ListNode(2, new ListNode(3, null)));
  let list2 = new ListNode(1, new ListNode(2, new ListNode(3, null)));
  let list3 = new ListNode(2, new ListNode(2, new ListNode(3, null)));
  let list4 = new ListNode(1, new ListNode(2, null));

  let arr1 = [1,2,3];
  let arr2 = [1,2,3,4];
  let arr3 = [1,4,2,3];
  let arr4 = [1,2,3,4,5];
  let arr5 = [1,5,2,4,3];

  it('compare ListNodes and Lists', function() {
    assert(listNodesEqual(node1, node2), 'node1 == node2');
    assert(!listNodesEqual(node1, node3), 'node1 != node3');
    assert(listsEqual(list1, list2), 'list1 == list2');
    assert(!listsEqual(list1, list3), 'list1 != list3');
    assert(!listsEqual(list1, list4), 'list1 != list4');
    assert(!listsEqual(list4, list1), 'list1 != list4');
  });

  it('finds nth node in linked list', function() {
    assert(listNodesEqual(getNth(list1, 2),node3), 'getNth(list1, 2) == node3');
    assert(!listNodesEqual(getNth(list1, 2),node1), 'getNth(list1, 2) != node1');
  });
  
  it('converts array to a linked list', function() {
    assert(listsEqual(arrToList(arr1), list1), 'arr1 == l1');
    assert(!listsEqual(arrToList(arr1), list3), 'arr1 != l3');
  });

  it('reorder linked list', function() {
    let tmpList1 = arrToList(arr2);
    reorderList(tmpList1);

    assert(listsEqual(tmpList1, arrToList(arr3)),
	   'reorder tmpList1 equals arr3');

    let tmpList2 = arrToList(arr4);
    reorderList(tmpList2);

    assert(listsEqual(tmpList2, arrToList(arr5)),
	   'reorder tmpList1 equals arr3');
  });

});
