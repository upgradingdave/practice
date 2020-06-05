class PaginationHelper {
  constructor(collection, itemsPerPage) {
    this._collection = collection;
    this._itemsPerPage = itemsPerPage;
  }
  
  pageCount() {
    let pageCount = Math.floor(this.itemCount() / this._itemsPerPage);
    if (this._collection.length % this._itemsPerPage > 0) {
      pageCount += 1;
    }
    return pageCount;
  }

  itemCount() {
    return this._collection.length;
  }

  pageItemCount(page) {
    if (page < (this.pageCount()-1)) {
      return this._itemsPerPage;
    } else if(page == (this.pageCount()-1)) {
      return this._collection.length % this._itemsPerPage;
    } else {
      return -1;
    }
  }

  pageIndex(index) {
    if(index < 0 || index >= this.itemCount()) {
      return -1;
    } else {
      return Math.floor(index / this._itemsPerPage);
    }
  }
}

module.exports = {
  PaginationHelper: PaginationHelper
}

