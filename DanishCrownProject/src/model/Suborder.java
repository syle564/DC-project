package model;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Suborder {
	@Id 
	@GeneratedValue
	private int id;
	private int loadingTime;
	private int weight;
	@Temporal(TemporalType.DATE)
	private Date loadingDate;
	@ManyToOne
	private Trailer lTrailer;
	@OneToOne
	private Load lLoad;
	
	public Suborder(int loadingTime, int weight, Date loadingDate,Trailer trailer) {
		super();
		this.loadingTime = loadingTime;
		this.weight = weight;
		this.loadingDate = loadingDate;
		this.lTrailer=trailer;
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
