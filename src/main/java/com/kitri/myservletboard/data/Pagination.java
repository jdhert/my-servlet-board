package com.kitri.myservletboard.data;

public class Pagination {

    private int currentPage;

    private int recordsPerPage = 10;

    private int pagesOnScreen = 5;

    private int startIndex = 0;

    private int totalRecords = 0;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    private String type;
    private String keyword;

    private boolean hasNext = false;

    public boolean isHasNext() {
        return hasNext;
    }

    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }

    public boolean isHasPrev() {
        return hasPrev;
    }

    public void setHasPrev(boolean hasPrev) {
        this.hasPrev = hasPrev;
    }

    private boolean hasPrev = false;

    public int getStartPageOnScreen() {
        return startPageOnScreen;
    }

    public void setStartPageOnScreen(int startPageOnScreen) {
        this.startPageOnScreen = startPageOnScreen;
    }

    public int getEndPageOnScreen() {
        return endPageOnScreen;
    }

    public void setEndPageOnScreen(int endPageOnScreen) {
        this.endPageOnScreen = endPageOnScreen;
    }

    private int startPageOnScreen = 1;

    private int endPageOnScreen = this.pagesOnScreen;

    private String term;

    private String orderBy;

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public void datePagination(){
        switch (this.term){
            case "all":
                break;
            case "day":
                break;
                case "week":
                break;
                case "month":
                break;
                case "half_mon":
                break;
                case "year":
                break;
            default:
                break;
        }
    }
    public void calcPagination(){
        int totalPages = (int) Math.ceil((double) this.totalRecords / this.recordsPerPage);
        this.startPageOnScreen =  ((int)(Math.ceil((double) this.currentPage / this.pagesOnScreen) - 1) * this.pagesOnScreen + 1);
        this.endPageOnScreen = this.startPageOnScreen + this.pagesOnScreen - 1;
        if(this.endPageOnScreen > totalPages){
            this.endPageOnScreen = totalPages;
        }
        if (this.endPageOnScreen < totalPages){
            this.hasNext = true;
        }
        if(this.startPageOnScreen > this.pagesOnScreen){
            this.hasPrev = true;
        }
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getRecordsPerPage() {
        return recordsPerPage;
    }

    public void setRecordsPerPage(int recordsPerPage) {
        this.recordsPerPage = recordsPerPage;
    }

    public int getPagesOnScreen() {
        return pagesOnScreen;
    }

    public void setPagesOnScreen(int pagesOnScreen) {
        this.pagesOnScreen = pagesOnScreen;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }

    public Pagination(int currentPage) {

        this.currentPage = currentPage;

    }

}
