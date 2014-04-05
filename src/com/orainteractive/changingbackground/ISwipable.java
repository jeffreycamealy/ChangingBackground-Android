package com.orainteractive.changingbackground;

/**
 * The interface that provides swiping functionality. 
 * The class that implements this interface should override two methods for forward and backward shifting.
 * Each method should check the validity of corresponding operation.
 * For example, forward/backward shifts are allowed if next/previous fragment exists.
 * 
 * @author Artem Vynohradov
 */
interface ISwipable {

	/**
	 * Provides forward shifting.
	 */
	public void swipeForward();
	
	/**
	 * Provides backward shifting.
	 */
	public void swipeBackward();
	
}
