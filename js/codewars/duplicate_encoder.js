
function duplicateEncode(word){
  var counts = {};
  for(var i=0; i<word.length; i++) {
    let c = word.toLowerCase().charAt(i);
    counts[c] = counts[c] ? counts[c] + 1 : 1;
  }
  var result = "";
  for(var i=0; i<word.length; i++) {
    let c = word.toLowerCase().charAt(i);
    result += counts[c] > 1 ? ')' : '(';
  }
  return result;
}

module.exports = {
  duplicateEncode: duplicateEncode
}

