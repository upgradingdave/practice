let snail = function(array) {
  
  if(array.length <=1 && array[0].length <= 0) return [];

  let result = [];

  let row = 0;
  let rowMax = array.length;
  let rowMin = 0;
  let col = 0;
  let colMax = array[row].length;
  let colMin = 0;

  while (rowMax > rowMin && colMax > colMin) {

    // left to right
    for(let x=col; x<colMax; x++) {
      result.push(array[row][x]);
      col = x;
    }

    row += 1;
    rowMin += 1;
    // top to bottom
    for(let y=row; y<rowMax; y++) {
      result.push(array[y][col]);
      row = y;
    }

    col -= 1;
    colMax -= 1;
    // right to left
    for(let x=col; x>=colMin; x--) {
      result.push(array[row][x]);
      col = x;
    }

    row -= 1;
    rowMax -= 1;
    // bottom to top
    for(let y=row; y>=rowMin; y--){
      result.push(array[y][col]);
      row = y;
    }

    col += 1;
    colMin += 1;
  }

  return result;
}

module.exports = {
  snail: snail
}

