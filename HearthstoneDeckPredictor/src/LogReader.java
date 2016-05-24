import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;


public class LogReader implements Runnable{

	private boolean keepReading = true;
	private HearthPredictController controller;
	private String logPath;
	private boolean fileLoaded = false;

	public LogReader(HearthPredictController controller, String logPath) {
		this.controller = controller;
		this.logPath = logPath;
	}

	public void run() {
		String line;
		long fileSize;
		long filePointer = 0;
		File logfile = null;
		RandomAccessFile log = null;

		while(!fileLoaded){

			logfile = new File(logPath);

			try {
				log = new RandomAccessFile(logfile, "r");
				fileLoaded = true;
			} catch (FileNotFoundException e) {
				fileLoaded = false;
				//e.printStackTrace();
				System.out.println("File not yet loaded");
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
		
		//skips everything that has been written so far in the log file
		//this is not good when the player starts the application while a hearthstone game is already running
		//filePointer = logfile.length();

		try{
			while(keepReading){
				fileSize = logfile.length();
				//System.out.println("filePointer: " + filePointer + " fileSize: " + fileSize);
				if(filePointer > fileSize){
					log = new RandomAccessFile(logfile, "r");
					filePointer = 0;
				}
				if(filePointer < fileSize){
					log.seek(filePointer);
					while((line = log.readLine()) != null){
						controller.processLine(line);
					}
					filePointer = log.getFilePointer();
				}
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			log.close();
		}catch(Exception e){
			e.printStackTrace();
		}

	}

}
