/**
 * @param {number[]} a
 * @return {number[]}
 */
let sortArrayByParity = function(a) {
  let even = [];
  let odd = [];
  for(let i=0; i<a.length; i++) {
    if(a[i]%2===0) {
      even.push(a[i]);
    } else {
      odd.push(a[i]);
    }
  }
  let result = [];
  for(let i=0; i<even.length; i++) {
    result.push(even[i]);
  }
  for(let i=0; i<odd.length; i++) {
    result.push(odd[i]);
  }
  
  return result;
};

module.exports = {
  sortArrayByParity: sortArrayByParity
};

