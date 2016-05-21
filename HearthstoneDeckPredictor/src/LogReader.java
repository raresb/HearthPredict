import java.io.File;
import java.io.RandomAccessFile;


public class LogReader implements Runnable{

	private boolean keepReading = true;
	private HearthPredictController controller;
	private String logPath;

	public LogReader(HearthPredictController controller, String logPath) {
		this.controller = controller;
		this.logPath = logPath;
	}

	public void run() {
		try{
			String line;
			long fileSize;
			long filePointer = 0;
			File logfile = new File(logPath);
			RandomAccessFile log = new RandomAccessFile(logfile, "r");
			while(keepReading){
				fileSize = logfile.length();
		
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
				Thread.sleep(500);
			}
			log.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
