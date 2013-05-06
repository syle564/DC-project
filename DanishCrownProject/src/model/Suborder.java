package model;

import java.util.Date;

public class Suborder {
	private int loadingTime;
	private int weight;
	private Date loadingDate;
	private Trailer lTrailer;
	private Load lLoad;
	
	public Suborder(int loadingTime, int weight, Date loadingDate) {
		super();
		this.loadingTime = loadingTime;
		this.weight = weight;
		this.loadingDate = loadingDate;
	}
	
	public Suborder(){}
	
	public int getLoadingTime() {
		return loadingTime;
	}

	public void setLoadingTime(int loadingTime) {
		this.loadingTime = loadingTime;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public Date getLoadingDate() {
		return loadingDate;
	}

	public void setLoadingDate(Date loadingDate) {
		this.loadingDate = loadingDate;
	}
	
	
	
	public Trailer getlTrailer() {
		return lTrailer;
	}

	public void setlTrailer(Trailer lTrailer) {
		this.lTrailer = lTrailer;
	}

	public Load getlLoad() {
		return lLoad;
	}

	public void setlLoad(Load lLoad) {
		this.lLoad = lLoad;
	}
	

	@Override
	public String toString() {
		return "Suborder [loadingTime=" + loadingTime + ", weight=" + weight
				+ ", loadingDate=" + loadingDate + "]";
	}

	
	
	

}
