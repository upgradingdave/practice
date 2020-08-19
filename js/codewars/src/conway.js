let neighbors = function(cells, x, y) {
  let result = [];

  if(cells[y-1] && cells[y-1][x-1] != undefined ) { result.push(cells[y-1][x-1]); }
  if(cells[y-1] && cells[y-1][x]   != undefined ) { result.push(cells[y-1][x]);   }
  if(cells[y-1] && cells[y-1][x+1] != undefined ) { result.push(cells[y-1][x+1]); }

  if(cells[y] && cells[y][x-1] != undefined) { result.push(cells[y][x-1]); }
  if(cells[y] && cells[y][x+1] != undefined) { result.push(cells[y][x+1]); }

  if(cells[y+1] && cells[y+1][x-1] != undefined ) { result.push(cells[y+1][x-1]); }
  if(cells[y+1] && cells[y+1][x]   != undefined ) { result.push(cells[y+1][x]);   }
  if(cells[y+1] && cells[y+1][x+1] != undefined ) { result.push(cells[y+1][x+1]); }

  return result;
}

let getSingleGeneration = function(cells) {
  /* console.log('Before: ' + cells);*/
  result = [];
  for(y=0; y<cells.length; y++) {
    result.push([]);
    for(x=0; x<cells[y].length; x++) {
      let val = 0;
      let ns = neighbors(cells, x, y);
      let live = ns.reduce(function(acc, curr) {
	return acc + curr;
      }, 0);
      /* console.log('['+x+','+y+']='+cells[y][x]);
       * console.log('ns: '+ns);
       * console.log('live: '+live);*/
      if(live < 2 || live > 3) {
	val = 0;
      } else if (cells[y][x] == 0 && live == 3) {
	val = 1;
      }	else {
	val = cells[y][x];
      }
      result[y].push(val);
    }
  }
  /* console.log('After: ' + result);*/
  return result;
}

let shrink = function(cells) {

  if(cells.length <= 0) { return [[]] }
  
  let topZero = true;
  let bottomZero = true;
  let h = cells.length;
  let w = cells[0].length;
  for(let x=0; x<w; x++) {
    if(cells[0][x] == 1) {
      topZero = false;
    }
    if(cells[h-1][x] == 1) {
      bottomZero = false;
    }
  }

  let leftZero = true;
  let rightZero = true;

  for(let y=0; y<h; y++) {
    if(cells[y][0] == 1) {
      leftZero = false;
    }
    if(cells[y][w-1] == 1) {
      rightZero = false;
    }
  }

  let result = [];
  for(let y=0; y<h; y++) {
    if((y==0 && topZero) || (y==h-1 && bottomZero)) {
      // skip 
    } else {
      result.push([]);
      for(let x=0; x<w; x++) {
	if((x==0 && leftZero) || (x==w-1 && rightZero)) {
	  //skip
	} else {
	  result[y].push(cells[y][x]);
	}
      }
    }
  }
  if(result.length <= 0) { return [[]] }
  return result;
}

let grow = function(cells) {
  let h = cells.length;
  let w = cells[0].length;
  let result = [];

  let top = [];
  let bottom = [];
  for(let x=0; x<=w+1; x++) {
    top.push(0);
    bottom.push(0);
  }
  
  // top layer
  result.push(top);

  // left and right layers
  let y2 = 1;
  for(let y=0; y<h; y++) {
    result[y2] = [0];
    for(let x=0; x<w; x++) {
      result[y2].push(cells[y][x]); 
    }
    result[y2].push(0);
    y2=y2+1;
  }

  // bottom layer
  result.push(bottom);
  return result;
}
  
let getGeneration = function(cells, generations) {
  let result = cells;
  for(let i=0; i<generations; i++) {
    result = getSingleGeneration(result);
  }
  return result;
}

module.exports = {
  getGeneration: getGeneration,
  neighbors: neighbors,
  grow: grow,
  shrink: shrink
}

