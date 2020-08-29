//Definition for singly - linked list.function
function ListNode(val, next) {
    this.val = (val === undefined ? 0 : val)
    this.next = (next === undefined ? null : next)
}

/**
 * @param {Integer[]} result
 * @param {Integer} carry
 * @param {ListNode} l1
 * @param {ListNode} l2
 * @return {Integer[]}
 */
function doAddTwoNumbers(result, carry, l1, l2) {
    if(l1 == null && l2 == null) {
        if(carry > 0) {
            result.push(carry);
        }
        return result;
    }

    let v1 = l1 === null ? 0 : l1.val;
    let v2 = l2 === null ? 0 : l2.val;
    let total = v1 + v2 + carry;
    if(total >= 10) {
        carry = Math.ceil(10/total);
        total = total % 10;
    } else {
        carry = 0;
    }
    result.push(total);
    return doAddTwoNumbers(result, carry, l1===null ? l1 : l1.next, l2===null ? l2 : l2.next);
}

/**
 * @param {ListNode} l1
 * @param {ListNode} l2
 * @return {ListNode}
 */
let addTwoNumbers = function (l1, l2) {
    let result = doAddTwoNumbers([], 0, l1, l2);
    if(result.length >= 1) {
        let resultList = new ListNode(result[0], null);
        let curr = resultList;
        for(let i=1; i<result.length; i++) {
            curr.next = new ListNode(result[i], null);
            curr = curr.next;
        }
        return resultList;
    } else {
        return new ListNode(0, null);
    }
};

module.exports = {
    addTwoNumbers: addTwoNumbers,
    ListNode: ListNode
};
