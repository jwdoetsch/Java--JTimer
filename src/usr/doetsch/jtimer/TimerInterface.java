package usr.doetsch.jtimer;

interface TimerInterface {
	
	/**
	 * Updates the time elapsed and returns the time elapsed
	 * in milliseconds.
	 * @return the time elapsed, in milliseconds
	 */
	abstract long update ();
	
	/**
	 * Resets the timer, maintaining the duration specified
	 * on instantiation.
	 */
	void reset ();
	
	/**
	 * Resets the timer with the given duration.
	 * @param duration the timer's duration, a duration
	 * less than 0 means there is no specified duration
	 */
	void reset (long duration);

	/**
	 * Starts the timer.
	 */
	void start ();
	
	/**
	 * Resets the timer to 00:00:00.000 but doesn't
	 * pause it;
	 * @return 
	 */
	long lap ();
	
	/**
	 * Pauses the timer.
	 */
	void pause ();
	
	/**
	 * Returns the operative status of the timer
	 * @return true if the timer is running, false otherwise
	 */
	boolean isRunning ();
	
	/**
	 * Returns whether or not the elapsed time has reached
	 * the specified timer duration.
	 * @return true if reached, false otherwise
	 */
	boolean isFinished ();
	
}
