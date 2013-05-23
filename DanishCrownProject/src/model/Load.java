package model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
@Table(name="\"LOAD\"")
public class Load {

	@Id
	@GeneratedValue
	private int id;
	
	@Temporal(TemporalType.DATE)
	private Date actualBegTime;
	@Temporal(TemporalType.DATE)
	private Date actualEndTime;
	@Temporal(TemporalType.DATE)
	private Date estStartTime;
	@Temporal(TemporalType.DATE)
	private Date estEndTime;
	private boolean completed;
	@OneToOne
	private Suborder lSuborder;
	
	public Load() {}

	public Load(Date estStartTime, Date estEndTime,Suborder suborder) {
		super();
		this.estStartTime = estStartTime;
		this.estEndTime = estEndTime;
		this.lSuborder=suborder;		
	}

	public Date getActualBegTime() {
		return actualBegTime;
	}

	public void setActualBegTime(Date actualBegTime) {
		this.actualBegTime = actualBegTime;
	}

	public Date getActtualEndTime() {
		return actualEndTime;
	}

	public void setActualEndTime(Date acttualEndTime) {
		this.actualEndTime = acttualEndTime;
	}

	public Date getEstStartTime() {
		return estStartTime;
	}

	public void setEstStartTime(Date estStartTime) {
		this.estStartTime = estStartTime;
	}

	public Date getEstEndTime() {
		return estEndTime;
	}

	public void setEstEndTime(Date estEndTime) {
		this.estEndTime = estEndTime;
	}
	
	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public Suborder getlSuborder() {
		return lSuborder;
	}
 
	public void setlSuborder(Suborder lSuborder) {
		this.lSuborder = lSuborder;
	}

	@Override
	public String toString() {
		return "Load :" +estStartTime+ " End time:" 
				 + estEndTime;
	}	
}
