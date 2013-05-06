package model;

import java.util.ArrayList;

public class LoadingDock {
	private int dockID;
	private Type lType;
	private Status lStatus;
	private ArrayList<Load> lLoad = new ArrayList<Load>();
	
	public LoadingDock() {
		
	}

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
	
	public ArrayList<Load> getlLoad() {
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
		return "LoadingDock [dockID=" + dockID + "]";
	}

	

}
