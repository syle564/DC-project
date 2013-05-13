package model;


import java.util.Date;

public class Load {

	private Date actualBegTime;
	private Date acttualEndTime;
	private Date estStartTime;
	private Date estEndTime;
	private boolean completed;
	private Suborder lSuborder;
	
	
	
	
	public Load() {

	}


	public Load(Date estStartTime, Date estEndTime,Suborder suborder,LoadingDock loadingDock) {
		super();
		this.estStartTime = estStartTime;
		this.estEndTime = estEndTime;
		this.lSuborder=suborder;
		loadingDock.addLoad(this);
	}


	public Date getActualBegTime() {
		return actualBegTime;
	}


	public void setActualBegTime(Date actualBegTime) {
		this.actualBegTime = actualBegTime;
	}


	public Date getActtualEndTime() {
		return acttualEndTime;
	}


	public void setActtualEndTime(Date acttualEndTime) {
		this.acttualEndTime = acttualEndTime;
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
