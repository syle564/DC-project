package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
@Entity
public class LoadingDock {
	
	@Id
	@GeneratedValue
	private int dockID;
	@Enumerated(EnumType.STRING)
	private Type lType;
	@Enumerated(EnumType.STRING)
	private Status lStatus;
	@OneToMany(cascade=CascadeType.PERSIST)
	@JoinColumn
	private List<Load> lLoad = new ArrayList<Load>();
	
	public LoadingDock() {}

	public LoadingDock(int dockID, Type lType, Status lStatus) {
		super();
		this.dockID = dockID;
		this.lType = lType;
		this.lStatus = lStatus;
	}	
	
	public int getDockID() {
		return dockID;
	}

	public void setDockID(int dockID) {
		this.dockID = dockID;
	}

	public Type getlType() {
		return lType;
	}

	public void setlType(Type lType) {
		this.lType = lType;
	}

	public Status getlStatus() {
		return lStatus;
	}

	public void setlStatus(Status lStatus) {
		this.lStatus = lStatus;
	}
	
	public List<Load> getlLoad() {
		return lLoad;
	}

	public void addLoad(Load load){
		this.lLoad.add(load);
	}
	
	public void removeLoad(Load load){
		this.lLoad.remove(load);
	}

	@Override
	public String toString() {
		return "DockID:" + dockID ;
	}
}
