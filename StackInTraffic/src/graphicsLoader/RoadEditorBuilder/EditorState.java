package graphicsLoader.RoadEditorBuilder;

public class EditorState {
	
	private short editionState;
	private short currentBlockType;
	private boolean isHandled;
	private boolean canBuild;
	
	public EditorState(){
		this.editionState = RoadEditorConfig.EMPTY_STATE;
		this.currentBlockType = RoadEditorConfig.EMPTY_STATE;
		this.isHandled = false;
		this.canBuild = false;
	}
	
	public short getState(){
		return this.editionState;
	}
	
	public void setState(short state){
		this.editionState = state;
		System.out.println("Change state :" + state);
	}
	
	public short getCurrentBlockType(){
		return this.currentBlockType;
	}
	
	public void setCurrentBlockType(short currentBlockType){
		this.currentBlockType = currentBlockType;
	}
	
	public void setHandled(boolean x){
		this.isHandled = x;
	}
	
	public boolean getHandled(){
		return this.isHandled;
	}
	
	public void setCanBuild(boolean x){
		this.canBuild = x;
		
	}
	
	public boolean getCanBuild(){
		return this.canBuild;
	}
}
