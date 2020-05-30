let tags = ['div', 'h1', 'p', 'span']

let el = function(tag, parentEl) {

  let elFn = function() {
    var argLen = arguments.length
    
    let text = ""
    for(arg in arguments) {
      text += arguments[arg]
    }

    let result = "";

    let parent = parentEl;
    let parentTags = [];
    while(parent != undefined) {
      parentTags.push(parent.tag);
      parent = parent.parentEl
    }

    for(let i=parentTags.length-1; i>=0; i--) {
      result += '<' + parentTags[i] + '>';
    }
    
    result += '<' + tag + '>' + text + '</' + tag + '>'

    for(let i=0; i<parentTags.length; i++) {
      result += '</' + parentTags[i] + '>';
    }

    return result
  }

  elFn.tag = tag
  elFn.parentEl = parentEl

  // Need to also add properties onto the elFn
  for(otherTag in tags) {
    let tagName = tags[otherTag]
    
    Object.defineProperty(elFn, tagName, {
      get() {
	return el(tagName, elFn)
      }
    })
  }
  
  return elFn;
}

let Format = {}
for(tag in tags) {
  Format[tags[tag]] = el(tags[tag]);
}

module.exports = {
  Format: Format
}
