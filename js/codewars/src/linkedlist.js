//Definition for singly-linked list.

function ListNode(val, next) {
  this.val = (val===undefined ? 0 : val)
  this.next = (next===undefined ? null : next)
}

ListNode.prototype.toString = function listNodeToString() {
  let s = `${this.val}`;
  let curr = this;
  let uhoh = 0;
  while(curr.next && uhoh <= 100) {
    s += ` -> ${curr.next.val}`;
    curr = curr.next;
    uhoh = uhoh+1;
  }
  return s;
}

function listNodesEqual(a,b) {
  if(a == null || b == null) {
    return a == null && b == null;
  }

  return a.val === b.val
}

function listsEqual(a,b) {
  let p1 = a;
  let p2 = b;
  if(!listNodesEqual(p1, p2)) return false;
  while(p1.next != null) {
    p1 = p1.next;
    p2 = p2.next;
    if(!listNodesEqual(p1, p2)) return false;
  }
  return p2.next == null;
}

let arrToList = function(arr) {
  if(arr != null && arr.length > 0) {
    let head = new ListNode(arr[0], null);
    let prev = head;
    let curr = null;
    for(let i=1; i<arr.length; i++) {
      curr = new ListNode(arr[i], null);
      prev.next = curr;
      prev = curr;
    }
    return head;
  } else {
    return null;
  }
}

let getNth = function(head, n) {
  let curr = head;
  let idx = 1;
  while(curr.next != null) {
    if(n === idx) return curr.next;
    curr = curr.next;
    idx = idx + 1;
  }
  return head;
}

/**
 * @param {ListNode} head
 * @return {void} Do not return anything, modify head in-place instead.
 */
let reorderList = function(head) {

  if(head == null || head.next == null) return head;

  // count the total nodes in the list
  let tail = head;
  let prev = tail;
  let count = 1;
  while(tail.next != null) {
    prev = tail;
    tail = tail.next;
    count = count + 1;
  }

  //console.log("count: " + count);

  let curr = head;

  while(curr != null && curr.next != null && curr.next.next != null) {
    /* console.log('------');
     * console.log('BEFORE');
     * console.log(head.toString());
     */
    let next = curr.next;
    curr.next = tail;
    tail.next = next;
    prev.next = null;

    /* console.log('AFTER');
     * console.log(head.toString());*/
    
    curr = tail.next;
    /* console.log('CURR');
     * console.log(curr);
     */
    while(tail.next != null) {
      prev = tail;
      tail = tail.next;
    }
  }
  
};

module.exports = {
  ListNode: ListNode,
  listsEqual: listsEqual,
  listNodesEqual: listNodesEqual,
  getNth: getNth,
  arrToList: arrToList,
  reorderList: reorderList
};

