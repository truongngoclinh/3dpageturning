package com.smb.utils;

public class Constant {

	// is animation stated
	public static boolean ANIMATION_STATE = true;
	
	public static final int ROTATION_DEGREE = 20;
	public static final int SCROLL_SPEED_INCREASING = 100;
	
	// after rotating, make the next pages smaller than current page by scaling
	public static final float SCALE_FACTOR = 0.70f;
	
	// approximate value
	public static final int PAGE_TRANSLATE_X = 1350;
	
	// after rotating, move page to left margin of screen, distant based on rotating degree
	public static final int PAGE_TRANSLATE_X_AFTER_ROTATING = 350; 

}
