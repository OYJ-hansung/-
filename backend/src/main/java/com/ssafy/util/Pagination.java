package com.ssafy.util;

public class Pagination {

    /** 1. 페이지 당 보여지는 게시글의 최대 개수 **/
    private int pageSize = 10;

    /** 2. 페이징된 버튼의 블럭당 최대 개수. 이전 1, 2, 3, 4, 5 다음 >> 여기서 blockSize는 5 **/
    private int blockSize = 5;

    /** 3. 현재 페이지 **/
    private int page = 1;

    /** 4. 현재 블럭, 1, 2, 3, 4, 5 (여기까지 block은 1), 6, 7, 8, 9, 10 (block은 2) **/
    private int block = 1;

    /** 5. 총 게시글 수 **/
    private int totalListCnt;

    /** 6. 총 페이지 수 **/
    private int totalPageCnt;

    /** 7. 총 블럭 수 **/
    private int totalBlockCnt;

    /** 8. 블럭 시작 페이지 **/
    private int startPage = 1;

    /** 9. 블럭 마지막 페이지 **/
    private int endPage = 1;

    /** 10. DB 접근 시작 index **/
    private int startIndex = 0;

    /** 11. 이전 블럭의 마지막 페이지 **/
    private int prevBlock;

    /** 12. 다음 블럭의 시작 페이지 **/
    private int nextBlock;

    public Pagination(int totalListCnt, int page){

        // 총 게시물 수와 현재 페이지를 Controller로 부터 받아온다.

        // 총 게시물 수	- totalListCnt
        // 현재 페이지	- page


        /** 3. 현재 페이지 **/
        setPage(page);


        /** 5. 총 게시글 수 **/
        setTotalListCnt(totalListCnt);


        /** 6. 총 페이지 수 **/
        // 한 페이지의 최대 개수를 총 게시물 수 * 1.0로 나누어주고 올림 해준다.
        // 총 페이지 수 를 구할 수 있다.
        setTotalPageCnt((int) Math.ceil(totalListCnt * 1.0 / pageSize));


        /** 7. 총 블럭 수 **/
        // 한 블럭의 최대 개수를 총  페이지의 수 * 1.0로 나누어주고 올림 해준다.
        // 총 블럭 수를 구할 수 있다.
        setTotalBlockCnt((int) Math.ceil(totalPageCnt * 1.0 / blockSize));


        /** 4. 현재 블럭 **/
        // 현재 페이지 * 1.0을 블록의 최대 개수로 나누어주고 올림한다.
        // 현재 블록을 구할 수 있다.
        setBlock((int) Math.ceil((page * 1.0)/blockSize));


        /** 8. 블럭 시작 페이지 **/
        setStartPage((block - 1) * blockSize + 1);


        /** 9. 블럭 마지막 페이지 **/
        setEndPage(startPage + blockSize - 1);


        /* === 블럭 마지막 페이지에 대한 validation ===*/
        if(endPage > totalPageCnt){this.endPage = totalPageCnt;}


        /** 11. 이전 블럭(클릭 시, 이전 블럭 마지막 페이지) **/
        setPrevBlock((block * blockSize) - blockSize);


        /* === 이전 블럭에 대한 validation === */
        if(prevBlock < 1) {this.prevBlock = 1;}


        /** 12. 다음 블럭(클릭 시, 다음 블럭 첫번째 페이지) **/
        setNextBlock((block * blockSize) + 1);


        /* === 다음 블럭에 대한 validation ===*/
        if(nextBlock > totalPageCnt) {nextBlock = totalPageCnt;}


        /** 10. DB 접근 시작 index **/
        setStartIndex((page-1) * pageSize);

    }




    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setBlockSize(int blockSize) {
        this.blockSize = blockSize;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setBlock(int block) {
        this.block = block;
    }

    public void setTotalListCnt(int totalListCnt) {
        this.totalListCnt = totalListCnt;
    }

    public void setTotalPageCnt(int totalPageCnt) {
        this.totalPageCnt = totalPageCnt;
    }

    public void setTotalBlockCnt(int totalBlockCnt) {
        this.totalBlockCnt = totalBlockCnt;
    }

    public void setStartPage(int startPage) {
        this.startPage = startPage;
    }

    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public void setPrevBlock(int prevBlock) {
        this.prevBlock = prevBlock;
    }

    public void setNextBlock(int nextBlock) {
        this.nextBlock = nextBlock;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getBlockSize() {
        return blockSize;
    }

    public int getPage() {
        return page;
    }

    public int getBlock() {
        return block;
    }

    public int getTotalListCnt() {
        return totalListCnt;
    }

    public int getTotalPageCnt() {
        return totalPageCnt;
    }

    public int getTotalBlockCnt() {
        return totalBlockCnt;
    }

    public int getStartPage() {
        return startPage;
    }

    public int getEndPage() {
        return endPage;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public int getPrevBlock() {
        return prevBlock;
    }

    public int getNextBlock() {
        return nextBlock;
    }
}
