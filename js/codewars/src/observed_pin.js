let possible = {
  '1': ['1','2','4'],
  '2': ['2','1','5','3'],
  '3': ['3','2','6'],
  '4': ['4','1','5','7'],
  '5': ['5','2','4','8','6'],
  '6': ['6','3','5','9'],
  '7': ['7','4','8'],
  '8': ['8','5','7','9','0'],
  '9': ['9','6','8'],
  '0': ['0','8']
}

function permute(possibles, result, row, s) {
  if(row == possibles.length) {
    result.push(s);
    return result;
  }

  for(let i=0; i<possibles[row].length; i++) {
    result = permute(possibles, result, row+1, s + possibles[row][i]);
  }

  return result;
}

function getPINs(observed) {
  let possibles = []
  for(let i=0;i<observed.length;i++) {
    let c = observed.charAt(i);
    possibles[i] = possible[c];
  }
  
  let result = permute(possibles, [], 0, '');
  return result;
}

module.exports = {
  getPINs: getPINs
}

