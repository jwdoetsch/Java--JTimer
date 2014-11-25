package usr.doetsch.jtimer;

import java.util.List;

import javax.swing.JTextField;
import javax.swing.SwingWorker;

class JTimer extends SwingWorker<Boolean, Long> implements TimerInterface {

	private long duration;
	private long elapsedTime;
	private long startTime;
	private boolean isRunning;
	private JTextField textField;
	
	/**
	 * Instantiates a JTimer with the given duration
	 * 
	 * @param duration the duration of the JTimer, <0 if the
	 * JTimer should function as a stopwatch
	 */
	public JTimer (long duration, JTextField textField) {
		reset(duration);
		this.textField = textField;
	}
	
	@Override
	protected Boolean doInBackground () throws Exception {
		
		while (!isCancelled()) {
			
			if (isRunning()) {
				Thread.sleep(1);
				//textField = String.valueOf(update());
				//publish(update());
				update();
			}
			
		}
		
		return true;
	}

	@Override
	protected void process (List<Long> times) {
		long time = times.get(times.size() - 1);
		this.textField.setText(format(time));
	}
	
	@Override
	public long update () {
		if (isRunning()) {
			long currentTime = System.currentTimeMillis();
			this.elapsedTime += currentTime - startTime;
			this.startTime = currentTime;
		}		
		
		publish(this.elapsedTime);
		
		return this.elapsedTime;
	}

	@Override
	public void reset () {
		reset(duration);		
	}

	@Override
	public void reset (long duration) {
		this.duration = duration;
		this.isRunning = false;
		this.elapsedTime = 0;
		//this.textField = "00:00:00.000";
		publish((long)0);
	}

	@Override
	public void start () {
		if (!isRunning()) {
			this.isRunning = true;
			this.startTime = System.currentTimeMillis();
		}
	}

	@Override
	public long lap () {
		long elapsed = update();
		this.elapsedTime = 0;
		publish(this.elapsedTime);
		this.startTime = System.currentTimeMillis();
		return elapsed;
	}
	
	@Override
	public void pause () {
		if (isRunning()) {
			update();
			this.isRunning = false; 
		}
	}

	@Override
	public boolean isRunning () {
		return this.isRunning;
	}

	@Override
	public boolean isFinished () {
		return (elapsedTime >= duration);
	}
	
	public static String format (long time) {
		long hours, minutes, seconds;
		StringBuilder sb = new StringBuilder();
		
		hours = time / 3600000;
		time -= hours * 3600000;
		
		minutes = time / 60000;
		time -= minutes * 60000;
		
		seconds = time / 1000;
		time -= seconds * 1000;
		
		sb.append((hours < 10 ? "0" : ""));
		sb.append(String.valueOf(hours) + ":");

		sb.append((minutes < 10 ? "0" : ""));
		sb.append(String.valueOf(minutes) + ":");
		
		sb.append((seconds < 10 ? "0" : ""));
		sb.append(String.valueOf(seconds) + ".");
		
		if (time < 10) {
			sb.append("00");
		} else if (time < 100) {
			sb.append("0");
		}
		
		sb.append(String.valueOf(time));

		return sb.toString();
	}

	public static void main (String[] args) {
		System.out.println(JTimer.format(1000000));
	}
	
}
