/*
 * @author  Maxim Vasilishin
 * @version 1.0
 */
package graphicsLoader.RoadEditorBuilder;

// TODO: Auto-generated Javadoc
/**
 * The Class EditorState.
 */
public class EditorState {
	
	/** The edition state. */
	private short editionState;
	
	/** The current block type. */
	private short currentBlockType;
	
	/** The is handled. */
	private boolean isHandled;
	
	/** The can build. */
	private boolean canBuild;
	
	/**
	 * Instantiates a new editor state.
	 */
	public EditorState(){
		this.editionState = RoadEditorConfig.EMPTY_STATE;
		this.currentBlockType = RoadEditorConfig.EMPTY_STATE;
		this.isHandled = false;
		this.canBuild = false;
	}
	
	/**
	 * Gets the state.
	 *
	 * @return the state
	 */
	public short getState(){
		return this.editionState;
	}
	
	/**
	 * Sets the state.
	 *
	 * @param state
	 *            the new state
	 */
	public void setState(short state){
		this.editionState = state;
		System.out.println("Change state :" + state);
	}
	
	/**
	 * Gets the current block type.
	 *
	 * @return the current block type
	 */
	public short getCurrentBlockType(){
		return this.currentBlockType;
	}
	
	/**
	 * Sets the current block type.
	 *
	 * @param currentBlockType
	 *            the new current block type
	 */
	public void setCurrentBlockType(short currentBlockType){
		this.currentBlockType = currentBlockType;
	}
	
	/**
	 * Sets the handled.
	 *
	 * @param x
	 *            the new handled
	 */
	public void setHandled(boolean x){
		this.isHandled = x;
	}
	
	/**
	 * Gets the handled.
	 *
	 * @return the handled
	 */
	public boolean getHandled(){
		return this.isHandled;
	}
	
	/**
	 * Sets the can build.
	 *
	 * @param x
	 *            the new can build
	 */
	public void setCanBuild(boolean x){
		this.canBuild = x;
		
	}
	
	/**
	 * Gets the can build.
	 *
	 * @return the can build
	 */
	public boolean getCanBuild(){
		return this.canBuild;
	}
}
