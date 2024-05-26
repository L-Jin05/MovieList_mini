package project;

public class P_detail {

	private String movieNm;
	private String openDt;
	private String genreNm;
	private String showTm;
	private String directors;
	private String actors;

	public P_detail(String mNm, String dt, String genre, String time, String direc, String actors) {
		this.movieNm = mNm;
		this.openDt = dt;
		this.genreNm = genre;
		this.showTm = time;
		this.directors = direc;
		this.actors = actors;
	}

	// set
	public String setmNm(String mNm) {
		return mNm;
	}

	public String setdt(String dt) {
		return dt;
	}

	public String setgenre(String genre) {
		return genre;
	}

	public String settime(String time) {
		return time;
	}

	public String setdirec(String direc) {
		return direc;
	}

	public String setactors(String actors) {
		return actors;
	}

	// get

	public String getmNm() {
		return movieNm;
	}

	public String getdt() {
		return openDt;
	}

	public String getgenre() {
		return genreNm;
	}

	public String gettime() {
		return showTm;
	}

	public String getdirec() {
		return directors;
	}

	public String getactors() {
		return actors;
	}

	@Override
	public String toString() {
		return "movie[movieNm:" + movieNm + ", openDt:" + openDt + 
				", genreNm:" + genreNm + ", showTm:" + showTm +
				", directors:" + directors + ", actors:" + actors + "]";
	}
}
