/**
 * @param {string} s
 * @return {string}
 */
let toGoatLatin = function(s) {
  if(s == null) {
    return null;
  }

  let result = "";
  
  let words = s.split(/[ ]+/);

  for(let i=0; i<words.length; i++) {

    let w = words[i];

    if(w.substring(0,1).toLowerCase() == 'a' || 
       w.substring(0,1).toLowerCase() == 'e' ||
       w.substring(0,1).toLowerCase() == 'i' ||
       w.substring(0,1).toLowerCase() == 'o' ||
       w.substring(0,1).toLowerCase() == 'u') {

      w = w + 'ma';
    } else {
      w = w.substring(1) + w.substring(0,1) + 'ma';
    }

    for(let j=0; j<=i; j++) {
      w = w + 'a'
    }

    if(i==0) {
      result = result + w;
    } else {
      result = result + ' ' + w;
    }
  }
  return result;
  
};

module.exports = {
  toGoatLatin: toGoatLatin
};

