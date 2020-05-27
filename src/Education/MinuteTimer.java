package Education;
import java.util.Timer;
import java.util.TimerTask;

public class MinuteTimer {

    public void startTimer() {
        Timer myTimer = new Timer();
        myTimer.schedule(new TimerTask(){
            @Override
            public void run() {
                System.out.println("hello from timer");
            }
        }, 60000);
    }
}