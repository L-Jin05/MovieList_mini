package project;

public class p_movieList {

	private String rnum;
	private String movieNm;
	private String movieCd;

	public p_movieList(String rnum, String movieNm, String movieCd) {
		this.rnum = rnum;
		this.movieNm = movieNm;
		this.movieCd = movieCd;
	}

	// set
	public String setrnum(String rnum) {
		return rnum;
	}

	public String setName(String movieNm) {
		return movieNm;
	}


	public String setCd(String movieCd) {
		return movieCd;
	}

	// get

	public String getrnum() {
		return rnum;
	}

	public String getName() {
		return movieNm;
	}


	public String getCd() {
		return movieCd;
	}

	@Override
	public String toString() {
		return "movie[rnum:" + rnum + ", movieNm:" + movieNm + ", movieCd:" + movieCd + "]";
	}
}
