package graphicsLoader.RoadEditorBuilder;

public class EditorState {
	
	private short editionState;
	private short currentBlockType;
	private short isHandled;
	
	public EditorState(){
		this.editionState = RoadEditorConfig.EMPTY_STATE;
		this.currentBlockType = RoadEditorConfig.EMPTY_STATE;
	}
	
	public short getState(){
		return this.editionState;
	}
	
	public void setState(short state){
		this.editionState = state;
	}
	
	public short getCurrentBlockType(){
		return this.currentBlockType;
	}
	
	public void setCurrentBlockType(short currentBlockType){
		this.currentBlockType = currentBlockType;
	}
	

}
